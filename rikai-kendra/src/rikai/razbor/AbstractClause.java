package rikai.razbor;

import yarar.graph.YGVertex;

public abstract class AbstractClause extends YGVertex implements KontextTr�ger {
    
    protected AbstractKontext kontext;
    private KontextTypes kontextType;
    
    protected final void setKontext(AbstractKontext kontext, KontextTypes kontextType) {
	this.kontext = kontext;
	this.kontextType = kontextType;
    }
    
    /*
     * (non-Javadoc)
     * @see rikai.ontoleg.KontextTräger#getContext()
     */
    @Override
    public final AbstractKontext getKontext() {
	return kontext;
    }
    
    /*
     * (non-Javadoc)
     * @see rikai.ontoleg.KontextTräger#getContextType()
     */
    @Override
    public final KontextTypes getKontextType() {
	return kontextType;
    }

}
