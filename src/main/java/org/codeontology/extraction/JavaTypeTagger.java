package org.codeontology.extraction;

import org.codeontology.Ontology;
<<<<<<< HEAD

public class JavaTypeTagger {

    private TypedElementWrapper<?> typedElement;
    private TypeWrapper<?> type;

    public JavaTypeTagger(TypedElementWrapper<?> typedElement) {
        this.typedElement = typedElement;
        this.type = typedElement.getJavaType();
=======
import spoon.reflect.declaration.CtTypedElement;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtVariableReference;

public class JavaTypeTagger {

    private Wrapper<? extends CtTypedElement> typedElement;
    private TypeWrapper<?> type;

    public JavaTypeTagger(Wrapper<? extends CtTypedElement> typedElement) {
        this.typedElement = typedElement;
        if (typedElement.getReference() instanceof CtVariableReference) {
            CtTypeReference reference = ((CtVariableReference) typedElement.getReference()).getType();
            type = WrapperFactory.getInstance().wrap(reference);
        } else {
            type = WrapperFactory.getInstance().wrap((CtTypeReference<?>) typedElement.getReference());
        }
>>>>>>> master
    }

    private void tagJavaType() {
        RDFLogger.getInstance().addTriple(typedElement, Ontology.JAVA_TYPE_PROPERTY, type);
<<<<<<< HEAD
        type.follow();
=======
        if (!type.isDeclarationAvailable()) {
            type.extract();
        }
>>>>>>> master
    }

    public void tagJavaType(Wrapper<?> parent) {
        type.setParent(parent);
        tagJavaType();
    }
}
