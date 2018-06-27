package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.CodeOntology;
import org.codeontology.Ontology;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
import spoon.reflect.reference.CtPackageReference;
import spoon.reflect.reference.CtTypeReference;

import java.util.HashSet;
import java.util.Set;


public class PackageWrapper extends AbstractWrapper<CtPackage> {

    private Set<TypeWrapper<?>> types;
<<<<<<< HEAD
=======
=======

import java.util.Set;


public class PackageWrapper extends Wrapper<CtPackage> {
>>>>>>> master
>>>>>>> master

    public PackageWrapper(CtPackage pack) {
        super(pack);
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
    public PackageWrapper(CtPackageReference pack) {
        super(pack);
    }

    @Override
    public String buildRelativeURI() {
        String relativeURI = getPackageName();
<<<<<<< HEAD
=======
=======
    @Override
    public String getRelativeURI() {
        String relativeURI = getElement().getQualifiedName();
>>>>>>> master
>>>>>>> master
        return relativeURI.replace(" ", SEPARATOR);
    }

    @Override
    protected RDFNode getType() {
<<<<<<< HEAD
        return Ontology.PACKAGE_ENTITY;
=======
<<<<<<< HEAD
        return Ontology.PACKAGE_ENTITY;
=======
        return Ontology.PACKAGE_CLASS;
>>>>>>> master
>>>>>>> master
    }

    @Override
    public void extract() {
<<<<<<< HEAD
        Set<TypeWrapper<?>> types = getTypes();
=======
<<<<<<< HEAD
        Set<TypeWrapper<?>> types = getTypes();
=======
        Set<CtType<?>> types = getElement().getTypes();
>>>>>>> master
>>>>>>> master

        if (types.isEmpty()) {
            return;
        }

        tagType();
        tagName();
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> master
        tagPackageOf();

        if (isDeclarationAvailable()) {
            tagComment();
        }
    }

    public void tagPackageOf() {
        for (TypeWrapper type : types) {
            getLogger().addTriple(this, Ontology.PACKAGE_OF_PROPERTY, type);
            if (CodeOntology.verboseMode()) {
                System.out.println("Running on " + type.getReference().getQualifiedName());
            }
            type.extract();
        }
    }

    public Set<TypeWrapper<?>> getTypes() {
        if (types == null) {
            setTypes();
        }
        return types;
    }

     private void setTypes() {
         types = new HashSet<>();
         if (isDeclarationAvailable()) {
             Set<CtType<?>> ctTypes = getElement().getTypes();
             for (CtType current : ctTypes) {
                 types.add(getFactory().wrap(current));
             }
         }
     }

    public void setTypes(Set<Class<?>> types) {
        this.types = new HashSet<>();
        for (Class<?> type : types) {
            CtTypeReference<?> reference = ReflectionFactory.getInstance().createTypeReference(type);
            this.types.add(getFactory().wrap(reference));
        }
    }

    private String getPackageName() {
        if (isDeclarationAvailable()) {
            return getElement().getQualifiedName();
        } else {
            return ((CtPackageReference) getReference()).getActualPackage().getName();
<<<<<<< HEAD
=======
=======
        tagComment();
        tagPackageOf(types);
    }

    public void tagPackageOf(Set<CtType<?>> types) {
        for (CtType<?> current : types) {
            TypeWrapper<?> wrapper = getFactory().wrap(current);
            getLogger().addTriple(this, Ontology.PACKAGE_OF_PROPERTY, wrapper.getResource());
            if (CodeOntology.verboseMode()) {
                System.out.println("Extracting triples for " + current.getQualifiedName());
            }
            wrapper.extract();
>>>>>>> master
>>>>>>> master
        }
    }
}