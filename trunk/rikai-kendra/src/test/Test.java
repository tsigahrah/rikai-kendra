package test;

import rikai.kendra.Kendra;
import rikai.kuptimor.OntolegLadata;
import yarar.rikai.Logger;
import yarar.tietokanta.Feldtyp;
import yarar.tietokanta.Kysely;
import yarar.tietokanta.KyselyLadata;
import yarar.tietokanta.TypeValuePair;

/**
 * As the name suggests, this is a <i>TEST</i> class.
 * 
 * @author Dimo Vanchev
 */
@SuppressWarnings({ "javadoc", "unused" })
public final class Test {

    /**
     * Private constructor
     */
    private Test() {
	Kendra.getInstance();
	// testDB();
	testGraphs();
    }

    /**
     * Test Graphs.
     */
    private void testGraphs() {
	// pay attention to the "varga.branches.to.skip" property from config.properties
	// which instructs OntolegLadata to skip loading specific branches
	final OntolegLadata kvl = OntolegLadata.getInstance();
	kvl.displaySelf();
	// kvl.displayBranch(kvl.getVarga(21));
    }

    /**
     * Tests DB connection
     */
    private void testDB() {
	// sqlISO();
	// sqlVargaDescription();
	sqlKuptimorDescription();
    }

    private void sqlVargaDescription() {
	final Kysely vargaKysely = KyselyLadata.getInstance().getKysely(
		"Varga.getDescription");
	Kendra.getDB().prepareQuery(vargaKysely);
	final TypeValuePair[] values = {
		new TypeValuePair(Feldtyp.INTEGER, 1),
		new TypeValuePair(Feldtyp.STRING, "bul")
	};
	Kendra.getDB().doPreparedQuery(vargaKysely, values);
	if (vargaKysely.hasResult()) {
	    while (vargaKysely.goNext()) {
		Logger.print("description = " + vargaKysely.getFieldAs(1, Feldtyp.STRING));
	    }
	}
    }

    private void sqlKuptimorDescription() {
	final Kysely kysely = KyselyLadata.getInstance().getKysely(
		"Kuptimor.getDescription");
	Kendra.getDB().prepareQuery(kysely);
	final TypeValuePair[] values = {
		new TypeValuePair(Feldtyp.INTEGER, 2),
		new TypeValuePair(Feldtyp.STRING, "eng")
	};
	Kendra.getDB().doPreparedQuery(kysely, values);
	if (kysely.hasResult()) {
	    while (kysely.goNext()) {
		Logger.print("description = " + kysely.getFieldAs(1, Feldtyp.STRING));
	    }
	}
    }

    private void sqlISO() {
	final Kysely testKysely = KyselyLadata.getInstance().getKysely("list_iso639_3");
	Kendra.getDB().doQuery(testKysely);
	if (testKysely.hasResult()) {
	    while (testKysely.goNext()) {
		Logger.print("code = " + testKysely.getFieldAs(1, Feldtyp.STRING)
			+ "; text = " + testKysely.getFieldAs("ref_name", Feldtyp.STRING));
	    }
	}
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
	new Test();
    }
}
