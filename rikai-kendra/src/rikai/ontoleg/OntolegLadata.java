package rikai.ontoleg;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import rikai.kendra.Kendra;
import yarar.graph.YGAbstractGraphHolder;
import yarar.graph.YGGraph;
import yarar.graph.YGVisualisationLayouts;
import yarar.tietokanta.Feldtyp;
import yarar.tietokanta.Kysely;
import yarar.tietokanta.KyselyLadata;

/**
 * Initiates all semantic categories {@link Varga}.<br>
 * <b>Ontoleg</b> is an <a href="http://en.wikipedia.org/wiki/Welsh_language"
 * target="_blank">Welsh</a> word meaning <i>ontology</i>.<br>
 * <b>Ladata</b> is a <a href="http://en.wikipedia.org/wiki/Finnish_language"
 * target="_blank">Finnish</a> verb with meaning <i>load, charge</i>.
 * 
 * @author Dimo Vanchev
 */
public class OntolegLadata extends YGAbstractGraphHolder {

    /** The only instance of this class. */
    private static OntolegLadata selfInstance;

    /**
     * {@link Enumeration} containing all SIDs from the loaded {@link Varga} objects. These are used
     * as keys in {@link #vargas}.
     */
    private Set<Integer> keys;

    /**
     * This {@link HashMap} contains all {@link Varga} defined in the DB, using their SID as a key.
     */
    private final HashMap<Integer, Varga> vargas;

    /**
     * In "config.properties" there is a property defining which varga-branches should be skipped.
     * The result is stored here.
     */
    private final String[] branchesToSkip;

    /** Singleton private constructor. */
    private OntolegLadata() {
	OntolegLadata.selfInstance = this;
	/* initiates the Ontoleg object, which will store the varga-tree */
	ygg = Ontoleg.getInstance();
	/* initiating the HashMap that will store all vargas */
	vargas = new HashMap<Integer, Varga>();
	/* checks which branches must be skipped */
	branchesToSkip = Kendra.getInstance().getConfigValue("varga.branches.to.skip").split(",");
	load();
	buildVargaTree();
	setDisplayPreferences();
    }

    /**
     * Singleton <code>getInstance()</code> implementation.
     * 
     * @return the only instance of this class.
     */
    public static OntolegLadata getInstance() {
	if (OntolegLadata.selfInstance == null) {
	    new OntolegLadata();
	}
	return OntolegLadata.selfInstance;
    }

    /**
     * Loads all {@link Varga} objects and stores them into a Map
     */
    private void load() {
	/* the kysely object loading all vargas */
	final Kysely kysely = KyselyLadata.getInstance()
		.getKysely("OntolegLadata.load");
	Kendra.getDB().doQuery(kysely);
	/* iterating over the results */
	if (kysely.hasResult()) {
	    /*
	     * Since we're going to iterate over a result set ordered by "varga_sid", we'll use the
	     * following two fields in order to prevent duplications of varga entries.
	     */
	    int lastVargaSID = 0;
	    Varga varga = null;

	    while (kysely.goNext()) {
		final Integer vargaSID = (Integer) kysely.getFieldAs("vargasid", Feldtyp.INTEGER);
		final Integer parentSID = (int) kysely.getFieldAs("vargaparent", Feldtyp.INTEGER);

		/* check if it's a new varga */
		if (lastVargaSID != vargaSID) {
		    varga = KuptimorFactory.createVarga(
			    (String) kysely.getFieldAs("vargaclass", Feldtyp.STRING), vargaSID,
			    parentSID, (String) kysely.getFieldAs("varganame", Feldtyp.STRING));

		    /* check if it's a top-level varga or not part of forbidden trees */
		    if (((vargaSID.intValue() == parentSID.intValue()) || (getVarga(parentSID) != null))
			    && !isBranchToSkip(vargaSID)) {
			vargas.put(vargaSID, varga);
			((Ontoleg) ygg).addFork(varga);
		    } else {
			varga = null;
		    }
		    lastVargaSID = vargaSID.intValue();
		}

		/* adding kuptimor's in the tree */
		final Integer kuptimorSID = (Integer) kysely.getFieldAs("ksid", Feldtyp.INTEGER);
		if ((kuptimorSID > 0) && (varga != null)) {
		    final Kuptimor kuptimor = KuptimorFactory.createKuptimor(
			    (String) kysely.getFieldAs("kclass", Feldtyp.STRING), kuptimorSID,
			    varga, (String) kysely.getFieldAs("kname", Feldtyp.STRING));
		    ((Ontoleg) ygg).addLeafTo(kuptimor, varga);
		}
	    }
	}
	/* sets the keys' Set */
	keys = vargas.keySet();
    }

    /**
     * Checks if the varga with a given ID and all its sub-branches should be skipped.
     * 
     * @param sid The SID of the referenced varga object.
     * @return <code>true</code> if it needs to be skipped, <code>false</code> - otherwise.
     */
    private boolean isBranchToSkip(final int sid) {
	for (final String s : branchesToSkip) {
	    if (new Integer(s).intValue() == sid) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Orders the loaded {@link Varga} elements into the category tree.
     */
    private void buildVargaTree() {
	for (final int sid : keys) {
	    ((Ontoleg) ygg).addBranch(getVarga(sid));
	}
    }

    /**
     * Displays a branch of the forest.
     * 
     * @param varga the {@link Varga} to branch from.
     */
    public void displayBranch(final Varga varga) {
	final YGGraph branch = ygg.cloneBranch(varga);
	display(branch);
    }

    /**
     * Gets a <code>Varga</code> object by SID.
     * 
     * @param SID the unique class-identifier in the semantic database, used as a key
     * @return the corresponding varga object.
     */
    public Varga getVarga(final int SID) {
	return vargas.get(SID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see yarar.graph.YGAbstractGraphHolder#setDisplayPreferences()
     */
    @Override
    protected void setDisplayPreferences() {
	preferredLayout = YGVisualisationLayouts.RADIAL_TREE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see yarar.graph.YGAbstractGraphHolder#getPreferredLayout()
     */
    @Override
    protected YGVisualisationLayouts getPreferredLayout() {
	return preferredLayout;
    }
}
