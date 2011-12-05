package jve.generated;

import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Default implementation of IDataSourceService.
 */
public class DataSourceService implements IDataSourceService {

	protected IDataSource dataSource = null;

	private String serviceSignature = null; // full signature

	private String serviceName = null; // service part of the signature

	private Class[] serviceArgSignatures = null; // argument signature

	private ObjectReference[] serviceArguments = new ObjectReference[0];

	private PropertyChangeListener argsListener = null;

	// These properties should not be exposed on the property sheet
	private Method serviceMethod = null; // Cache the method

	private static final HashMap primitiveClasses = new HashMap(10);
	static {
		primitiveClasses.put("int", Integer.TYPE);
		primitiveClasses.put("float", Float.TYPE);
		primitiveClasses.put("char", Character.TYPE);
		primitiveClasses.put("short", Short.TYPE);
		primitiveClasses.put("long", Long.TYPE);
		primitiveClasses.put("double", Double.TYPE);
		primitiveClasses.put("boolean", Boolean.TYPE);
	}

	public DataSourceService(PropertyChangeListener argsListener) {
		this.argsListener = argsListener;
	}

	private String fdebugMsg = null;

	protected void log(Throwable t) {
		if (ObjectReference.debug && t != null) {
			t.printStackTrace();
		}
		if (t != null)
			fdebugMsg = t.getMessage();
		else
			fdebugMsg = null;
	}

	protected void log(String s) {
		if (ObjectReference.debug && s != null)
			System.err.println(s);
		fdebugMsg = s;
	}

	protected String getErrorMessage() {
		return fdebugMsg;
	}

	/**
	 * Remove the argument listener from all of the service arguments.
	 */
	protected void clearArgListeners() {
		if (argsListener != null) {
			for (int i = 0; i < serviceArguments.length; i++) {
				serviceArguments[i].removePropertyChangeListener(argsListener);
			}
		}
	}

	/**
	 * Add the argument listener to all of the service arguments.
	 */
	protected void addArgListeners() {
		if (argsListener != null) {
			for (int i = 0; i < serviceArguments.length; i++) {
				serviceArguments[i].addPropertyChangeListener(argsListener);
			}
		}
	}

	public void setDataSource(IDataSource source) {
		dataSource = source;
	}

	public IDataSource getDataSource() {
		return dataSource;
	}

	public Class getClass(String clazz) throws ClassNotFoundException {
		Class c = (Class) primitiveClasses.get(clazz);
		if (c == null)
			c = Class.forName(clazz);
		return c;
	}

	public void setServiceSignature(String service)
			throws IllegalArgumentException {
		serviceMethod = null;
		serviceSignature = null;
		int idx = service.indexOf('(');
		if (idx >= 0) {
			serviceName = service.substring(0, idx).trim();
			String args = service.substring(idx + 1, service.length());
			idx = args.indexOf(')');
			if (idx >= 0) {
				args = args.substring(0, idx);
			} else
				throw new IllegalArgumentException("signature missing '('");

			StringTokenizer t = new StringTokenizer(args, ",");
			serviceArgSignatures = new Class[t.countTokens()];
			for (int i = 0; i < t.countTokens(); i++) {
				String arg = null;
				try {
					arg = t.nextToken();
					serviceArgSignatures[i] = getClass(arg);
				} catch (ClassNotFoundException e) {
					throw new IllegalArgumentException("invalid class: " + arg);
				}
			}
		} else
			throw new IllegalArgumentException("signature missing ')'");
		serviceSignature = service;
	}

	public String getServiceSignature() {
		return serviceSignature;
	}

	public void setServiceArgument(ObjectReference arg) {
		// Stop Listening
		clearArgListeners();
		// temporary until we provide index property
		serviceArguments = new ObjectReference[] { arg };
		//	need to know if argument setting has changed
		addArgListeners();
	}

	public ObjectReference getServiceArgument() {
		if (serviceArguments.length == 0)
			serviceArguments = new ObjectReference[] { new ObjectReference() };
		return serviceArguments[0];
	}

