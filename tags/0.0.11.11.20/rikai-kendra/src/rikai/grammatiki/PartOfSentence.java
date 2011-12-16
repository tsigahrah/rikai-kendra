package rikai.grammatiki;

import rikai.kuptimor.Fraza;

/**
 * DOC empty type javadoc
 * 
 * @author Dimo Vanchev
 */
public abstract class PartOfSentence {

    private Fraza[] frazi;// UGLY: array [] is not the best type. Look for other data structures.

    public Fraza[] getFrazi() {
	return frazi;
    }

}
