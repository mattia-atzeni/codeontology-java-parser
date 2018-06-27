package org.codeontology.buildsystems.maven;

import org.apache.maven.project.MavenProject;
import org.codeontology.CodeOntology;
<<<<<<< HEAD
import org.codeontology.buildsystems.ClasspathLoader;
=======
>>>>>>> master
import org.codeontology.buildsystems.DependenciesLoader;

import java.io.File;
import java.io.IOException;
<<<<<<< HEAD
import java.util.Scanner;
=======
>>>>>>> master
import java.util.Set;

public class MavenLoader extends DependenciesLoader {
    private static final String PATH_TO_DEPENDENCIES = "/target/dependency/";
<<<<<<< HEAD
    private final File output;
    private final File error;
    private MavenProject project;
    private static boolean m2Loaded = false;
=======
    private MavenProject project;
>>>>>>> master

    public MavenLoader(File root) {
        project = new MavenProject();
        File pom = new File(root.getAbsolutePath() + "/pom.xml");
        project.setFile(pom);
<<<<<<< HEAD
        error = new File(project.getBasedir() + "/error");
        output = new File(project.getBasedir() + "/output");
=======
    }

    public MavenLoader(String path) {
        this(new File(path));
>>>>>>> master
    }

    @Override
    public void loadDependencies() {
<<<<<<< HEAD
        System.out.println("Loading dependencies with Maven");
        try {
            MavenModulesHandler modulesHandler = new MavenModulesHandler(project.getBasedir());
            if (CodeOntology.downloadDependencies()) {
                modulesHandler.setUp();
                downloadDependencies();
            }

            ProcessBuilder builder = new ProcessBuilder("mvn", "dependency:build-classpath", "-Dmdep.outputFile=.cp");
            builder.directory(project.getBasedir());
            builder.redirectError(error);
            builder.redirectOutput(output);
            int exitStatus = builder.start().waitFor();

            if (exitStatus == 0) {
                File classpath = new File(project.getBasedir() + "/.cp");
                Scanner reader = new Scanner(classpath);
                reader.useDelimiter("\\Z");
                if (reader.hasNext()) {
                    getLoader().loadClasspath(reader.next());
                }
                reader.close();
                classpath.deleteOnExit();
            } else {
                getLoader().loadAllJars(project.getBasedir());
                if (!m2Loaded) {
                    ClasspathLoader loader = getLoader();
                    loader.lock();
                    loader.loadAllJars(System.getProperty("user.home") + "/.m2");
                    loader.release();
                    m2Loaded = true;
                }
            }

=======
        try {
            MavenModulesHandler modulesHandler = new MavenModulesHandler(project.getBasedir());
            modulesHandler.setUp();
            if (CodeOntology.getDownloadDependenciesFlag()) {
                downloadDependencies();
            }

            getLoader().loadAllJars(project.getBasedir());
            getLoader().loadAllJars(System.getProperty("user.home") + "/.m2");
>>>>>>> master

            Set<File> modules = modulesHandler.findModules();
            for (File module : modules) {
                System.out.println("Running on module " + module.getPath());
                getFactory().getLoader(module).loadDependencies();
            }
<<<<<<< HEAD
        } catch (IOException | InterruptedException e) {
=======
        } catch (IOException e) {
>>>>>>> master
            throw new RuntimeException(e);
        }
    }

    /**
     * Get dependencies for maven project in
     * folder {@code projectRoot}, and save them in
     * projectRoot/target/dependency/.
     */
    public void downloadDependencies() {
        try {
<<<<<<< HEAD
=======
            File error = new File(project.getBasedir() + "/error");
            File output = new File(project.getBasedir() + "/output");
>>>>>>> master
            File downloadDirectory = new File(project.getBasedir() + PATH_TO_DEPENDENCIES);

            if (!downloadDirectory.exists()) {
                if (!downloadDirectory.mkdirs()) {
                    throw new IOException("Could not create download directory for dependencies");
                }
            }

            System.out.println("Downloading dependencies...");
<<<<<<< HEAD
            ProcessBuilder builder = new ProcessBuilder("mvn", "dependency:copy-dependencies");
            builder.directory(project.getBasedir());
            builder.redirectError(error);
            builder.redirectOutput(output);

            builder.start().waitFor();
=======
            ProcessBuilder prB = new ProcessBuilder("mvn", "dependency:copy-dependencies");
            prB.directory(project.getBasedir());
            prB.redirectError(error);
            prB.redirectOutput(output);

            prB.start().waitFor();
>>>>>>> master
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
