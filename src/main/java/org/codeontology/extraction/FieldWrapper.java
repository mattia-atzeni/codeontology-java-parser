package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtField;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtTypeReference;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class FieldWrapper extends AbstractWrapper<CtField<?>> implements ModifiableWrapper<CtField<?>>, MemberWrapper<CtField<?>>, TypedElementWrapper<CtField<?>> {

    public FieldWrapper(CtField<?> field) {
        super(field);
    }

    public FieldWrapper(CtFieldReference<?> field) {
        super(field);
    }

    @Override
    public String buildRelativeURI() {
        return getDeclaringElement().getRelativeURI() + SEPARATOR + getReference().getSimpleName();
<<<<<<< HEAD
=======
=======
import spoon.reflect.declaration.CtType;


public class FieldWrapper extends Wrapper<CtField<?>> {

    public FieldWrapper(CtField<?> field) {
        super(field);

    }

    @Override
    public String getRelativeURI() {
        return getFactory().wrap(getElement().getDeclaringType()).getRelativeURI() + SEPARATOR + getElement().getSimpleName();
>>>>>>> master
>>>>>>> master
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD
        return Ontology.FIELD_ENTITY;
=======
<<<<<<< HEAD
        return Ontology.FIELD_ENTITY;
=======
        return Ontology.FIELD_CLASS;
>>>>>>> master
>>>>>>> master
    }

    @Override
    public void extract() {
        tagName();
        tagType();
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
        tagDeclaringElement();
        tagJavaType();
        tagModifiers();
        if (isDeclarationAvailable()) {
            tagComment();
            tagAnnotations();
        }
    }

<<<<<<< HEAD
=======
=======
        tagComment();
        tagJavaType();
        tagModifiers();
        tagDeclaringType();
        tagAnnotations();
    }

<<<<<<< Updated upstream
>>>>>>> master
>>>>>>> master
    @Override
    public List<Modifier> getModifiers() {
        if (isDeclarationAvailable()) {
            return Modifier.asList(getElement().getModifiers());
        } else {
            try {
                return Modifier.asList(((CtFieldReference<?>) getReference()).getModifiers());
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
            } catch (NoClassDefFoundError e) {
                return new ArrayList<>();
            }
        }
    }

<<<<<<< HEAD
=======
=======
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


>>>>>>> master
>>>>>>> master
    public void tagModifiers() {
        new ModifiableTagger(this).tagModifiers();
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
    @Override
    public TypeWrapper<?> getJavaType() {
        TypeWrapper<?> type;
        if (isDeclarationAvailable()) {
            type = getFactory().wrap(getElement().getType());
        } else {
            type = getGenericType();
            if (type == null) {
                CtTypeReference<?> typeReference = ((CtFieldReference<?>) getReference()).getType();
                type = getFactory().wrap(typeReference);
            }
        }

        type.setParent(getDeclaringElement());
        return type;
    }

    private TypeWrapper<?> getGenericType() {
        TypeWrapper<?> result = null;
        if (!isDeclarationAvailable()) {
            try {
                CtFieldReference<?> reference = ((CtFieldReference<?>) getReference());
                Field field = (Field) reference.getActualField();
                Type genericType = field.getGenericType();

                if (genericType instanceof GenericArrayType ||
                        genericType instanceof TypeVariable<?>  ||
                        genericType instanceof ParameterizedType) {

                    result = getFactory().wrap(genericType);
                }

            } catch (Throwable t) {
                return null;
            }
        }

        return result;
    }

    public void tagJavaType() {
        new JavaTypeTagger(this).tagJavaType(getDeclaringElement());
    }

    @Override
    public Wrapper<?> getDeclaringElement() {
        if (isDeclarationAvailable()) {
            return getFactory().wrap(getElement().getDeclaringType());
        } else {
            CtFieldReference<?> reference = (CtFieldReference) getReference();
            CtTypeReference<?> declaringType = ReflectionFactory.getInstance().clone(reference.getDeclaringType());
            declaringType.setActualTypeArguments(new ArrayList<>());
            return getFactory().wrap(declaringType);
        }
    }

    @Override
    public void tagDeclaringElement() {
        new DeclaringElementTagger(this).tagDeclaredBy();
    }
<<<<<<< HEAD
}
=======
}
=======
    public void tagJavaType() {
        CtType<?> declaringType = getElement().getDeclaringType();
        Wrapper<?> parent = getFactory().wrap(declaringType);
        new JavaTypeTagger(this).tagJavaType(parent);
    }
}

>>>>>>> master
>>>>>>> master
