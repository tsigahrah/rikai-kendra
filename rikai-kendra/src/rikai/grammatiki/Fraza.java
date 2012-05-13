package rikai.grammatiki;

import rikai.ontoleg.Kuptimor;
import yarar.graph.YGVertex;

/**
 * DOC empty type javadoc
 * 
 * @author Dimo Vanchev
 */
public class Fraza extends YGVertex {
    
    private Kuptimor content;
    // members[] part of speech || context modifiers || something even smarter :)

//    /**
//     * Creates a new Fraza instance.
//     * 
//     * @param internalID The internal id.
//     */
//    public Fraza(final String internalID) {
//	super(internalID);
//    }

    
    // FIXME this is just a dummy implementation for Fraza.content
    public Fraza(Kuptimor content) {
	//super("fraza" + content.getSID());
	super(content.toString());
	setContent(content);
    }
    
    private void setContent(Kuptimor content) {
	this.content = content;
    }
    
    public Kuptimor getContent() {
	return content;
    }
}
