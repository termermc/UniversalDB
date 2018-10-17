package net.termer.udb;

/**
 * Adapter class for databases
 * @author termer
 * @since 1.0
 */
public interface DatabaseAdapter {
	/**
	 * Connects to this database
	 * @since 1.0
	 */
	public void connect() throws Exception;
	
	/**
	 * Disconnects from this database
	 * @since 1.0
	 */
	public void disconnect() throws Exception;
}