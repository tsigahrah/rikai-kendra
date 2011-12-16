package rikai.kuptimor;

/**
 * DOC empty type javadoc
 * 
 * @author Dimo Vanchev
 */
public abstract class AbstractContext {

    private AbstractRelationshipMap relationshipMap;

    public abstract Fraza getAnswer(AbstractContextQuestion question);

    public final AbstractRelationshipMap getRelationshipMap() {
	return relationshipMap;
    }

}
