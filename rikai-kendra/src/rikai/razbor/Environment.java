package rikai.razbor;

/**
 * <code>Environment</code> is a over-sentence context level. It's focus is paragraph, i.e. the
 * context on shorter passages of text.<br>
 * IDEA: It sounds as a good idea to have one level next, something like Summary level of all the
 * information collected so far.
 * 
 * @author Dimo Vanchev
 */
public class Environment extends AbstractClause {

    public void setKontext(EnvironmentKontext kontext) {
	setKontext(kontext, KontextTypes.ENVIRONMENT);
    }

}
