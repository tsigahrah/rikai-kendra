package test;

import java.awt.Color;

import rikai.kuptimor.Kuptimor;
import rikai.kuptimor.Varga;

@SuppressWarnings({ "javadoc" })
public class TestKuptimor extends Kuptimor {

    /**
     * @param SID
     * @param varga
     * @param name
     */
    public TestKuptimor(final int SID, final Varga varga, final String name) {
	super(SID, varga, name);
	setDisplayColor(Color.CYAN);
    }

}