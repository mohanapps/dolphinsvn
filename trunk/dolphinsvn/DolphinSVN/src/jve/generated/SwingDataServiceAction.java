package jve.generated;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;

/**
 * This action will execute a data source service with a single argument.  The argument is specified
 * by a ObjectReference.
 */
public class SwingDataServiceAction extends AbstractAction implements
		IActionBinder1, IDataSourceService, IDataObject {

	protected String fdebugMsg = null;

	public static boolean debug = System.getProperty("jve.debug") != null;

	private int disableFlags = FLAGS_NONE;

	private int enableFlags = FLAGS_NONE;

	boolean validArg = false;

	private IDataObject actionResult = new BasicDataObject();

	protected List actionBinderListeners = new ArrayList();

	protected ActionBinderEvent actionBinderEvent = new ActionBinderEvent(this);

	private static int allFlags = FLAGS_ACTION_PERFORMED | FLAGS_OBJECT_CHANGE
			| FLAGS_NULL_OBJECT_CHANGE | FLAGS_PROPERTY_CHANGE
			| FLAGS_NULL_PROPERTY_CHANGE;

	protected PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			int currentFlags = FLAGS_NONE;
			if (evt.getPropertyName().equals(IBoundObject.PROPERTY_OBJ_CHANGED)) {
				// Object Changed
				if (evt.getNewValue() != null) {
					currentFlags |= FLAGS_OBJECT_CHANGE;
					validArg = true;
				} else {
					currentFlags |= FLAGS_NULL_OBJECT_CHANGE;
					validArg = false;
				}
			} else {
				// Preperty Changed
				if (evt.getNewValue() != null) {
					currentFlags = FLAGS_PROPERTY_CHANGE;
					validArg = true;
				} else {
					currentFlags = FLAGS_NULL_PROPERTY_CHANGE;
					validArg = false;
				}
			}

			// enable will be used in the case of collision. 
			if ((currentFlags & enableFlags) != 0)
				setEnabled(true);
			else if ((currentFlags & disableFlags) != 0)
				setEnabled(false);
		}
	};

	protected DataSourceService dsData = new DataSourceService(
			propertyChangeListener);

	/**
	 * The log(xxx) methods are called in case of a failure.  
	 * Data Objects and binders do not intend to provide any error 
	 * validation framework. The default logging support will 
	 * use the error stream if the -Djve.debug system property
	 * is defined.
	 * 
	 * One can override these methods to hook up to a particular error 
	 * validation/framework
	 *
	 * @param t Exceptions
	 */
	protected void log(Throwable t) {
		if (debug && t != null) {
			t.printStackTrace();
		}
		if (t != null)
			fdebugMsg = t.getMessage();
		else
			fdebugMsg = null;
	}

	protected void log(String s) {
		if (debug && s != null)
			System.err.println(s);
		fdebugMsg = s;
	}

	/**
	 * Respond to an action, by invoking the data source service.  Before the service is invoked,
	 * the registered ActionBinderListners will be notified by the beforeActionPerformed event.  
	 * After the service call is complete, the registered listeners will be notified by the 
	 * afterActionPerformed event.
	 */
	public void actionPerformed(ActionEvent e) {
		fireBeforeActionPerformed();
		try {
			Object result = dsData.invokeDataSourceService();
			log((String) null);
			if (dsData.getType() != null) {
				actionResult.setSourceObject(result);
			}
		} catch (IllegalStateException err) {
			log(err);
		}
		fireActionPerformed();

		int currentFlags = validArg ? FLAGS_ACTION_PERFORMED_OBJECT
				: FLAGS_ACTION_PERFORMED_NULL_OBJECT;
		// enable will be used in the case of collision. 
		if ((enableFlags & currentFlags) != 0)
			setEnabled(true);
		else if ((disableFlags & currentFlags) != 0)
			setEnabled(false);
		fireAfterActionPerformed();
	}

	/**
	 * Fire an actionPeformed event.  This will call actionPerformed on all of the
	 * service method's arguments.
	 */
	protected void fireActionPerformed() {
		dsData.actionPerformed(this);
	}

	/**
	 * Send a beforeActionPerformed event to all of the registered ActionBinderListeners.
	 *
	 */
	protected void fireBeforeActionPerformed() {
		Iterator itr = actionBinderListeners.iterator();
		while (itr.hasNext()) {
			((ActionBinderListener) itr.next())
					.beforeActionPerformed(actionBinderEvent);
		}
	}

	/**
	 * Send a afterActionPerformed event to all of the registered ActionBinderListeners.
	 *
	 */
	protected void fireAfterActionPerformed() {
		Iterator itr = actionBinderListeners.iterator();
		while (itr.hasNext()) {
			((ActionBinderListener) itr.next())
					.afterActionPerformed(actionBinderEvent);
		}
	}

	public String getServiceSignature() {
		return dsData.getServiceSignature();
	}

	public void setServiceSignature(String signature) {
		dsData.setServiceSignature(signature);
	}

	public IDataSource getDataSource() {
		return dsData.getDataSource();
	}

	public void setDataSource(IDataSource datasource) {
		dsData.setDataSource(datasource);
	}

	/**
	 * @deprecated - use getEnableFlags or getEnableflags
	 */
	public int getActionType() {
		return -1;
	}

	/**
	 * @deprecated - use setEnableFlags or setDisableflags
	 */
	public void setActionType(int type) {
		switch (type) {
		case ENABLE_NO_ACTION:
			setEnableFlags(FLAGS_NONE);
			setDisableFlags(FLAGS_NONE);
			break;
		case ENABLE_ENABLE_ACTION:
			setEnableFlags(FLAGS_PROPERTY_CHANGE | FLAGS_ACTION_PERFORMED);
			setDisableFlags(FLAGS_OBJECT_CHANGE | FLAGS_NULL_OBJECT_CHANGE
					| FLAGS_NULL_PROPERTY_CHANGE);
			break;
		case ENABLE_DISABLE_ACTION:
			setEnableFlags(FLAGS_OBJECT_CHANGE);
			setDisableFlags(FLAGS_NULL_OBJECT_CHANGE
					| FLAGS_NULL_PROPERTY_CHANGE | FLAGS_PROPERTY_CHANGE);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public void setServiceArgument(ObjectReference arg) {
		dsData.setServiceArgument(arg);
	}

	public ObjectReference getServiceArgument() {
		return dsData.getServiceArgument();
	}

	public void setEnabled(boolean newValue) {
		// Binder may be out of step with the visual... 
		// so make sure that they are in sync
		super.setEnabled(!newValue);
		super.setEnabled(newValue);
	}

	public void removeActionBinderListener(ActionBinderListener l) {
		actionBinderListeners.remove(l);
	}

	public void setDisableFlags(int flags) {
		// valid and no conflict
		if ((flags & ~allFlags) != 0 || (flags & enableFlags) != 0)
			throw new IllegalArgumentException();
		disableFlags = flags;
	}

	/**
	 * Returns the flags which configure when the binder will 
	 * disable the visual.
	 * 
	 * @return Disable flags
	 */
	public int getDisableFlags() {
		return disableFlags;
	}

	public void setEnableFlags(int flags) {
		// valid and no conflict
		if ((flags & ~allFlags) != 0 || (flags & enableFlags) != 0)
			throw new IllegalArgumentException();
		enableFlags = flags;
	}

	/**
	 * Returns the flags which configure when the binder will 
	 * enable the visual.
	 * 
	 * @return Enable flags
	 */
	public int getEnableFlags() {
		return enableFlags;
	}

	protected String getErrorMessage() {
		return fdebugMsg;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (getErrorMessage() != null) {
			sb.append("***Error***: ");
			sb.append(getErrorMessage());
			sb.append("\n");
		}
		sb.append("SwingDataServiceAction\nFlags:\n");
		sb.append("\tenable: " + Integer.toHexString(enableFlags));
		sb.append("\n\tdisable: " + Integer.toHexString(disableFlags));
		sb.append("\n");
		sb.append("Result\n");
		sb.append(actionResult.toString());
		return sb.toString();
	}

	public void setValue(String property, Object value) {
		throw new IllegalAccessError("can not change action's result");
	}

	public boolean isReadOnly(String property) throws IllegalArgumentException {
		return actionResult.isReadOnly(property);
	}

	public boolean isPrimitive(String property) throws IllegalArgumentException {
		return actionResult.isPrimitive(property);
	}

	public void setProperty(String property) {
		throw new IllegalAccessError("can not change action's result");
	}

	public String getProperty() {
		return null;
	}

	public void setSourceObject(Object object) {
		throw new IllegalAccessError("can not change action's result");
	}

	public void setSourceObject(Object object, String property) {
		throw new IllegalAccessError("can not change action's result");
	}

	public Object getSourceObject() {
		return getObject();
	}

	public Object getObject() {
		return actionResult.getObject();
	}

	public void actionPerformed(IActionBinder action) {
	}

	public void refresh() {
	}

	public Class getType() throws IllegalStateException {
		return dsData.getType();
	}

	public void addActionBinderListener(ActionBinderListener l) {
		if (!actionBinderListeners.contains(l))
			actionBinderListeners.add(l);
	}

	public synchronized void addPropertyChangeListener(
			PropertyChangeListener listener) {
		super.addPropertyChangeListener(listener);
		actionResult.addPropertyChangeListener(listener);
	}

	public Object getValue(String property) {
		return actionResult.getValue(property);
	}
}