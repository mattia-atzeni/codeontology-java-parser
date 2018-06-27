package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master

import java.lang.reflect.*;
import java.util.List;

public class MethodWrapper extends ExecutableWrapper<CtMethod<?>> implements GenericDeclarationWrapper<CtMethod<?>> {
<<<<<<< HEAD
=======
=======
import spoon.support.reflect.reference.SpoonClassNotFoundException;

import java.lang.reflect.*;

public class MethodWrapper extends ExecutableWrapper<CtMethod<?>> {
>>>>>>> master
>>>>>>> master
    public MethodWrapper(CtMethod<?> method) {
        super(method);
    }

    public MethodWrapper(CtExecutableReference<?> reference) {
        super(reference);
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD
        return Ontology.METHOD_ENTITY;
=======
<<<<<<< HEAD
        return Ontology.METHOD_ENTITY;
=======
        return Ontology.METHOD_CLASS;
>>>>>>> master
>>>>>>> master
    }

    @Override
    public void extract() {
        super.extract();
        tagReturns();
        if (isDeclarationAvailable()) {
            tagOverrides();
            tagFormalTypeParameters();
        }
    }

    public void tagOverrides() {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
        try {
            CtExecutableReference<?> reference = ((CtExecutableReference<?>) getReference()).getOverridingExecutable();
            if (reference != null) {
                ExecutableWrapper overridingMethod = getFactory().wrap(reference);
                getLogger().addTriple(this, Ontology.OVERRIDES_PROPERTY, overridingMethod);
                overridingMethod.follow();
            }
        } catch (Exception | Error e) {
            // could not get overriding executable
            // we just skip this method
<<<<<<< HEAD
=======
=======
        CtExecutableReference<?> reference = ((CtExecutableReference<?>) getReference()).getOverridingExecutable();
        if (reference != null) {
            ExecutableWrapper overridingMethod = getFactory().wrap(reference);
            getLogger().addTriple(this, Ontology.OVERRIDES_PROPERTY, overridingMethod);

            if (!overridingMethod.isDeclarationAvailable()) {
                overridingMethod.extract();
            }
>>>>>>> master
>>>>>>> master
        }
    }

    public void tagReturns() {
        getLogger().addTriple(this, Ontology.RETURNS_PROPERTY, getReturnType());
    }

    private TypeWrapper getReturnType() {
        TypeWrapper<?> returnType = getGenericReturnType();
        if (returnType != null) {
            return returnType;
        }

        CtTypeReference<?> reference = ((CtExecutableReference<?>) getReference()).getType();
        returnType = getFactory().wrap(reference);
        returnType.setParent(this);
<<<<<<< HEAD
        returnType.follow();
=======
<<<<<<< HEAD
        returnType.follow();
=======
        if (!returnType.isDeclarationAvailable()) {
            returnType.extract();
        }
>>>>>>> master
>>>>>>> master

        return returnType;
    }

    private TypeWrapper getGenericReturnType() {
        TypeWrapper<?> result = null;
        if (!isDeclarationAvailable()) {
            try {
                CtExecutableReference<?> reference = ((CtExecutableReference<?>) getReference());
<<<<<<< HEAD
                Method method = (Method) ReflectionFactory.getInstance().createActualExecutable(reference);
                Type returnType = method.getGenericReturnType();

=======
<<<<<<< HEAD
                Method method = (Method) ReflectionFactory.getInstance().createActualExecutable(reference);
                Type returnType = method.getGenericReturnType();

=======
                Method method = reference.getActualMethod();
                Type returnType = method.getGenericReturnType();


>>>>>>> master
>>>>>>> master
                if (returnType instanceof GenericArrayType ||
                    returnType instanceof TypeVariable<?>  ||
                    returnType instanceof ParameterizedType) {

                    result = getFactory().wrap(returnType);
                    result.setParent(this);
                }
<<<<<<< HEAD

            } catch (Throwable t) {
=======
<<<<<<< HEAD

            } catch (Throwable t) {
=======
            } catch (SpoonClassNotFoundException | NullPointerException e) {
>>>>>>> master
>>>>>>> master
                return null;
            }
        }

        return result;
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
    @Override
    public List<TypeVariableWrapper> getFormalTypeParameters() {
        return FormalTypeParametersTagger.formalTypeParametersOf(this);
    }

    @Override
<<<<<<< HEAD
=======
=======
>>>>>>> master
>>>>>>> master
    public void tagFormalTypeParameters() {
        new FormalTypeParametersTagger(this).tagFormalTypeParameters();
    }
}
