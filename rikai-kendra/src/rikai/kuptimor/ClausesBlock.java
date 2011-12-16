package rikai.kuptimor;

import java.util.Collection;

/**
 * Group of sequential, semantically connected {@link Clause}s. In a typical example a
 * <code>ClausesBlock</code> means a sentence. <br>
 * Example of <a href="http://en.wikipedia.org/wiki/Complex-compound_sentence"
 * target="_blank">complex-compound sentence</a>:<br>
 * <i>The dog lived in the backyard, but the cat, who knew he was superior, lived inside the
 * house.</i><br>
 * In this example we have two independent and one dependent clause:
 * <ul>
 * <li><i>The dog lived in the backyard.</i> - independent</li>
 * <li><i>The cat lived inside the house.</i> - independent</li>
 * <li><i>who knew he was superior</i> - dependent</li>
 * </ul>
 * 
 * @author Dimo Vanchev
 */
public class ClausesBlock implements KontextTräger {

    private ClausesBlockContext context;
    private Collection<Clause> clauses;

    public Collection<Clause> getClauses() {
	return clauses;
    }

    @Override
    public final AbstractContext getContext() {
	return context;
    }

}
