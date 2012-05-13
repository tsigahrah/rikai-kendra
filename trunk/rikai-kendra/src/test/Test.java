package test;

import rikai.grammatiki.Fraza;
import rikai.kendra.Kendra;
import rikai.ontoleg.Kuptimor;
import rikai.ontoleg.KuptimorFactory;
import rikai.ontoleg.OntolegLadata;
import rikai.ontoleg.Varga;
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
	testRazbor();
    }
    
    private void testRazbor() {
	// pay attention to the "varga.branches.to.skip" property from config.properties
	// which instructs OntolegLadata to skip loading specific branches
	final OntolegLadata kvl = OntolegLadata.getInstance();
	Varga sv = KuptimorFactory.createVarga(null, 1, 1, "тестово");
	// Самотното куче лаеше в затънтеното село.
	Kuptimor k1 = KuptimorFactory.createKuptimor(null, 1, sv, "самотен");
	Kuptimor k2 = KuptimorFactory.createKuptimor(null, 2, sv, "куче");
	Kuptimor k3 = KuptimorFactory.createKuptimor(null, 3, sv, "лай");
	Kuptimor k4 = KuptimorFactory.createKuptimor(null, 4, sv, "село");
	Kuptimor k5 = KuptimorFactory.createKuptimor(null, 5, sv, "затънтен");
	Fraza f1 = new Fraza(k1);
	Fraza f2 = new Fraza(k2);
	Fraza f3 = new Fraza(k3);
	Fraza f4 = new Fraza(k4);
	Fraza f5 = new Fraza(k5);
	TestRelationshipMap trmap = new TestRelationshipMap();
	trmap.addFraza(f1);
	trmap.addFraza(f2);
	trmap.addFraza(f3);
	trmap.addFraza(f4);
	trmap.addRelatia(new TestRelatia("ОПРЕД"), f1, f2);
	trmap.addRelatia(new TestRelatia("ДЕЙНО"), f2, f3);
	trmap.addRelatia(new TestRelatia("ПОЯСН"), f2, f4);
	trmap.addRelatia(new TestRelatia("ОПРЕД"), f5, f4);
	trmap.displaySelf();
    }

    /**
     * Test Graphs.
     */
    private void testGraphs() {
	// pay attention to the "varga.branches.to.skip" property from config.properties
	// which instructs OntolegLadata to skip loading specific branches
	final OntolegLadata kvl = OntolegLadata.getInstance();
	kvl.displaySelf();
	// kvl.displayBranch(kvl.getVarga(10));
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
