package rikai.razbor;


/**
 * DOC empty type javadoc
 * 
 * @author Dimo Vanchev
 */
public abstract class AbstractKontext {

    private AbstractRelationshipMap relationshipMap;

    // public abstract Fraza getAnswer(AbstractKontextQuestion question);
    // implementation like: AbstractRelationshipMap.getSomehowTheFraza

    public final AbstractRelationshipMap getRelationshipMap() {
	return relationshipMap;
    }

}
