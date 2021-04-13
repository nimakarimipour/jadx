package jadx.api;

import org.jetbrains.annotations.Nullable;

import jadx.Initializer;

import java.io.Closeable;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jadx.api.plugins.JadxPlugin;
import jadx.api.plugins.JadxPluginManager;
import jadx.api.plugins.input.JadxInputPlugin;
import jadx.api.plugins.input.data.ILoadResult;
import jadx.core.Jadx;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.nodes.LineAttrNode;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.dex.visitors.SaveCode;
import jadx.core.export.ExportGradleProject;
import jadx.core.utils.Utils;
import jadx.core.utils.exceptions.JadxRuntimeException;
import jadx.core.xmlgen.BinaryXMLParser;
import jadx.core.xmlgen.ResourcesSaver;

public final class JadxDecompiler implements Closeable {
	private static final Logger LOG = LoggerFactory.getLogger(JadxDecompiler.class);

	private final JadxArgs args;
	private final JadxPluginManager pluginManager = new JadxPluginManager();
	private final List<ILoadResult> loadedInputs = new ArrayList<>();

	@Nullable
	private RootNode root;

	@Nullable
	private List<JavaClass> classes;

	@Nullable
	private List<ResourceFile> resources;

	@Nullable
	private BinaryXMLParser xmlParser;

	private final Map<ClassNode, JavaClass> classesMap = new ConcurrentHashMap<>();
	private final Map<MethodNode, JavaMethod> methodsMap = new ConcurrentHashMap<>();
	private final Map<FieldNode, JavaField> fieldsMap = new ConcurrentHashMap<>();

	public JadxDecompiler() {
		this(new JadxArgs());
	}

	public JadxDecompiler(JadxArgs args) {
		this.args = args;
	}

	public void load() {
		reset();
		JadxArgsValidator.validate(args);
		LOG.info("loading ...");
		loadInputFiles();

		root = new RootNode(args);
		root.loadClasses(loadedInputs);
		root.initClassPath();
		root.loadResources(getResources());
		root.runPreDecompileStage();
		root.initPasses();
	}

	private void loadInputFiles() {
		loadedInputs.clear();
		List<Path> inputPaths = Utils.collectionMap(args.getInputFiles(), File::toPath);
		for (JadxInputPlugin inputPlugin : pluginManager.getInputPlugins()) {
			ILoadResult loadResult = inputPlugin.loadFiles(inputPaths);
			if (loadResult != null && !loadResult.isEmpty()) {
				loadedInputs.add(loadResult);
			}
		}
	}

	private void reset() {
		root = null;
		classes = null;
		resources = null;
		xmlParser = null;

		classesMap.clear();
		methodsMap.clear();
		fieldsMap.clear();

		closeInputs();
	}

	private void closeInputs() {
		loadedInputs.forEach(load -> {
			try {
				load.close();
			} catch (Exception e) {
				LOG.error("Failed to close input", e);
			}
		});
		loadedInputs.clear();
	}

	@Override
	public void close() {
		reset();
	}

	public void registerPlugin(JadxPlugin plugin) {
		pluginManager.register(plugin);
	}

	public static String getVersion() {
		return Jadx.getVersion();
	}

	public void save() {
		save(!args.isSkipSources(), !args.isSkipResources());
	}

	public void saveSources() {
		save(true, false);
	}

	public void saveResources() {
		save(false, true);
	}

	private void save(boolean saveSources, boolean saveResources) {
		ExecutorService ex = getSaveExecutor(saveSources, saveResources);
		ex.shutdown();
		try {
			ex.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			LOG.error("Save interrupted", e);
			Thread.currentThread().interrupt();
		}
	}

	public ExecutorService getSaveExecutor() {
		return getSaveExecutor(!args.isSkipSources(), !args.isSkipResources());
	}

	@Initializer
	private ExecutorService getSaveExecutor(boolean saveSources, boolean saveResources) {
		if (root == null) {
			throw new JadxRuntimeException("No loaded files");
		}
		int threadsCount = args.getThreadsCount();
		LOG.debug("processing threads count: {}", threadsCount);

		LOG.info("processing ...");
		ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

		File sourcesOutDir;
		File resOutDir;
		if (args.isExportAsGradleProject()) {
			ExportGradleProject export = new ExportGradleProject(root, args.getOutDir());
			export.init();
			sourcesOutDir = export.getSrcOutDir();
			resOutDir = export.getResOutDir();
		} else {
			sourcesOutDir = args.getOutDirSrc();
			resOutDir = args.getOutDirRes();
		}
		if (saveResources) {
			appendResourcesSave(executor, resOutDir);
		}
		if (saveSources) {
			appendSourcesSave(executor, sourcesOutDir);
		}
		return executor;
	}

