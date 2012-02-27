package rikai.kuptimor;

import yarar.graph.YGEdge;

/**
 * This abstract class denotes relationship between two objects. <Br
 * DOC provide more details for Relatia.
 * 
 * @author Dimo Vanchev
 */
public abstract class Relatia extends YGEdge {

    /** the weight of a edge within a graph */
    protected int weight = 1;

    /**
     * Creates a new Relatia instance.
     * 
     * @param internalID The internal id.
     */
    public Relatia(final String internalID) {
	super(internalID);
    }

    /**
     * @return the weight
     */
    public final int getWeight() {
	return weight;
    }

    /**
     * @param weight the weight to set
     */
    public final void setWeight(final int weight) {
	this.weight = weight;
    }
}
