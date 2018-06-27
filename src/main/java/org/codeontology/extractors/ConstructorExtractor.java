package org.codeontology.extractors;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.reference.CtExecutableReference;

public class ConstructorExtractor extends ExecutableExtractor<CtConstructor<?>> {

    public ConstructorExtractor(CtConstructor<?> constructor) {
        super(constructor);
    }

    public ConstructorExtractor(CtExecutableReference<?> reference) {
        super(reference);
    }

    @Override
    protected String getRelativeURI() {
        String uri = getReference().toString();
        uri = uri.replaceAll(", |\\(|\\)", SEPARATOR);
        return uri;
    }

    @Override
    protected RDFNode getType() {
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/ConstructorWrapper.java
        return Ontology.CONSTRUCTOR_CLASS;
=======
        return Ontology.getConstructorIndividual();
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/ConstructorExtractor.java
    }
}
