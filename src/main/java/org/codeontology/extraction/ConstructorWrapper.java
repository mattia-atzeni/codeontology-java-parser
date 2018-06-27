package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.reference.CtExecutableReference;

public class ConstructorWrapper extends ExecutableWrapper<CtConstructor<?>> {

    public ConstructorWrapper(CtConstructor<?> constructor) {
        super(constructor);
    }

    public ConstructorWrapper(CtExecutableReference<?> reference) {
        super(reference);
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD:src/main/java/org/codeontology/extraction/ConstructorWrapper.java
        return Ontology.CONSTRUCTOR_ENTITY;
=======
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/ConstructorWrapper.java
        return Ontology.CONSTRUCTOR_CLASS;
=======
        return Ontology.getConstructorIndividual();
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/ConstructorExtractor.java
>>>>>>> master:src/main/java/org/codeontology/extractors/ConstructorExtractor.java
    }
}