	public String toString() {
		if (fdebugMsg != null)
			return fdebugMsg;
		return serviceSignature;
	}

	/**
	 * Resolve the service method based on the serviceName and argument signature
	 * @throws Exception throws any introspection exceptions resolving the method.
	 */
	protected Method resolveMethod() throws Exception {
		if (serviceArgSignatures == null)
			return null;
		return dataSource.getType()
				.getMethod(serviceName, serviceArgSignatures);
	}

	/**
	 * Call the refresh() method on all of the service arguments.
	 *
	 */
	protected void refreshArguments() {
		for (int i = 0; i < serviceArguments.length; i++) {
			if (serviceArguments[i] != null) {
				serviceArguments[i].refresh();
			}
		}
	}

	/**
	 * If the argument points to a binder, it will have to use the binder's
	 * object, and potentially a property from it
	 * @param index the index in the argument list
	 * @return the object of the argument at the given index
	 */
	protected Object getArg(int index) {
		if (index >= serviceArguments.length)
			return null;
		return serviceArguments[index].getObject();
	}

	/**
	 * Use the service provider to retrive an object.
	 * Assume service/args are already set
	 * @return service result
	 * @throws IllegalStateException if there's an error invoking the service
	 */
	public Object invokeDataSourceService() throws IllegalStateException {
		if (dataSource == null || serviceSignature == null)
			return null;
		Object[] args = new Object[serviceArguments.length];

		if (serviceMethod == null) {
			try {
				serviceMethod = resolveMethod();
			} catch (Exception e1) {
				log(e1);
			}
			if (serviceMethod == null)
				throw new IllegalStateException(getErrorMessage());
		}
		// Ensure that arguments have been refreshed.
		for (int i = 0; i < args.length; i++) {
			args[i] = getArg(i);
			if (args[i] == null) { // No support for null arguments at this time
				throw new IllegalStateException(fdebugMsg);
			}
		}

		try {
			Object result = serviceMethod.invoke(dataSource.getDataSource(),
					args);
			log((String) null);
			return result;
		} catch (InvocationTargetException e) {
			log(e.getTargetException());
			throw new IllegalStateException(getErrorMessage());
		} catch (IllegalArgumentException e) {
			log(e);
			throw new IllegalStateException(getErrorMessage());
		} catch (IllegalAccessException e) {
			log(e);
			throw new IllegalStateException(getErrorMessage());
		}
	}

	/**
	 * Reacts to some action performed on this data source service.
	 * This implmenetation simply passes the action onto all of the
	 * service's argument objects.
	 * 
	 * @param action the action performed
	 */
	public void actionPerformed(IActionBinder action) {
		// Some action occured that may impact myself and my arguments
		for (int i = 0; i < serviceArguments.length; i++) {
			serviceArguments[i].actionPerformed(action);
		}
	}

	/**
	 * Get the return type of the service call
	 * @return the return type
	 * @throws IllegalStateException if the service isn't properly set up or there was a reflection exception
	 */
	public Class getType() throws IllegalStateException {
		// make sure all is set
		StringBuffer msg = new StringBuffer();
		if (dataSource == null)
			msg.append("DataSource is not set");
		if (serviceSignature == null) {
			if (msg.length() > 0)
				msg.append(", and");
			msg.append("Service is not set");
		}
		if (msg.length() > 0)
			throw new IllegalStateException(msg.toString());
		Class service = dataSource.getType();
		if (service == null)
			throw new IllegalStateException("Bad Data Source");

		Class[] args = new Class[serviceArguments.length];
		args = serviceArgSignatures;
		// resolve the class from the datasource's service
		try {
			Method serviceMethod = service.getMethod(serviceName, args);
			return serviceMethod.getReturnType();
		} catch (Exception e1) {
			throw new IllegalStateException("Invalid Data Source Service");
		}
	}

	/**
	 * Refresh the data source service. <br>
	 * This implementation simply refreshes all of the service
	 * arguments.
	 *
	 */
	public void refresh() {
		refreshArguments();
	}
}