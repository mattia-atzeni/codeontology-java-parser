package org.codeontology.buildsystems;

<<<<<<< HEAD
import java.io.File;
import java.util.Set;

=======
>>>>>>> master
public abstract class DependenciesLoader {

    private ClasspathLoader loader = ClasspathLoader.getInstance();

    public abstract void loadDependencies();

<<<<<<< HEAD
    public Set<File> getJarsLoaded() {
        return getLoader().getJarsLoaded();
    }

=======
>>>>>>> master
    public void loadClasspath(String classpath) {
        loader.loadClasspath(classpath);
    }

    public ClasspathLoader getLoader() {
        return loader;
    }

    public LoaderFactory getFactory() {
        return LoaderFactory.getInstance();
    }
}
