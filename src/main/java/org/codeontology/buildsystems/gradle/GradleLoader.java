package org.codeontology.buildsystems.gradle;

import org.apache.commons.io.FileUtils;
import org.codeontology.CodeOntology;
import org.codeontology.buildsystems.DependenciesLoader;

<<<<<<< HEAD
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
=======
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
>>>>>>> master


/**
 * A gradle frontend.
 */
public class GradleLoader extends DependenciesLoader {

    private File gradleLocalRepository;
<<<<<<< HEAD
    private File projectDirectory;
    private File buildFile;
    private File error;
    private File output;
    private boolean subProject;
    private File root;

    public GradleLoader(File projectDirectory) {
        gradleLocalRepository = new File(System.getProperty("user.home") + "/.gradle");
        this.projectDirectory = projectDirectory;
        buildFile = new File(projectDirectory.getPath() + "/build.gradle");
        error = new File(projectDirectory.getPath() + "/error");
        output = new File(projectDirectory.getPath() + "/output");
        subProject = false;
        this.root = projectDirectory;
=======
    private File root;

    public GradleLoader(File root) {
        gradleLocalRepository = new File(System.getProperty("user.home") + "/.gradle");
        this.root = root;
    }

    public GradleLoader(String path) {
        this(new File(path));
>>>>>>> master
    }

    @Override
    public void loadDependencies() {
<<<<<<< HEAD
        System.out.println("Loading dependencies with gradle...");
        handleLocalProperties();

        GradleModulesHandler modulesHandler = new GradleModulesHandler(this);
        Set<File> modules = modulesHandler.findModules();
        Set<File> subProjects = new HashSet<>();
        if (!subProject) {
            subProjects = modulesHandler.findSubProjects();
        }

        if (CodeOntology.downloadDependencies()) {
            downloadDependencies();
        }

=======
        GradleModulesHandler modulesHandler = new GradleModulesHandler(root);
        Set<File> modules = modulesHandler.findModules();
        Set<File> subProjects = modulesHandler.findSubProjects();

        if (CodeOntology.getDownloadDependenciesFlag()) {
            downloadDependencies();
        }

        getLoader().loadAllJars(root);
        getLoader().loadAllJars(gradleLocalRepository);

        File src = new File(root.getPath() + "/src/");
        if (src.exists()) {
            getLoader().loadAllJars(src);
        }

>>>>>>> master
        // Run on sub-modules
        for (File module : modules) {
            getLoader().loadAllJars(module);
        }

<<<<<<< HEAD
        runTasks();
        loadClasspath();

=======
>>>>>>> master
        // Run on sub-projects: those may be gradle projects as well as maven/ant ones
        // need to get back to the main DefaultManager
        for (File subProject : subProjects) {
            System.out.println("Running on sub-project: " + subProject.getPath());
<<<<<<< HEAD
            DependenciesLoader loader = getFactory().getLoader(subProject);
            if (loader instanceof GradleLoader) {
                ((GradleLoader) loader).setSubProject(true);
                ((GradleLoader) loader).setRoot(projectDirectory);
            }
            loader.loadDependencies();
        }
    }

    private void handleLocalProperties() {
        File localProperties = new File(getProjectDirectory().getPath() + "/local.properties");
        File tmp = new File(getProjectDirectory().getPath() + "/.tmp.properties");
        if (localProperties.exists()) {
            try (Scanner scanner = new Scanner(localProperties)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmp))) {

                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (!line.trim().startsWith("sdk.dir")) {
                            writer.write(line + "\n");
                        }
                    }

                    FileUtils.forceDelete(localProperties);
                    boolean success = tmp.renameTo(localProperties);
                    if (!success) {
                        FileUtils.forceDelete(tmp);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void loadClasspath() {
        File classpathFile = new File(projectDirectory + "/build/cp");
        String classpath = "";

        try (Scanner scanner = new Scanner(classpathFile)) {
            scanner.useDelimiter("\\Z");
            if (scanner.hasNext()) {
                classpath = scanner.next().trim();
            }
            if (classpath.equals("")) {
                loadAllAvailableJars();
            } else {
                getLoader().loadClasspath(classpath);
            }
        } catch (FileNotFoundException e) {
            loadAllAvailableJars();
        }

        File src = new File(projectDirectory.getPath() + "/src/");
        if (src.exists()) {
            getLoader().loadAllJars(src);
        }
    }

    protected void loadAllAvailableJars() {
        getLoader().loadAllJars(root);
        getLoader().lock();
        getLoader().loadAllJars(gradleLocalRepository);
        getLoader().release();
    }

    public void downloadDependencies() {
        try {
            System.out.println("Downloading dependencies...");
            getBuilder("dependencies").start().waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void runTasks() {
        applyPlugin("java");

        String testJarTask = "CodeOntologyTestJar";
        String testJarTaskBody = "(type: Jar) {" + '\n' +
                '\t' + "classifier = 'tests'" + '\n' +
                '\t' + "from sourceSets.test.output" + '\n' +
                "}";

        addTask(testJarTask, testJarTaskBody);
        runTask(testJarTask);

        String separator = System.getProperty("line.separator");
        String cpFileTask = "CodeOntologyCpFile";
        String cpFileTaskBody = "{" + separator +
                '\t' + "buildDir.mkdirs()" + separator +
                '\t' + "new File(buildDir, \"cp\").text = configurations.runtime.asPath" + separator +
                "}";

        addTask(cpFileTask, cpFileTaskBody);
        runTask(cpFileTask);
    }

    protected void applyPlugin(String plugin) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(buildFile, true)))) {
            String build = getBuildFileContent();
            String applyPlugin = "apply plugin: " + "\'" + plugin + "\'";
            if (build != null && !hasPlugin(plugin)) {
                writer.write("\n" + " \n" + applyPlugin);
            }
        } catch (IOException e) {
            CodeOntology.showWarning("Could not apply plugin " + plugin);
        }
    }

