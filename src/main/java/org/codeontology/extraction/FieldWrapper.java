package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;


public class FieldWrapper extends Wrapper<CtField<?>> {

    public FieldWrapper(CtField<?> field) {
        super(field);

    }

    @Override
    public String getRelativeURI() {
        return getFactory().wrap(getElement().getDeclaringType()).getRelativeURI() + SEPARATOR + getElement().getSimpleName();
    }

    @Override
    protected RDFNode getType() {
        return Ontology.FIELD_CLASS;
    }

    @Override
    public void extract() {
        tagName();
        tagType();
        tagComment();
        tagJavaType();
        tagModifiers();
        tagDeclaringType();
        tagAnnotations();
    }

<<<<<<< Updated upstream
    @Override
    public List<Modifier> getModifiers() {
        if (isDeclarationAvailable()) {
            return Modifier.asList(getElement().getModifiers());
        } else {
            try {
                return Modifier.asList(((CtFieldReference<?>) getReference()).getModifiers());
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< HEAD
            } catch (NoClassDefFoundError e) {
=======
            } catch (Exception | Error e) {
>>>>>>> master
=======
            } catch (NoClassDefFoundError e) {
>>>>>>> Stashed changes
=======
            } catch (NoClassDefFoundError e) {
>>>>>>> Stashed changes
                return new ArrayList<>();
            }
        }
=======
    public void tagDeclaringType() {
        new DeclaredByTagger(this).tagDeclaredBy();
>>>>>>> Stashed changes
    }


    public void tagModifiers() {
        new ModifiableTagger(this).tagModifiers();
    }

    public void tagJavaType() {
        CtType<?> declaringType = getElement().getDeclaringType();
        Wrapper<?> parent = getFactory().wrap(declaringType);
        new JavaTypeTagger(this).tagJavaType(parent);
    }
}

