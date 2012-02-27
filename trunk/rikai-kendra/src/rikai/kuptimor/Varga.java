package rikai.kuptimor;

import java.awt.Color;

import rikai.kendra.Kendra;
import yarar.tietokanta.Feldtyp;
import yarar.tietokanta.Kysely;
import yarar.tietokanta.KyselyLadata;
import yarar.tietokanta.TypeValuePair;

/**
 * This represents a semantic category. Each varga (i.e. category) is part of exactly one category
 * tree. Location in the semantic category tree is defined by reference to the parent varga object.
 * For top-level varga objects parent reference equals self instance.<br>
 * Instantiation should be done from {@link KuptimorFactory#createVarga(String, int, int, String)}.<br>
 * <b>Varga</b> is the latin-phonetical spelling of the <a
 * href="http://en.wikipedia.org/wiki/Kannada_language" target="_blank">Kannada</a> word ವರ್ಗ, which
 * means <i>category</i>.
 * 
 * @author Dimo Vanchev
 */
public abstract class Varga extends AbstractKuptimor {
    /** SID of parent category, or self SID if top-level */
    private final int parentSID;

    /**
     * Instantiates a new {@link Varga} object.
     * 
     * @param SID Unique semantic identifier.
     * @param parentSID SID of the parent category.
     * @param name Name of the object.
     */
    public Varga(final int SID, final int parentSID, final String name) {
	super(name);
	this.parentSID = parentSID;
	setName(name);
	setSID(SID);
	setInternalID(name);
    }

    /**
     * Returns the parent category, or <i>self</i>, if current Varga is a top-level one.
     * 
     * @return parent or self.
     */
    public Varga getParent() {
	return OntolegLadata.getInstance().getVarga(parentSID);
    }

    /**
     * Checks if the Varga is top level.
     * 
     * @return <code>true</code> if there are no parents.
     */
    public boolean isTopLevel() {
	return (getSID() == parentSID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see rikai.kuptimor.AbstractKuptimor#getDescription(java.lang.String)
     */
    @Override
    public String getDescription(final String langid) {
	final Kysely kysely = KyselyLadata.getInstance().getKysely(
		"Varga.getDescription");
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

    /*
     * (non-Javadoc)
     * 
     * @see yarar.graph.YGAbstractElement#getDisplayColor()
     */
    @Override
    public Color getDisplayColor() {
	if (isTopLevel()) {
	    super.setDisplayColor(Color.ORANGE);
	}
	return super.getDisplayColor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see yarar.graph.YGAbstractElement#getDisplayStroke()
     */
    // @Override
    // public Stroke getDisplayStroke() {
    // if (isTopLevel()) {
    // super.setDisplayStroke(YGStrokeType.SOLID);
    // }
    // return super.getDisplayStroke();
    // }

}