	private void appendResourcesSave(ExecutorService executor, File outDir) {
		Set<String> inputFileNames = args.getInputFiles().stream().map(File::getAbsolutePath).collect(Collectors.toSet());
		for (ResourceFile resourceFile : getResources()) {
			if (resourceFile.getType() != ResourceType.ARSC
					&& inputFileNames.contains(resourceFile.getOriginalName())) {
				// ignore resource made from input file
				continue;
			}
			executor.execute(new ResourcesSaver(outDir, resourceFile));
		}
	}

	private void appendSourcesSave(ExecutorService executor, File outDir) {
		Predicate<String> classFilter = args.getClassFilter();
		for (JavaClass cls : getClasses()) {
			if (cls.getClassNode().contains(AFlag.DONT_GENERATE)) {
				continue;
			}
			if (classFilter != null && !classFilter.test(cls.getFullName())) {
				continue;
			}
			executor.execute(() -> {
				try {
					ICodeInfo code = cls.getCodeInfo();
					SaveCode.save(outDir, cls.getClassNode(), code);
				} catch (Exception e) {
					LOG.error("Error saving class: {}", cls.getFullName(), e);
				}
			});
		}
	}

	public List<JavaClass> getClasses() {
		if (root == null) {
			return Collections.emptyList();
		}
		if (classes == null) {
			List<ClassNode> classNodeList = root.getClasses(false);
			List<JavaClass> clsList = new ArrayList<>(classNodeList.size());
			classesMap.clear();
			for (ClassNode classNode : classNodeList) {
				if (!classNode.contains(AFlag.DONT_GENERATE)) {
					JavaClass javaClass = new JavaClass(classNode, this);
					clsList.add(javaClass);
					classesMap.put(classNode, javaClass);
				}
			}
			classes = Collections.unmodifiableList(clsList);
		}
		return classes;
	}

	@Initializer
	public List<ResourceFile> getResources() {
		if (resources == null) {
			if (root == null) {
				return Collections.emptyList();
			}
			resources = new ResourcesLoader(this).load();
		}
		return resources;
	}

