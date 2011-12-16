package rikai.kuptimor;

import java.util.Collection;

import yarar.graph.YGAbstractGraphHolder;
import yarar.graph.YGDisplay;
import yarar.graph.YGGraph;
import yarar.graph.YGGraphTypes;

/**
 * DOC empty type javadoc
 * 
 * @author Dimo Vanchev
 */
public abstract class AbstractRelationshipMap extends YGAbstractGraphHolder {

    public AbstractRelationshipMap() {
	ygg = new YGGraph(YGGraphTypes.SPARSE_DIRECTED);
    }

    public void addFraza(final Fraza v) {
	ygg.addVertex(v);
    }

    public void addRelatia(final Relatia e, final Fraza v1, final Fraza v2) {
	ygg.addEdge(e, v1, v2, YGGraph.EDGE_DIRECTED);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Collection<Relatia> getRelatia(final Fraza v1, final Fraza v2) {
	return (Collection) ygg.findEdgeSet(v1, v2);
    }

    public Fraza getRelatedFraza(final Fraza v, final Relatia e) {
	return (Fraza) ygg.getOpposite(v, e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see yarar.graph.YGAbstractGraphHolder#setPreferredLayout()
     */
    @Override
    protected void setDisplayPreferences() {
	preferredLayout = YGDisplay.Layouts.FR;
    }

}
