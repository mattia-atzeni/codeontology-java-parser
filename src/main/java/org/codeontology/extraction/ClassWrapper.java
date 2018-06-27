package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
<<<<<<< HEAD
import org.codeontology.CodeOntology;
=======
>>>>>>> master
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtType;
<<<<<<< HEAD
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClassWrapper<T> extends TypeWrapper<CtClass<T>> implements ModifiableWrapper<CtClass<T>>, GenericDeclarationWrapper<CtClass<T>> {

    private List<ConstructorWrapper> constructors;

    public ClassWrapper(CtClass<T> clazz) {
        super(clazz);
    }
=======
import spoon.reflect.reference.CtTypeReference;

import java.util.Set;

public class ClassWrapper<T> extends TypeWrapper<CtClass<T>> {
>>>>>>> master

    public ClassWrapper(CtTypeReference<?> reference) {
        super(reference);
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD
        return Ontology.CLASS_ENTITY;
=======
        return Ontology.CLASS_CLASS;
>>>>>>> master
    }

    @Override
    public void extract() {
        tagType();
        tagName();
<<<<<<< HEAD
        tagSuperClass();
        tagSuperInterfaces();
        tagModifiers();
        if (isDeclarationAvailable() || CodeOntology.isJarExplorationEnabled()) {
            tagFields();
            tagConstructors();
            tagMethods();
        }
        if (isDeclarationAvailable()) {
            tagAnnotations();
            tagComment();
            tagSourceCode();
            tagNestedTypes();
=======
        if (isDeclarationAvailable()) {
            tagAnnotations();
            tagSuperClass();
            tagSuperInterfaces();
            tagComment();
            tagFields();
            tagConstructors();
            tagMethods();
            tagSourceCode();
            tagNestedTypes();
            tagModifiers();
>>>>>>> master
            tagFormalTypeParameters();
        }
    }

    public void tagSuperClass() {
        CtTypeReference<?> superclass = getReference().getSuperclass();
        if (superclass == null) {
            superclass = ReflectionFactory.getInstance().createTypeReference(Object.class);
        }
        TypeWrapper<?> superClass = getFactory().wrap(superclass);
        superClass.setParent(this);
        getLogger().addTriple(this, Ontology.EXTENDS_PROPERTY, superClass);
<<<<<<< HEAD
        superClass.follow();
=======
        if (!superClass.isDeclarationAvailable()) {
            superClass.extract();
        }
>>>>>>> master
    }

    public void tagSuperInterfaces() {
        tagSuperInterfaces(Ontology.IMPLEMENTS_PROPERTY);
    }

    public void tagConstructors() {
<<<<<<< HEAD
        List<ConstructorWrapper> constructors = getConstructors();

        for (ConstructorWrapper constructor : constructors) {
            constructor.extract();
        }
    }

    public List<ConstructorWrapper> getConstructors() {
        if (constructors == null) {
            setConstructors();
        }

        return constructors;
    }

    private void setConstructors() {
        constructors = new ArrayList<>();

        if (isDeclarationAvailable()) {
            Set<CtConstructor<T>> ctConstructors = getElement().getConstructors();
            for (CtConstructor ctConstructor : ctConstructors) {
                ConstructorWrapper constructor = getFactory().wrap(ctConstructor);
                constructor.setParent(this);
                constructors.add(constructor);
            }
        } else {
            setConstructorsByReflection();
        }
    }

    private void setConstructorsByReflection() {
        try {
            Constructor[] actualConstructors = getReference().getActualClass().getDeclaredConstructors();
            for (Constructor actualConstructor : actualConstructors) {
                CtExecutableReference<?> reference = ReflectionFactory.getInstance().createConstructor(actualConstructor);
                ConstructorWrapper constructor = (ConstructorWrapper) getFactory().wrap(reference);
                constructor.setParent(this);
                constructors.add(constructor);
            }
        } catch (Throwable t) {
            showMemberAccessWarning();
=======
        Set<CtConstructor<T>> constructors = getElement().getConstructors();

        for (CtConstructor<T> constructor : constructors) {
            getFactory().wrap(constructor).extract();
>>>>>>> master
        }
    }

    public void tagNestedTypes() {
        Set<CtType<?>> nestedTypes = getElement().getNestedTypes();
        for (CtType<?> type : nestedTypes) {
            Wrapper wrapper = getFactory().wrap(type);
<<<<<<< HEAD
            getLogger().addTriple(wrapper, Ontology.NESTED_IN_PROPERTY, this);
=======
            getLogger().addTriple(wrapper, Ontology.IS_NESTED_IN_PROPERTY, this.getResource());
>>>>>>> master
            wrapper.extract();
        }
    }

<<<<<<< HEAD
    @Override
    public List<TypeVariableWrapper> getFormalTypeParameters() {
        return FormalTypeParametersTagger.formalTypeParametersOf(this);
=======
    public void tagModifiers() {
        new ModifiableTagger(this).tagModifiers();
>>>>>>> master
    }

    public void tagFormalTypeParameters() {
        new FormalTypeParametersTagger(this).tagFormalTypeParameters();
    }
}
