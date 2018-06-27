package org.codeontology.extraction;

import org.codeontology.Ontology;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
import spoon.reflect.reference.CtTypeReference;

import java.util.ArrayList;
import java.util.List;

public class FormalTypeParametersTagger {
    private GenericDeclarationWrapper<?> genericDeclaration;

    public FormalTypeParametersTagger(GenericDeclarationWrapper<?> genericDeclaration) {
<<<<<<< HEAD
=======
=======
import spoon.reflect.declaration.CtGenericElement;
import spoon.reflect.reference.CtTypeReference;

import java.util.List;

public class FormalTypeParametersTagger {
    private Wrapper<? extends CtGenericElement> genericDeclaration;

    public FormalTypeParametersTagger(Wrapper<? extends CtGenericElement> genericDeclaration) {
>>>>>>> master
>>>>>>> master
        this.genericDeclaration = genericDeclaration;
    }

    public void tagFormalTypeParameters() {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
        List<TypeVariableWrapper> parameters = genericDeclaration.getFormalTypeParameters();
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            TypeVariableWrapper typeVariable = parameters.get(i);
<<<<<<< HEAD
=======
=======
        List<CtTypeReference<?>> parameters = genericDeclaration.getElement().getFormalTypeParameters();
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            CtTypeReference<?> current = parameters.get(i);
            TypeVariableWrapper typeVariable = (TypeVariableWrapper) WrapperFactory.getInstance().wrap(current);
>>>>>>> master
>>>>>>> master
            typeVariable.setParent(genericDeclaration);
            typeVariable.setPosition(i);
            RDFLogger.getInstance().addTriple(genericDeclaration, Ontology.FORMAL_TYPE_PARAMETER_PROPERTY, typeVariable);
            typeVariable.extract();
        }
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master

    public static List<TypeVariableWrapper> formalTypeParametersOf(GenericDeclarationWrapper<?> genericDeclaration) {
        List<CtTypeReference<?>> parameters = genericDeclaration.getElement().getFormalTypeParameters();
        List<TypeVariableWrapper> typeVariables = new ArrayList<>();

        for (CtTypeReference parameter : parameters) {
            Wrapper<?> wrapper = WrapperFactory.getInstance().wrap(parameter);
            if (wrapper instanceof TypeVariableWrapper) {
                typeVariables.add((TypeVariableWrapper) wrapper);
            }
        }

        return typeVariables;
    }
<<<<<<< HEAD
=======
=======
>>>>>>> master
>>>>>>> master
}
