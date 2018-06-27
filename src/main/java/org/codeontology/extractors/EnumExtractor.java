package org.codeontology.extractors;


import org.codeontology.Ontology;
import com.hp.hpl.jena.rdf.model.RDFNode;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.reference.CtTypeReference;

public class EnumExtractor<T extends Enum<?>> extends ClassExtractor<T> {

    public EnumExtractor(CtEnum<T> type) {
        super(type);
    }

    public EnumExtractor(CtTypeReference<?> reference) {
        super(reference);
    }

    @Override
    protected RDFNode getType() {
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/EnumWrapper.java
        return Ontology.ENUM_CLASS;
=======
        return Ontology.getEnumIndividual();
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/EnumExtractor.java
    }
}
