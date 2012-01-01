package rikai.kendra;

import yarar.rikai.ConfigParser;
import yarar.rikai.Logger;
import yarar.tietokanta.KyselyLadata;
import yarar.tietokanta.Tietokanta;

/**
 * This is the main class of Rikai-Kendra project. It is the single point for launching and
 * initiating the application.<br>
 * <b>Kēndra</b> is the latin-phonetical spelling of the <a
 * href="http://en.wikipedia.org/wiki/Telugu_language" target="_blank">Telugu</a> word కేంద్ర, which
 * means <i>central</i>.
 * 
 * @author Dimo Vanchev
 * 
 */
public final class Kendra {
    /**
     * The main DB object.
     */
    private static Tietokanta db;
    /**
     * The single instance of this class.
     */
    private static Kendra selfInstance;
    /** Parser of application-level settings. */
    private final ConfigParser configParser;

    /**
     * Singleton private constructor.
     */
    private Kendra() {
	Kendra.selfInstance = this;
	// loading properties from the the relevant properties file
	configParser = new ConfigParser("config.properties");
	init();
    }

    /**
     * Returns the main DB instance used by the Rikai Kendra application.
     * 
     * @return the relevant Tietokanta (DB) instance.
     */
    public static Tietokanta getDB() {
	return Kendra.db;
    }

    /**
     * Searches for a configuration value from the relevant property list.
     * 
     * @param key
     *            Configuration key
     * @return the found value
     */
    public String getConfigValue(final String key) {
	return configParser.getValue(key);
    }

    /**
     * Initialises the main application components.
     */
    private void init() {
	// instantiate the logger
	Logger.getInstance();
	// instantiate the DB
	Kendra.db = new Tietokanta();
	// instantiate the SQLs
	KyselyLadata.getInstance();
    }

    /**
     * Singleton instance getter.
     * 
     * @return The single instance of this class.
     */
    public static final Kendra getInstance() {
	if (Kendra.selfInstance == null) {
	    new Kendra();
	}
	return Kendra.selfInstance;
    }

    /**
     * The main method to start the application.
     * 
     * @param args
     *            all parameters are ignored
     */
    public static void main(final String[] args) {
	Kendra.getInstance();
    }

}
