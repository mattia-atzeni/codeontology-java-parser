package org.codeontology.buildsystems;

<<<<<<< HEAD
import java.io.File;
import java.util.Set;

=======
<<<<<<< HEAD
import java.io.File;
import java.util.Set;

=======
>>>>>>> master
>>>>>>> master
public abstract class DependenciesLoader {

    private ClasspathLoader loader = ClasspathLoader.getInstance();

    public abstract void loadDependencies();

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
    public Set<File> getJarsLoaded() {
        return getLoader().getJarsLoaded();
    }

<<<<<<< HEAD
=======
=======
>>>>>>> master
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
