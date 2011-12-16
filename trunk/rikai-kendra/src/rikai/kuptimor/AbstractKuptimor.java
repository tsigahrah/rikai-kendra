package rikai.kuptimor;

import yarar.graph.YGVertex;

/**
 * This is the abstract class for all <i>semantic objects</i> within the Rikai project.<br>
 * <b>Kuptimor</b> is an <a href="http://en.wikipedia.org/wiki/Albanian_language"
 * target="_blank">Albanian</a> adjective for <i>semantic</i>.
 * 
 * @author Dimo Vanchev
 */
public abstract class AbstractKuptimor extends YGVertex {

    /**
     * Unique identifier of the class in the semantic database.
     */
    private int SID;

    /** Short name of the object in English */
    private String name;

    /**
     * Since AbtrsactKuptimor extends YGVertex, it is obligatory for subclasses to call this
     * constructor.
     * 
     * @param internalID The id used later in graph creation.
     */
    public AbstractKuptimor(final String internalID) {
	super(internalID);
    }

    /**
     * Gets the unique identifier of the class in the semantic database.
     * 
     * @return The unique semantic identifier.
     */
    public int getSID() {
	return SID;
    }

    /**
     * Sets the unique identifier of the class in the semantic database.
     * 
     * @param SID The unique semantic identifier.
     */
    protected void setSID(final int SID) {
	this.SID = SID;
    }

    /**
     * Returns the short name in English of current object as defined in the database.
     * 
     * @return the name.
     */
    public String getName() {
	return name;
    }

    /**
     * Returns description defined in the specified language.
     * 
     * @param langid An <code>ISO-639-3</code> language code, i.e. <i>eng</i> for English,
     *        <i>bul</i> for Bulgarian.
     * @return the description in the specified language, or <code>null</code>, if such not found.
     */
    public abstract String getDescription(final String langid);

    /**
     * Sets the name of the object (used for visualisation and easier recognition).
     * 
     * @param name the name to set
     */
    protected void setName(final String name) {
	this.name = name;
    }
}
