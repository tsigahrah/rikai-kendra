package rikai.kuptimor;

import rikai.kendra.Kendra;
import yarar.graph.YGVShape;
import yarar.tietokanta.Feldtyp;
import yarar.tietokanta.Kysely;
import yarar.tietokanta.KyselyLadata;
import yarar.tietokanta.TypeValuePair;

/**
 * This represents a semantic object. Each semantic object belongs to one and only one varga (i.e.
 * category), but there are semantical relations between different Kuptimor objects.<br>
 * Instantiation should be done from
 * {@link KuptimorFactory#createKuptimor(String, int, Varga, String)}.<br>
 * <b>Kuptimor</b> is an <a href="http://en.wikipedia.org/wiki/Albanian_language"
 * target="_blank">Albanian</a> adjective for <i>semantic</i>.<br>
 * 
 * @author Dimo Vanchev
 */
public abstract class Kuptimor extends AbstractKuptimor {
    /** The semantic category of current object */
    protected Varga varga;

    /**
     * Instantiates a new {@link Kuptimor} object.
     * 
     * @param SID Unique semantic identifier.
     * @param varga Reference to the semantic category.
     * @param name Name of the object.
     */
    public Kuptimor(final int SID, final Varga varga, final String name) {
	super(name);
	setName(name);
	setVarga(varga);
	setSID(SID);
	setInternalID(name);
	setDisplayShape(YGVShape.RECTANGLE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see rikai.kuptimor.AbstractKuptimor#getDescription(java.lang.String)
     */
    @Override
    public String getDescription(final String langid) {
	final Kysely kysely = KyselyLadata.getInstance().getKysely("Kuptimor.getDescription");
	Kendra.getDB().prepareQuery(kysely);
	final TypeValuePair[] values = {
		new TypeValuePair(Feldtyp.INTEGER, getSID()),
		new TypeValuePair(Feldtyp.STRING, langid)
	};
	Kendra.getDB().doPreparedQuery(kysely, values);

	String description = null;
	if (kysely.hasResult()) {
	    kysely.goNext();
	    description = (String) kysely.getFieldAs(1, Feldtyp.STRING);
	}
	return description;
    }

    /**
     * Gets the semantic category of current object
     * 
     * @return the varga
     */
    public Varga getVarga() {
	return varga;
    }

    /**
     * Sets the semantic category of current object
     * 
     * @param varga the varga to set
     */
    protected void setVarga(final Varga varga) {
	this.varga = varga;
    }

    /**
     * Returns the unique semantic identifier of the related semantic category.
     * 
     * @return The unique semantic identifier.
     * @see rikai.kuptimor.AbstractKuptimor#getSID()
     */
    public int getVargaSID() {
	return varga.getSID();
    }

}
