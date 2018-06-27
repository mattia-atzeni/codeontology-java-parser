package org.codeontology.extractors;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtAnnotationType;
import spoon.reflect.reference.CtTypeReference;

public class AnnotationExtractor extends TypeExtractor<CtAnnotationType<?>> {

    public AnnotationExtractor(CtAnnotationType<?> type) {
        super(type);
    }

    public AnnotationExtractor(CtTypeReference<?> reference) {
        super(reference);
    }

    @Override
    protected RDFNode getType() {
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/AnnotationWrapper.java
        return Ontology.ANNOTATION_CLASS;
=======
        return Ontology.getAnnotationIndividual();
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/AnnotationExtractor.java
    }

    @Override
    public void extract() {
        tagType();
        tagName();
        if (isDeclarationAvailable()) {
            tagComment();
            tagSourceCode();
        }
    }
}
