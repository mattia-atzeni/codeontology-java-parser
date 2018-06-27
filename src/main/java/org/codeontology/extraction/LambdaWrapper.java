package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.code.CtLambda;

<<<<<<< HEAD
public class LambdaWrapper extends AbstractWrapper<CtLambda<?>> {
=======
public class LambdaWrapper extends Wrapper<CtLambda<?>> {
    private static final String TAG = "lambda";
>>>>>>> master

    public LambdaWrapper(CtLambda<?> lambda) {
        super(lambda);
    }

    @Override
    public void extract() {
        tagType();
        tagSourceCode();
        tagFunctionalImplements();
    }

    private void tagFunctionalImplements() {
        Wrapper<?> implementedType = getFactory().wrap(getElement().getType());
        implementedType.setParent(this.getParent());
<<<<<<< HEAD
        getLogger().addTriple(this, Ontology.IMPLEMENTS_PROPERTY, implementedType);
        implementedType.follow();
    }

    @Override
    public String buildRelativeURI() {
        return getParent().getRelativeURI() + SEPARATOR + getElement().getSimpleName() + SEPARATOR +
=======
        if (!implementedType.isDeclarationAvailable()) {
            implementedType.extract();
        }
        getLogger().addTriple(this, Ontology.IMPLEMENTS_PROPERTY, implementedType);
    }

    @Override
    public String getRelativeURI() {
        return getParent().getRelativeURI() + SEPARATOR + TAG + SEPARATOR +
>>>>>>> master
                getElement().getType().getQualifiedName();
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD
        return Ontology.LAMBDA_ENTITY;
=======
        return Ontology.LAMBDA_CLASS;
>>>>>>> master
    }
}
