package jve.generated;

/**
 * A Data Source Service provided the interfaces necessary to perform a service call
 * on a Data Source.
 */
public interface IDataSourceService {

	/**
	 * Set the Data Source associated with this action.
	 * 
	 * @param source  DataSource to use for this service
	 */
	public void setDataSource(IDataSource source);

	/**
	 * Get the Data Source associated with this action.
	 * 
	 * @return The Data Source used for this service 
	 */
	public IDataSource getDataSource();

	/**
	 * This is the service method signature that is to be use.
	 * It has to be complete including parameter types
	 * e.g. getUserRecord(Integer id);
	 * 
	 * @param service signature
	 */
	public void setServiceSignature(String service)
			throws IllegalArgumentException;

	/**
	 * Get the service signature for this action.
	 * 
	 * @return service signature
	 */
	public String getServiceSignature();

	/**
	 * Set the argument for the service call, according to the method signature
	 * set with the setService method.
	 * 
	 * @param arg the service argument
	 */
	public void setServiceArgument(ObjectReference arg);

	/**
	 * Set the arguments for the service call, according to the method signature
	 * set with the setService method.  The arguments must be in the proper order
	 * specified in the method signature.
	 * 
	 * @param args the service arguments
	 */

	/**
	 * Get the argument used as the parameter for the service call. 
	 * @return argument used for the service
	 */
	public ObjectReference getServiceArgument();

}