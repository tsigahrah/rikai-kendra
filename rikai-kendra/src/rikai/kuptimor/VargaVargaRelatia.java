package rikai.kuptimor;

/**
 * This edge type denotes relationship between two {@link Varga} objects.
 * 
 * @author Dimo Vanchev
 */
public class VargaVargaRelatia extends Relatia {

    /**
     * The edge will be with an empty label.
     */
    public VargaVargaRelatia() {
	super("");
    }

    /**
     * The edge will be with the label passed as parameter.
     * 
     * @param internalID The label.
     */
    public VargaVargaRelatia(final String internalID) {
	super(internalID);
    }

}
