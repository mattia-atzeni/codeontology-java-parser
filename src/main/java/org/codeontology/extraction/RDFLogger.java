package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
<<<<<<< HEAD
import com.hp.hpl.jena.rdf.model.Statement;
=======
>>>>>>> master
import org.codeontology.Ontology;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RDFLogger {
    private Model model = Ontology.getModel();
    private String outputFile = "triples.nt";
    private int counter = 0;
    private static RDFLogger instance = new RDFLogger();
<<<<<<< HEAD
    public static final int MAX_SIZE = 10000;
=======
    public static final int LIMIT = 100000;
>>>>>>> master

    private RDFLogger() {

    }

    public static RDFLogger getInstance() {
        return instance;
    }

    public Model getModel() {
        return model;
    }

    public void setOutputFile(String path) {
        outputFile = path;
    }

    public void writeRDF() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)))) {
<<<<<<< HEAD
            model.write(writer, "N-TRIPLE");
        } catch (IOException e) {
            System.out.println("Cannot write triples.");
=======
            getModel().write(writer, "N-TRIPLE");
            model = Ontology.getModel();
            counter = 0;
        } catch (IOException e) {
            System.out.println("Unable to write triples");
>>>>>>> master
            System.exit(-1);
        }
    }

<<<<<<< HEAD
    public void addTriple(Wrapper<?> subject, Property property, Wrapper object) {
        addTriple(subject, property, object.getResource());
    }

    public void addTriple(Wrapper<?> subject, Property property, RDFNode object) {
        if (property != null && object != null) {
            Statement triple = model.createStatement(subject.getResource(), property, object);
            model.add(triple);
            counter++;
            if (counter > MAX_SIZE) {
                writeRDF();
                free();
            }
        }
    }

    private void free() {
        model = Ontology.getModel();
        counter = 0;
    }
=======
    public void addTriple(Wrapper subject, Property property, Wrapper object) {
        addTriple(subject, property, object.getResource());
    }

    public void addTriple(Wrapper subject, Property property, RDFNode object) {
        if (property != null && object != null) {
            model.add(model.createStatement(subject.getResource(), property, object));
            counter++;
            if (counter >= LIMIT) {
                writeRDF();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
>>>>>>> master
}
