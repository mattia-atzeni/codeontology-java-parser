package org.codeontology.buildsystems;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> master
>>>>>>> master
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class ClasspathLoader {

    private static ClasspathLoader instance;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
    private Set<File> classpath;
    private boolean locked;

    private ClasspathLoader() {
        classpath = new HashSet<>();
        locked = false;
<<<<<<< HEAD
=======
=======

    private ClasspathLoader() {

>>>>>>> master
>>>>>>> master
    }

    public static ClasspathLoader getInstance() {
        if (instance == null) {
            instance = new ClasspathLoader();
        }
        return instance;
    }

    public void load(String path) {
        load(new File(path));
    }

    public void load(File file) {
        if (file.isDirectory()) {
            loadAllJars(file);
            return;
        }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master

        if (file.getPath().endsWith(".jar") && !locked) {
            classpath.add(file);
        }

<<<<<<< HEAD
=======
=======
        
>>>>>>> master
>>>>>>> master
        try {
            load(file.toURI().toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

<<<<<<< HEAD
    private void load(URL url) {
=======
<<<<<<< HEAD
    private void load(URL url) {
=======
    public void load(URL url) {
>>>>>>> master
>>>>>>> master
        URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> clazz = URLClassLoader.class;
        final Class[] PARAMETERS = new Class[]{URL.class};

        try {
            Method method = clazz.getDeclaredMethod("addURL", PARAMETERS);
            method.setAccessible(true);
            method.invoke(loader, url);
        } catch (Throwable t) {
           System.err.println("Error loading " + url.getPath());
        }
    }

    public void loadAllJars(File root) {
        Set<File> jars = new HashSet<>();

        jars.addAll(FileUtils.listFiles(root,
                FileFilterUtils.suffixFileFilter(".jar"),
                TrueFileFilter.INSTANCE));

        for (File jar : jars) {
            load(jar);
        }
    }

<<<<<<< HEAD
    public void loadAllJars(String path) {
=======
<<<<<<< HEAD
    public void loadAllJars(String path) {
=======
    public void loadAllJars(String path) throws IOException {
>>>>>>> master
>>>>>>> master
        loadAllJars(new File(path));
    }

    public void loadClasspath(String classpath) {
        String[] paths = classpath.split(":");
        for (String path : paths) {
            load(path);
        }
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master

    public Set<File> getJarsLoaded() {
        return classpath;
    }

    public void lock() {
        locked = true;
    }

    public void release() {
        locked = false;
    }
<<<<<<< HEAD
=======
=======
>>>>>>> master
>>>>>>> master
}

