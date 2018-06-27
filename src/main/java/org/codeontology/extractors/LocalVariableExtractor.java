package org.codeontology.extractors;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.code.CtLocalVariable;

<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
public class LocalVariableWrapper extends Wrapper<CtLocalVariable<?>> {
=======
public class LocalVariableExtractor extends TypedElementExtractor<CtLocalVariable<?>> {
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java

    private ExecutableExtractor<?> parent;

    public LocalVariableExtractor(CtLocalVariable<?> variable) {
        super(variable);
    }

    @Override
    public void extract() {
        tagType();
        tagName();
        tagJavaType();
        tagDeclaredBy();
    }

    @Override
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
    public String getRelativeURI() {
=======
    protected String getRelativeURI() {
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
        return getParent().getRelativeURI() + SEPARATOR + getElement().getSimpleName();
    }

    @Override
    protected RDFNode getType() {
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
        return Ontology.VARIABLE_CLASS;
    }

    public void tagDeclaredBy() {
        new DeclaredByTagger(this).tagDeclaredBy();
=======
        return Ontology.getLocalVariableClass();
    }

    public void tagDeclaredBy() {
        addStatement(Ontology.getDeclaredByProperty(), getParent().getResource());
    }

    public ExecutableExtractor<?> getParent() {
        return parent;
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
    }

    public void setParent(ExecutableExtractor<?> parent) {
        this.parent = parent;
    }
}
