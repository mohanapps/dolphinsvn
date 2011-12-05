package jve.generated;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;

/**
 * Default implementation of a data object.
 */
public class BasicDataObject extends ObjectReference implements IDataObject {

	private HashMap targetPropertyHelpers = new HashMap(); // per [nested] property helper -CONTENT

	private ThreadLocal changingProperty = new ThreadLocal();

	public BasicDataObject() {
		super();
	}

	public BasicDataObject(Object source, String prop) {
		super(source, prop);
	}

	public void setSourceObject(Object obj) {
		targetPropertyHelpers.clear();
		super.setSourceObject(obj);
	}

	/**
	 * Get the value of the given property from the source object. <br>
	 * If the source object is another IDataObject, the getValue call will
	 * be delegated to the source object.
	 * 
	 * @param property the property to retrieve
	 * @return the value of the property, or null if there's a problem retrieving the value. 
	 */
	public Object getValue(String property) {
		try {
			if (getObject() == null)
				return null;
			log((String) null);
			if (getSourceObject() instanceof IDataObject) {
				return ((IDataObject) getSourceObject()).getValue(property);
			}
			return getHelper(property).getValue();
		} catch (RuntimeException e) {
			log(e);
		}
		return null;
	}

	/**
	 * Get the property helper for the given property.  The property helper
	 * is cached.  If a helper for the given property isn't available, one is
	 * created.
	 * 
	 * @param property the target property
	 * @return the PropertyHelper for this property
	 */
	protected PropertyHelper getHelper(String property) {
		PropertyHelper h = (PropertyHelper) targetPropertyHelpers.get(property);
		if (h == null) {
			h = new PropertyHelper(getObject(), property);
			targetPropertyHelpers.put(property, h);
		}
		return h;
	}

	/**
	 * Set the value of the given property on the source object to the given value. <br>
	 * If the source object is another IDataObject, the setValue call will
	 * be delegated to the source object.
	 * 
	 * @param property the property to set
	 * @param value the value of the target property
	 */
	public void setValue(String property, Object value) {
		try {
			if (getObject() == null)
				return;
			// If the property is bound, do not propogate it twice from the same thread
			changingProperty.set(Boolean.TRUE);
			Object old = getValue(property);
			// If the source is a Data Object, let the DO be
			// the one that resolve the property value, and
			// ensure it fires a property change event as well
			if (getSourceObject() instanceof IDataObject)
				((IDataObject) getSourceObject()).setValue(property, value);
			else
				getHelper(property).setValue(value);
			changingProperty.set(null);
			firePropertyChanged(property, old, value);
			log((String) null);
		} catch (Exception e) {
			log(e);
		} finally {
			changingProperty.set(null);
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (getErrorMessage() != null) {
			sb.append("***Error**: ");
			sb.append(getErrorMessage());
			sb.append("\n");
		}
		sb.append("SimpleDataObject\n\tObject: ");
		sb.append(getSourceObject());
		sb.append("\n\tProperty: ");
		sb.append(getProperty());
		sb.append("\n");
		return sb.toString();
	}

	protected void firePropertyChanged(PropertyChangeEvent event) {
		targetPropertyHelpers.clear();
		// Do not fire bound properties if they are caused by a call to setValue()
		if (changingProperty.get() == null)
			super.firePropertyChanged(event);
	}

	public void refresh() {
		targetPropertyHelpers.clear();
		super.refresh();
	}

	public boolean isReadOnly(String property) throws IllegalArgumentException {
		try {
			if (getSourceObject() instanceof IDataObject) {
				return ((IDataObject) getSourceObject()).isReadOnly(property);
			} else if (property != null)
				return getHelper(property).isReadOnly();
			else
				return false;
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("Invalid property " + property);
		}
	}

	public boolean isPrimitive(String property) throws IllegalArgumentException {
		try {
			if (property != null)
				return getHelper(property).isPrimitive();
			else if (getSourceObject() != null)
				return getSourceObject().getClass().isPrimitive();
			else
				return false;
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("Invalid property " + property);
		}
	}
}