	public List<JavaPackage> getPackages() {
		List<JavaClass> classList = getClasses();
		if (classList.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, List<JavaClass>> map = new HashMap<>();
		for (JavaClass javaClass : classList) {
			String pkg = javaClass.getPackage();
			List<JavaClass> clsList = map.computeIfAbsent(pkg, k -> new ArrayList<>());
			clsList.add(javaClass);
		}
		List<JavaPackage> packages = new ArrayList<>(map.size());
		for (Map.Entry<String, List<JavaClass>> entry : map.entrySet()) {
			packages.add(new JavaPackage(entry.getKey(), entry.getValue()));
		}
		Collections.sort(packages);
		for (JavaPackage pkg : packages) {
			pkg.getClasses().sort(Comparator.comparing(JavaClass::getName, String.CASE_INSENSITIVE_ORDER));
		}
		return Collections.unmodifiableList(packages);
	}

	public int getErrorsCount() {
		if (root == null) {
			return 0;
		}
		return root.getErrorsCounter().getErrorCount();
	}

	public int getWarnsCount() {
		if (root == null) {
			return 0;
		}
		return root.getErrorsCounter().getWarnsCount();
	}

	public void printErrorsReport() {
		if (root == null) {
			return;
		}
		root.getClsp().printMissingClasses();
		root.getErrorsCounter().printReport();
	}

	/**
	 * Internal API. Not Stable!
	 */
	public RootNode getRoot() {
		return root;
	}

	@Initializer
	synchronized BinaryXMLParser getXmlParser() {
		if (xmlParser == null) {
			xmlParser = new BinaryXMLParser(root);
		}
		return xmlParser;
	}

	private void loadJavaClass(@Nullable JavaClass javaClass) {
		javaClass.getMethods().forEach(mth -> methodsMap.put(mth.getMethodNode(), mth));
		javaClass.getFields().forEach(fld -> fieldsMap.put(fld.getFieldNode(), fld));

		for (JavaClass innerCls : javaClass.getInnerClasses()) {
			classesMap.put(innerCls.getClassNode(), innerCls);
			loadJavaClass(innerCls);
		}
	}

	@Nullable
	private JavaClass getJavaClassByNode(ClassNode cls) {
		JavaClass javaClass = classesMap.get(cls);
		if (javaClass != null) {
			return javaClass;
		}
		// load parent class if inner
		ClassNode parentClass = cls.getTopParentClass();
		if (parentClass.contains(AFlag.DONT_GENERATE)) {
			return null;
		}
		if (parentClass != cls) {
			JavaClass parentJavaClass = classesMap.get(parentClass);
			if (parentJavaClass == null) {
				getClasses();
				parentJavaClass = classesMap.get(parentClass);
			}
			loadJavaClass(parentJavaClass);
			javaClass = classesMap.get(cls);
			if (javaClass != null) {
				return javaClass;
			}
		}
		// class or parent classes can be excluded from generation
		if (cls.hasNotGeneratedParent()) {
			return null;
		}
		throw new JadxRuntimeException("JavaClass not found by ClassNode: " + cls);
	}


	@Nullable
	private JavaMethod getJavaMethodByNode(MethodNode mth) {
		JavaMethod javaMethod = methodsMap.get(mth);
		if (javaMethod != null) {
			return javaMethod;
		}
		// parent class not loaded yet
		JavaClass javaClass = getJavaClassByNode(mth.getParentClass().getTopParentClass());
		if (javaClass == null) {
			return null;
		}
		loadJavaClass(javaClass);
		javaMethod = methodsMap.get(mth);
		if (javaMethod != null) {
			return javaMethod;
		}
		if (mth.getParentClass().hasNotGeneratedParent()) {
			return null;
		}
		throw new JadxRuntimeException("JavaMethod not found by MethodNode: " + mth);
	}


	@Nullable
	private JavaField getJavaFieldByNode(FieldNode fld) {
		JavaField javaField = fieldsMap.get(fld);
		if (javaField != null) {
			return javaField;
		}
		// parent class not loaded yet
		JavaClass javaClass = getJavaClassByNode(fld.getParentClass().getTopParentClass());
		if (javaClass == null) {
			return null;
		}
		loadJavaClass(javaClass);
		javaField = fieldsMap.get(fld);
		if (javaField != null) {
			return javaField;
		}
		if (fld.getParentClass().hasNotGeneratedParent()) {
			return null;
		}
		throw new JadxRuntimeException("JavaField not found by FieldNode: " + fld);
	}


	@Nullable
	JavaNode convertNode(Object obj) {
		if (!(obj instanceof LineAttrNode)) {
			return null;
		}
		LineAttrNode node = (LineAttrNode) obj;
		if (node.contains(AFlag.DONT_GENERATE)) {
			return null;
		}
		if (obj instanceof ClassNode) {
			return getJavaClassByNode((ClassNode) obj);
		}
		if (obj instanceof MethodNode) {
			return getJavaMethodByNode(((MethodNode) obj));
		}
		if (obj instanceof FieldNode) {
			return getJavaFieldByNode((FieldNode) obj);
		}
		throw new JadxRuntimeException("Unexpected node type: " + obj);
	}

	List<JavaNode> convertNodes(Collection<?> nodesList) {
		return nodesList.stream()
				.map(this::convertNode)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}


	@Nullable
	public JavaNode getJavaNodeAtPosition(ICodeInfo codeInfo, int line, int offset) {
		Map<CodePosition, Object> map = codeInfo.getAnnotations();
		if (map.isEmpty()) {
			return null;
		}
		Object obj = map.get(new CodePosition(line, offset));
		if (obj == null) {
			return null;
		}
		return convertNode(obj);
	}


	@Nullable
	public CodePosition getDefinitionPosition(JavaNode javaNode) {
		JavaClass jCls = javaNode.getTopParentClass();
		jCls.decompile();
		int defLine = javaNode.getDecompiledLine();
		if (defLine == 0) {
			return null;
		}
		return new CodePosition(jCls, defLine, 0);
	}

	public JadxArgs getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "jadx decompiler " + getVersion();
	}
}
