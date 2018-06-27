package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.reference.CtTypeReference;

<<<<<<< HEAD:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
public class LocalVariableWrapper extends AbstractWrapper<CtLocalVariable<?>> implements MemberWrapper<CtLocalVariable<?>>, TypedElementWrapper<CtLocalVariable<?>> {
=======
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
public class LocalVariableWrapper extends Wrapper<CtLocalVariable<?>> {
=======
public class LocalVariableExtractor extends TypedElementExtractor<CtLocalVariable<?>> {
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
>>>>>>> master:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java

    public LocalVariableWrapper(CtLocalVariable<?> variable) {
        super(variable);
    }

    @Override
    public void extract() {
        tagType();
        tagName();
        tagJavaType();
        tagDeclaringElement();
    }

    @Override
<<<<<<< HEAD:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
    public String buildRelativeURI() {
=======
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
    public String getRelativeURI() {
=======
    protected String getRelativeURI() {
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
>>>>>>> master:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
        return getParent().getRelativeURI() + SEPARATOR + getElement().getSimpleName();
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
        return Ontology.VARIABLE_ENTITY;
=======
<<<<<<< Updated upstream:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
        return Ontology.VARIABLE_CLASS;
    }

    public void tagDeclaredBy() {
        new DeclaredByTagger(this).tagDeclaredBy();
=======
        return Ontology.getLocalVariableClass();
>>>>>>> master:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
    }

    @Override
    public Wrapper<?> getDeclaringElement() {
        return getParent();
    }

<<<<<<< HEAD:src/main/java/org/codeontology/extraction/LocalVariableWrapper.java
    public void tagDeclaringElement() {
        new DeclaringElementTagger(this).tagDeclaredBy();
    }

    @Override
    public TypeWrapper<?> getJavaType() {
        CtTypeReference<?> type = getElement().getType();
        return getFactory().wrap(type);
=======
    public ExecutableExtractor<?> getParent() {
        return parent;
>>>>>>> Stashed changes:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
>>>>>>> master:src/main/java/org/codeontology/extractors/LocalVariableExtractor.java
    }

    public void tagJavaType() {
        new JavaTypeTagger(this).tagJavaType(this.getParent());
    }
}
