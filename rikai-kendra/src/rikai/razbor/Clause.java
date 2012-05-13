package rikai.razbor;

import rikai.grammatiki.Podlog;
import rikai.grammatiki.Skazuemo;

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
public class Clause extends AbstractClause {
    /** The subject */
    private Podlog podlog;
    /** The predicate */
    private Skazuemo skazuemo;

    /**
     * Gets the subject of the clause.
     * @return the subject.
     */
    public final Podlog getPodlog() {
	return podlog;
    }

    /** 
     * Get the predicate of the clause.
     * @return the predicate.
     */
    public final Skazuemo getSkazuemo() {
	return skazuemo;
    }

    public void setKontext(ClauseKontext kontext) {
	setKontext(kontext, KontextTypes.CLAUSE);
    }
}
