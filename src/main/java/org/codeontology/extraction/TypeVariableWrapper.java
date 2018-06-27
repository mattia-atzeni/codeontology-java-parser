package org.codeontology.extraction;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.code.CtNewClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ParentNotInitializedException;
<<<<<<< HEAD
=======
=======
import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtType;
>>>>>>> master
>>>>>>> master
import spoon.reflect.reference.*;

import java.lang.reflect.Executable;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class TypeVariableWrapper extends TypeWrapper<CtType<?>> {

    private int position;
    private List<CtTypeReference<?>> bounds;

    public TypeVariableWrapper(CtTypeReference<?> reference) {
        super(reference);
        setBounds();
    }

    private void setBounds() {
        bounds = new ArrayList<>();
        CtTypeParameterReference reference = (CtTypeParameterReference) getReference();
        CtTypeReference<?> boundingType = reference.getBoundingType();
        if (boundingType instanceof CtIntersectionTypeReference) {
            bounds = boundingType.asCtIntersectionTypeReference().getBounds();
        } else if (boundingType != null) {
            bounds.add(boundingType);
        }
    }

    @Override
    public void extract() {
        tagType();
        tagBounds();
        tagPosition();
    }

    private void tagPosition() {
        getLogger().addTriple(this, Ontology.POSITION_PROPERTY, getModel().createTypedLiteral(position));
    }

    public void tagBounds() {
        for (CtTypeReference bound : bounds) {
            tagBound(bound);
        }
    }

    private void tagBound(CtTypeReference boundReference) {
        TypeWrapper<?> bound = getFactory().wrap(boundReference);
        bound.setParent(this.getParent());
        if (((CtTypeParameterReference) getReference()).isUpper()) {
            getLogger().addTriple(this, Ontology.EXTENDS_PROPERTY, bound);
        } else {
            getLogger().addTriple(this, Ontology.SUPER_PROPERTY, bound);
        }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
        bound.follow();
    }

    @Override
    public String buildRelativeURI() {
        if (isWildcard()) {
            return wildcardURI();
        }

        if (getParent() != null) {
            return getReference().getQualifiedName() + ":" + getParent().getRelativeURI();
        } else {
            return null;
        }
<<<<<<< HEAD
=======
=======
        if (!bound.isDeclarationAvailable()) {
            bound.extract();
        }
    }

    @Override
    public String getRelativeURI() {
        if (isWildcard()) {
            return wildcardURI();
        }
        return getReference().getQualifiedName() + ":" + getParent().getRelativeURI();
>>>>>>> master
>>>>>>> master
    }

    private String wildcardURI() {
        String clause;
        if (((CtTypeParameterReference) getReference()).isUpper()) {
            clause = "extends";
        } else {
            clause = "super";
        }

        String uri = "?";
        String separator = "_";

        if (bounds.size() > 0) {
            uri = uri + separator + clause;
        }

        for (CtTypeReference bound : bounds) {
            TypeWrapper<?> wrapper = getFactory().wrap(bound);
            wrapper.setParent(getParent());
            uri = uri + separator + wrapper.getRelativeURI();
        }

        return uri;
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
        if (isWildcard()) {
            return Ontology.WILDCARD_ENTITY;
        } else {
            return Ontology.TYPE_VARIABLE_ENTITY;
        }
<<<<<<< HEAD
=======
=======
        return Ontology.TYPE_VARIABLE_CLASS;
>>>>>>> master
>>>>>>> master
    }

    public void setPosition(int position) {
        this.position = position;
    }

<<<<<<< HEAD
    private Wrapper<?> findParent(ExecutableWrapper<?> executable) {
=======
<<<<<<< HEAD
    private Wrapper<?> findParent(ExecutableWrapper<?> executable) {
=======
    private void setParent(CtTypeReference reference) {
        super.setParent(getFactory().wrap(reference));
    }

    private void setParent(CtType type) {
        setParent(type.getReference());
    }

    private void setParent(Class<?> clazz) {
        CtTypeReference<?> parent = getReference().getFactory().Class().createReference(clazz);
        setParent(parent);
    }

    private void findAndSetParent(ExecutableWrapper<?> executable) {
>>>>>>> master
>>>>>>> master
        CtExecutableReference<?> executableReference = (CtExecutableReference<?>) executable.getReference();

        if (executable.isDeclarationAvailable()) {
            List<CtTypeReference<?>> parameters = new TypeVariableList(executable.getElement().getFormalTypeParameters());
            if (parameters.contains(getReference())) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
                return executable;
            } else {
                return findParent(executableReference.getDeclaringType());
            }
        } else {
            return findParent(executableReference);
        }
    }

    private Wrapper<?> findParent(CtExecutableReference<?> executableReference) {
        if (executableReference.getDeclaration() != null) {
            return findParent(getFactory().wrap(executableReference));
        } else if (isWildcard()) {
            return getFactory().wrap(executableReference);
        } else {
            Executable executable = ReflectionFactory.getInstance().createActualExecutable(executableReference);
            if (executable == null) {
                return null;
            }
<<<<<<< HEAD
=======
=======
                super.setParent(executable);
            } else {
                findAndSetParent(executableReference.getDeclaringType());
            }
        } else {
            findAndSetParent(executableReference);
        }
    }

    private void findAndSetParent(CtExecutableReference<?> executableReference) {
        if (executableReference.getDeclaration() != null) {
            findAndSetParent(getFactory().wrap(executableReference));
        } else if (isWildcard()) {
            super.setParent(getFactory().wrap(executableReference));
        } else {
            Executable executable = ReflectionFactory.getInstance().createActualExecutable(executableReference);
>>>>>>> master
>>>>>>> master
            TypeVariable<?>[] typeParameters = executable.getTypeParameters();
            Class<?> declaringClass = executable.getDeclaringClass();

            for (TypeVariable current : typeParameters) {
                if (current.getName().equals(getReference().getQualifiedName())) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
                    return getFactory().wrap(executableReference);
                }
            }

            return findParent(declaringClass);
<<<<<<< HEAD
=======
=======
                    super.setParent(getFactory().wrap(executableReference));
                    return;
                }
            }

            findAndSetParent(declaringClass);
