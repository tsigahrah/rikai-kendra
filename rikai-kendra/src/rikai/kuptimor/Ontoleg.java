package rikai.kuptimor;

import yarar.graph.YGGraph;
import yarar.graph.YGGraphTypes;

/**
 * Represents the static relationships between {@link Varga} and {@link Kuptimor} concepts within
 * the system, i.e. the so called <i>varga trees</i>.<br>
 * <b>Ontoleg</b> is an <a href="http://en.wikipedia.org/wiki/Welsh_language"
 * target="_blank">Welsh</a> word meaning <i>ontology</i>.<br>
 * 
 * @author Dimo Vanchev
 */
public class Ontoleg extends YGGraph {

    private static Ontoleg selfInstance;

    /**
     * Creates a new instance
     */
    private Ontoleg() {
	super(YGGraphTypes.FOREST);
	Ontoleg.selfInstance = this;
    }

    /**
     * Singleton getInstance implementation.
     * 
     * @return the single instance of this class
     */
    public static Ontoleg getInstance() {
	if (Ontoleg.selfInstance == null) {
	    new Ontoleg();
	}
	return Ontoleg.selfInstance;
    }

    /**
     * Adds a new category (varga)
     * 
     * @param varga the category to add
     */
    public void addFork(final Varga varga) {
	addVertex(varga);
    }

    /**
     * Creates a tree-branch between two categories.
     * 
     * @param varga the category to add to it's parent
     */
    public void addBranch(final Varga varga) {
	final Varga parent = varga.getParent();
	if ((parent != varga)) {
	    addEdge(new VargaVargaRelatia(), parent, varga);
	}
    }

    /**
     * Adds a new leaf (vertex) to an existing fork (varga)
     * 
     * @param kuptimor the leaf
     * @param varga the fork
     */
    public void addLeafTo(final Kuptimor kuptimor, final Varga varga) {
	addVertex(kuptimor);
	addEdge(new VargaKuptimorRelatia(), varga, kuptimor);
    }

}