    public boolean hasPlugin(String plugin) {
        String buildFile = getBuildFileContent();
        String regex = ".*apply\\s+plugin\\s*:\\s+\'" + plugin + "\'.*";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return pattern.matcher(buildFile).matches();
    }

    protected void addTask(String name, String body) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(buildFile, true)))) {
            String build = getBuildFileContent();
            Pattern pattern = Pattern.compile(".*task\\s+" + name + ".*", Pattern.DOTALL);
            if (build != null && !pattern.matcher(build).matches()) {
                writer.write("\n" + "\n" + "task " + name + " " + body);
            }
        } catch (IOException e) {
            CodeOntology.showWarning("Could not add task " + name);
        }
    }

    protected void runTask(String name) {
        try {
            getBuilder(name).start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Could not run task " + name);
        }
    }

    public String getBuildFileContent() {
        try (Scanner scanner = new Scanner(buildFile)) {
            scanner.useDelimiter("\\Z");
            String build = "";
            if (scanner.hasNext()) {
                build = scanner.next();
            }
            scanner.close();
            return build;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public File getProjectDirectory() {
        return projectDirectory;
    }

    public File getBuildFile() {
        return buildFile;
    }

    public void setSubProject(boolean subProject) {
        this.subProject = subProject;
    }

    public void setRoot(File root) {
        this.root = root;
    }

    public File getRoot() {
        return root;
    }

    public ProcessBuilder getBuilder(String command) {
        ProcessBuilder builder;
        File gradlew = new File(getRoot().getPath() + "/gradlew");
        if (gradlew.exists()) {
            if (!gradlew.setExecutable(true)) {
                CodeOntology.showWarning("Could not execute gradlew");
            }
            builder = new ProcessBuilder("bash", "-c", "./gradlew " + command);
            builder.directory(root);
        } else {
            builder = new ProcessBuilder("gradle", command);
            builder.directory(projectDirectory);
        }

        builder.redirectError(error);
        builder.redirectOutput(output);

        return builder;
=======
            getFactory().getLoader(subProject).loadDependencies();
        }
    }

    public void downloadDependencies() {
        try {
            File build = new File(root.getPath() + "/build.gradle");
            Scanner scanner = new Scanner(build);
            Random randomGenerator = new Random();

            String testJarTask = "testJar";
            String taskBody = "(type: Jar) {" + '\n' +
                    '\t' + "classifier = 'tests'" + '\n' +
                    '\t' + "from sourceSets.test.output" + '\n' +
                    "}";

            while (scanner.findInLine("task " + testJarTask + "\\(.+") != null) {
                testJarTask += randomGenerator.nextInt();
            }

            System.out.println("file: " + build.getPath());
            FileUtils.write(build, '\n' + '\n' + "apply plugin: \'java\'" + '\n' + '\n' + "task " + testJarTask + " " + taskBody, true);

            System.out.println("Downloading dependencies...");
            File error = new File(root.getPath() + "/error");
            File output = new File(root.getPath() + "/output");

            ProcessBuilder prB = new ProcessBuilder("gradle", "dependencies");
            prB.directory(root);
            prB.redirectError(error);
            prB.redirectOutput(output);

            prB.start().waitFor();
            System.out.println("Done.");

            System.out.println("Preparing tests with " + testJarTask + "... ");
            prB = new ProcessBuilder("gradle", testJarTask);
            prB.directory(root);
            prB.redirectError(error);
            prB.redirectOutput(output);

            prB.start().waitFor();
            System.out.println("Done.");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
>>>>>>> master
    }
}
