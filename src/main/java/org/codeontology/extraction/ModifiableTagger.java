package org.codeontology.extraction;

<<<<<<< HEAD
import com.hp.hpl.jena.rdf.model.Property;
import org.codeontology.Ontology;

import java.util.List;

public class ModifiableTagger {

    ModifiableWrapper<?> modifiable;

    public ModifiableTagger(ModifiableWrapper modifiable) {
        this.modifiable = modifiable;
    }

    public void tagModifiers() {
        List<Modifier> modifiers = modifiable.getModifiers();

        for (Modifier modifier : modifiers) {
            Property modifierProperty;
            if (modifier.isAccessModifier()) {
                modifierProperty = Ontology.VISIBILITY_PROPERTY;
            } else {
                modifierProperty = Ontology.MODIFIER_PROPERTY;
            }

            RDFLogger.getInstance().addTriple(modifiable, modifierProperty, modifier.getIndividual());
        }
    }
=======
import com.hp.hpl.jena.rdf.model.Resource;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtModifiable;
import spoon.reflect.declaration.ModifierKind;

import java.util.Set;

public class ModifiableTagger {

    Wrapper<? extends CtModifiable> wrapper;

    public ModifiableTagger(Wrapper<? extends CtModifiable> wrapper) {
        this.wrapper = wrapper;
    }

    private void tagVisibility() {
        ModifierKind modifier = wrapper.getElement().getVisibility();
        Resource encapsulation;

        if (modifier == null) {
            encapsulation = Ontology.DEFAULT_INDIVIDUAL;
        } else {
            switch (modifier) {
                case PUBLIC:
                    encapsulation = Ontology.PUBLIC_INDIVIDUAL;
                    break;
                case PRIVATE:
                    encapsulation = Ontology.PRIVATE_INDIVIDUAL;
                    break;
                case PROTECTED:
                    encapsulation = Ontology.PROTECTED_INDIVIDUAL;
                    break;
                default:
                    encapsulation = Ontology.DEFAULT_INDIVIDUAL;
                    break;
            }
        }

        RDFLogger.getInstance().addTriple(wrapper, Ontology.VISIBILITY_PROPERTY, encapsulation);
    }

    private void tagModifier() {
        Set<ModifierKind> modifiers = wrapper.getElement().getModifiers();
        Resource modifier;

        for (ModifierKind current : modifiers) {
            modifier = null;
            switch (current) {
                case ABSTRACT:
                    modifier = Ontology.ABSTRACT_INDIVIDUAL;
                    break;
                case FINAL:
                    modifier = Ontology.FINAL_INDIVIDUAL;
                    break;
                case STATIC:
                    modifier = Ontology.STATIC_INDIVIDUAL;
                    break;
                case SYNCHRONIZED:
                    modifier = Ontology.SYNCHRONIZED_INDIVIDUAL;
                    break;
                case TRANSIENT:
                    break;
                case VOLATILE:
                    modifier = Ontology.VOLATILE_INDIVIDUAL;
                    break;
                case NATIVE:
                    break;
                case STRICTFP:
                    break;
                default:
                    break;
            }
            if (modifier != null) {
                RDFLogger.getInstance().addTriple(wrapper, Ontology.MODIFIER_PROPERTY, modifier);
            }
        }
    }

    public void tagModifiers() {
        tagVisibility();
        tagModifier();
    }
>>>>>>> master
}
