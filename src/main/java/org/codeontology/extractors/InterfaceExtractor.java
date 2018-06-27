package org.codeontology.extractors;


import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;

<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/InterfaceWrapper.java
public class InterfaceWrapper extends TypeWrapper<CtInterface<?>> {
=======
import java.util.List;
import java.util.Set;

public class InterfaceExtractor extends TypeExtractor<CtInterface<?>> {
    public InterfaceExtractor(CtInterface<?> clazz) {
        super(clazz);
    }
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/InterfaceExtractor.java

    public InterfaceExtractor(CtTypeReference<?> reference) {
        super(reference);
    }

    @Override
    protected RDFNode getType() {
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/InterfaceWrapper.java
        return Ontology.INTERFACE_CLASS;
=======
        return Ontology.getInterfaceIndividual();
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/InterfaceExtractor.java
    }

    @Override
    public void extract() {
        tagInterface();
        writeRDF();
    }

    protected void tagInterface() {
        tagType();
        tagName();
        if (isDeclarationAvailable()) {
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/InterfaceWrapper.java
            tagAnnotations();
=======
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/InterfaceExtractor.java
            tagSuperInterfaces();
            tagFields();
            tagMethods();
            tagSourceCode();
            tagComment();
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/InterfaceWrapper.java
            tagModifiers();
        }
    }

    public void tagSuperInterfaces() {
        tagSuperInterfaces(Ontology.EXTENDS_PROPERTY);
    }

    public void tagModifiers() {
        new ModifiableTagger(this).tagModifiers();
    }
=======
        }
    }
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/InterfaceExtractor.java
}
