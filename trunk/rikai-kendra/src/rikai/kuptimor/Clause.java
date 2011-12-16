package rikai.kuptimor;

import rikai.grammatiki.Podlog;
import rikai.grammatiki.Skazuemo;
import yarar.graph.YGVertex;

/**
 * In grammar, a <b>clause</b> is the smallest grammatical unit that can express a complete
 * proposition. The most basic kind of sentence consists of a single (independent) clause. More
 * complex sentences may contain multiple clauses, including clauses contained within clauses.
 * <p>
 * Clauses may be <i>independent</i> or <i>dependent</i>. Independent clauses are those that could
 * stand as a sentence by themselves, although they may be used connected with other clauses in a
 * longer sentence. Dependent clauses are those that would be awkward or nonsensical if used alone,
 * and must be used in a sentence also containing an independent clause.
 * 
 * @author Dimo Vanchev
 */
public class Clause extends YGVertex implements KontextTräger {
    private Podlog podlog;
    private Skazuemo skazuemo;
    private ClauseContext context;

    public final Podlog getPodlog() {
	return podlog;
    }

    public final Skazuemo getSkazuemo() {
	return skazuemo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see rikai.kuptimor.KontextTräger#getContext()
     */
    @Override
    public final AbstractContext getContext() {
	return context;
    }
}
