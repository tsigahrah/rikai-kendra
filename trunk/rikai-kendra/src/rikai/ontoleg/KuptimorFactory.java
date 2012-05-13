package rikai.ontoleg;

import java.lang.reflect.Constructor;

import rikai.kendra.Kendra;
import yarar.rikai.Logger;

/**
 * Factory for creating/instantiating {@link Varga} and {@link Kuptimor} objects.
 * 
 * @author Dimo Vanchev
 */
public final class KuptimorFactory {

    /**
     * Instance of the class, instantiated as a <code>static final</code> for simplicity in calling
     * factory methods
     */
    private static final KuptimorFactory INSTANCE = new KuptimorFactory();

    /**
     * Creates a new {@link Varga} object.
     * 
     * @param className The name of the class to instantiate.
     * @param SID Unique semantic identifier.
     * @param parentSID SID of the parent category.
     * @param name Name of the object.
     * @return new instance from the requested class
     */
    public static Varga createVarga(final String className, final int SID,
	    final int parentSID, final String name) {
	Varga varga = null;

	if (className != null) {
	    varga = KuptimorFactory.loadVargaClass(className, SID, parentSID, name);
	    // if loading failed, "varga" will be still null, so we're checking what if the error
	    // level policy is set to throw error
	    if ((varga == null) && KuptimorFactory.shouldThrowErrorOnLoadingFail()) {
		throw new Error("Failed to load class: " + name);
	    }
	}

	if (varga == null) {
	    varga = KuptimorFactory.INSTANCE.new SimpleVarga(SID, parentSID, name);
	}

	return varga;
    }

    /**
     * Creates a new {@link Kuptimor} object.
     * 
     * @param className The name of the class to instantiate.
     * @param SID Unique semantic identifier.
     * @param varga Reference to the semantic category.
     * @param name Name of the object.
     * @return new instance from the requested class
     */
    public static Kuptimor createKuptimor(final String className, final int SID,
	    final Varga varga, final String name) {
	Kuptimor k = null;

	if (className != null) {
	    k = KuptimorFactory.loadKuptimorClass(className, SID, varga, name);
	    // if loading failed, "k" will be still null, so we're checking what if the error level
	    // policy is set to throw error
	    if ((k == null) && KuptimorFactory.shouldThrowErrorOnLoadingFail()) {
		throw new Error("Failed to load class: " + name);
	    }
	}

	if (k == null) {
	    k = KuptimorFactory.INSTANCE.new SimpleKuptimor(SID, varga, name);
	}

	return k;
    }

    /**
     * Instantiates the requested class.
     * 
     * @param className The class name to load (format package.subpackage.NameOfTheClass).
     * @param SID The unique identifier of the class to load.
     * @param parentSID The unique identifier of the parent category.
     * @param name The name of the object.
     * @return New instance, or <code>null</code> if for some reason the operation failed.
     */
    private static Varga loadVargaClass(final String className, final int SID,
	    final int parentSID, final String name) {
	Varga k = null;

	try {
	    final Class<?> cl = ClassLoader.getSystemClassLoader().loadClass(className);
	    final Constructor<?> co = cl.getConstructors()[0];
	    k = (Varga) co.newInstance(SID, parentSID, name);
	} catch (final Exception e) {
	    Logger.print(e);
	}

	return k;
    }

    /**
     * Instantiates the requested class.
     * 
     * @param className The class name to load (format package.subpackage.NameOfTheClass).
     * @param SID The unique identifier of the class to load.
     * @param varga Reference of the related {@link Varga} object.
     * @param name The name of the object.
     * @return New instance, or <code>null</code> if for some reason the operation failed.
     */
    private static Kuptimor loadKuptimorClass(final String className, final int SID,
	    final Varga varga, final String name) {
	Kuptimor k = null;
	try {
	    final Class<?> cl = ClassLoader.getSystemClassLoader().loadClass(className);
	    final Constructor<?> co = cl.getConstructors()[0];
	    k = (Kuptimor) co.newInstance(SID, varga, name);
	} catch (final Exception e) {
	    Logger.print(e);
	}

	return k;
    }

    /**
     * Checks what is the policy in case loading/instantiating of the class fails. This policy is
     * set in the configuration file.
     * 
     * @return <code>true</code> if policy is set to throw error, <code>false</code> otherwise.
     */
    private static boolean shouldThrowErrorOnLoadingFail() {
	final String errorLevel = Kendra.getInstance()
		.getConfigValue("errorlevel.kuptimor.loading");
	return errorLevel.equals("E");
    }

    /**
     * Simple implementation of {@link Varga}, used when the class name in the relevant DB record is
     * not defined, i.e. <code>null</code>.
     */
    private final class SimpleVarga extends Varga {
	/**
	 * Creates a new SimpleVarga instance.
	 * 
	 * @param SID Unique semantic identifier.
	 * @param parentSID SID of the parent category.
	 * @param name Name of the object.
	 */
	protected SimpleVarga(final int SID, final int parentSID, final String name) {
	    super(SID, parentSID, name);
	}

    }

    /**
     * Simple implementation of {@link Kuptimor}, used when the class name in the relevant DB record
     * is not defined, i.e. <code>null</code>.
     */
    private final class SimpleKuptimor extends Kuptimor {
	/**
	 * Creates a new SimpleKuptimor instance.
	 * 
	 * @param SID Unique semantic identifier.
	 * @param varga Reference to the semantic category.
	 * @param name Name of the object.
	 */
	public SimpleKuptimor(final int SID, final Varga varga, final String name) {
	    super(SID, varga, name);
	}

    }

}
