package jve.generated;

/**
 * A data source is a factory for a facade of some type of data service.
 * It is the responsibility of the DataSource implementor to initialize the facade
 * properly. Examples of facades include be a EJB session bean, a web service proxy,
 * and a JavaBean factory.
 */
public interface IDataSource {

	/**
	 * In design time, this method will be used to reflect
	 * on the services that are available from the implemented data source
	 * @return the class of the facade
	 */
	public Class getType();

	/**
	 * Get an an instance for a data source facade that is initialized
	 * properly and whose services are available. <br>
	 * Note: this method may return null at design time using<br> 
	 * <code>if (java.beans.Beans.isDesignTime()) { return null; }</code><br>
	 * to prevent querying live data while designing the interface.
	 * 
	 * @return an instance of the facde
	 */
	public Object getDataSource();

	/**
	 * Set an instance of a facade to become the data source
	 * @param facade the data source
	 */
	public void setDataSource(Object facade);
}