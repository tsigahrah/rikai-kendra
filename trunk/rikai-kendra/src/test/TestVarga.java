package test;

import java.awt.Color;

import rikai.kuptimor.Varga;

@SuppressWarnings({ "javadoc" })
public class TestVarga extends Varga {

    /**
     * @param SID
     * @param parentSID
     * @param name
     */
    public TestVarga(final int SID, final int parentSID, final String name) {
	super(SID, parentSID, name);
	setDisplayColor(Color.RED);
    }

}
