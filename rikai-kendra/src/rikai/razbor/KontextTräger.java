package rikai.razbor;

/**
 * DOC empty type javadoc
 * 
 * @author Dimo Vanchev
 */
public interface KontextTräger {

    /**
     * Returns the context.
     * @return The relevant context object.
     */
    public AbstractKontext getKontext();
    
    /**
     * Returns the context type.
     * @return The type of the context.
     */
    public KontextTypes getKontextType();
}
