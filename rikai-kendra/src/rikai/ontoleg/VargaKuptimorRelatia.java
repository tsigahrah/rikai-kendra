package rikai.ontoleg;

import rikai.kendra.Relatia;

/**
 * This edge type denotes relationship between {@link Varga} and {@link Kuptimor} objects.
 * 
 * @author Dimo Vanchev
 */
public class VargaKuptimorRelatia extends Relatia {

    /**
     * The edge will be with an empty label.
     */
    public VargaKuptimorRelatia() {
	super("");
    }

    /**
     * The edge will be with the label passed as parameter.
     * 
     * @param internalID The label.
     */
    public VargaKuptimorRelatia(final String internalID) {
	super(internalID);
	// TODO Auto-generated constructor stub
    }

}