>>>>>>> master
>>>>>>> master
        }
    }


<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
    private Wrapper<?> findParent(Class<?> clazz) {
        if (clazz != null) {
            TypeVariable<?>[] typeParameters = clazz.getTypeParameters();

            for (TypeVariable variable : typeParameters) {
                if (variable.getName().equals(getReference().getQualifiedName())) {
                    CtTypeReference<?> reference = ReflectionFactory.getInstance().createTypeReference(clazz);
                    return getFactory().wrap(reference);
                }
            }

            if (clazz.isAnonymousClass()) {
                try {
                    CtExecutableReference<?> reference = ReflectionFactory.getInstance().createMethod(clazz.getEnclosingMethod());
                    return findParent(reference);
                } catch (Exception | Error e) {
                    return null;
                }
            } else {
                return findParent(clazz.getDeclaringClass());
            }
        }

        return null;
    }

    private Wrapper<?> findParent(CtTypeReference<?> reference) {
        if (reference != null) {
            if (isWildcard()) {
                return getFactory().wrap(reference);
            } else if (reference.getDeclaration() != null) {
                return findParent(reference.getDeclaration());
            } else {
                return findParent(reference.getActualClass());
            }
        }

        return null;
    }

    private Wrapper<?> findParent(CtType type) {
        if (type != null) {
            List<CtTypeReference<?>> formalTypes = new TypeVariableList(type.getFormalTypeParameters());
            if (formalTypes.contains(getReference())) {
                return getFactory().wrap(type);
            } else {
                CtElement parent;
                try {
                    parent = type.getParent();
                } catch (ParentNotInitializedException e) {
                    parent = null;
                }

                if (parent instanceof CtNewClass<?>) {
                    return findParent(parent.getParent(CtExecutable.class).getReference());
                } else {
                    return findParent(type.getDeclaringType());
                }
            }
        }

        return null;
    }

    @Override
    public void setParent(Wrapper<?> context) {
        CtReference reference = context.getReference();
        String simpleName = getReference().getSimpleName();

        Wrapper<?> parent = TypeVariableCache.getInstance().getParent(simpleName, context);
        if (parent == null) {
            if (reference instanceof CtTypeReference<?>) {
                parent = findParent((CtTypeReference) reference);
            } else if (reference instanceof CtExecutableReference) {
                parent = findParent((CtExecutableReference) reference);
            }
            if (parent != null) {
                parent.setParent(context);
                super.setParent(parent);
                TypeVariableCache.getInstance().putParent(simpleName, context, parent);
            }
        } else {
            super.setParent(parent);
<<<<<<< HEAD
=======
=======
    private void findAndSetParent(Class<?> clazz) {
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();

        for (TypeVariable variable : typeParameters) {
            if (variable.getName().equals(getReference().getQualifiedName())) {
                setParent(clazz);
                return;
            }
        }

        findAndSetParent(clazz.getDeclaringClass());
    }

    private void findAndSetParent(CtTypeReference<?> reference) {
        if (reference.getDeclaration() != null) {
            findAndSetParent(reference.getDeclaration());
        } else {
            findAndSetParent(reference.getActualClass());
        }
    }

    private void findAndSetParent(CtType type) {
        if (type != null) {
            List<CtTypeReference<?>> formalTypes = new TypeVariableList(type.getFormalTypeParameters());
            while (!formalTypes.contains(getReference())) {
                type = type.getDeclaringType();
                formalTypes = new TypeVariableList(type.getFormalTypeParameters());
            }
            setParent(type);
        }
    }

    @Override
    public void setParent(Wrapper<?> wrapper) {
        CtReference reference = wrapper.getReference();
        if (reference instanceof CtTypeReference<?>) {
            findAndSetParent((CtTypeReference) reference);
        } else if (reference instanceof CtExecutableReference) {
            findAndSetParent((CtExecutableReference) reference);
>>>>>>> master
>>>>>>> master
        }
    }

    public boolean isWildcard() {
        return getReference().getSimpleName().equals("?");
    }
}

class TypeVariableList extends ArrayList<CtTypeReference<?>> {

    public TypeVariableList(List<CtTypeReference<?>> parameters) {
        super(parameters);
    }

    @Override
    public boolean contains(Object object) {
        if (!(object instanceof CtTypeParameterReference)) {
            return super.contains(object);
        }
        CtTypeReference<?> parameter = (CtTypeReference<?>) object;

        if (parameter.getSimpleName().equals("?")) {
            return true;
        }

        for (CtTypeReference currentParameter : this) {
            if (currentParameter.getQualifiedName().equals(parameter.getQualifiedName())) {
                return true;
            }
        }
        return false;
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
}

class TypeVariableCache {

    private static TypeVariableCache instance;
    private Table<String, Wrapper<?>, Wrapper<?>> table;
    private int size;
    private static final int ROWS = 16;
    private static final int COLS = 48;
    private static final int MAX_SIZE = (ROWS * COLS) / 2;

    private TypeVariableCache() {
        init();
    }

    public static TypeVariableCache getInstance() {
        if (instance == null) {
            instance = new TypeVariableCache();
        }

        return instance;
    }

    public Wrapper<?> getParent(String name, Wrapper<?> context) {
        return table.get(name, context);
    }

    public void putParent(String name, Wrapper<?> context, Wrapper<?> parent) {
        handleSize();
        table.put(name, context, parent);
    }

    private void handleSize() {
        size++;
        if (size > MAX_SIZE) {
            init();
        }
    }

    private void init() {
        table = HashBasedTable.create(ROWS, COLS);
        size = 0;
    }

<<<<<<< HEAD
=======
=======
>>>>>>> master
>>>>>>> master
}