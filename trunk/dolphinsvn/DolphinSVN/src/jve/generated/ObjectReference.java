package jve.generated;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a wrapper that references an Object: boundObject.
 * The boundObject is noted by [Object, property].  If a property
 * is given, the boundObject is Object.getProperty(), eithewise it boundObject == Object.
 * A property can be nested.  i.e., the boundObject for [Object, "foo.bar"] is Object.getFoo().getBar()<p>
 * 
 * The ObjectReference class provide property resolution, and event modeling support for bounded properties. 
 */
public class ObjectReference implements IBoundObject {

	private Object sourceObject; // Object (or IBoundObject)

	private String property; // Optionaly sub object property

	private String fdebugMsg = null;

	public static boolean debug = System.getProperty("jve.debug") != null;

	private PropertyHelper propertyHelper = null; // resolves a sub object

	PropertyChangeListener binderListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			firePropertyChanged(evt);
		}
	};

	private List propListeners = new ArrayList();

	/**
	 * Construct an uninitialized ObjectReference
	 */
	public ObjectReference() {
		sourceObject = null;
		property = null;
	}

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
	 * Construct an ObjectReference initialized with its source object and property.
	 * @param source the source object
	 * @param property a property on the source object (may be null)
	 */
	public ObjectReference(Object source, String property) {
		setSourceObject(source, property);
	}

	/**
	 * Return the property of the source object being referenced by this
	 * ObjectReference
	 * 
	 * @return Returns the property.
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Get the final source object being referenced.  If the source object
	 * is a IBoundObject, this will return the source's object.
	 * resolve a IBoundObject
	 */
	protected Object getPureSourceObject() {
		if (sourceObject instanceof IBoundObject)
			return ((IBoundObject) sourceObject).getObject();
		return sourceObject;
	}

	/**
	 * Create and cache a new PropertyHelper to reflect the current source object and
	 * property.  If the property is null, the PropertyHelper will be null.
	 */
	protected void refreshPropertyHelper() {
		if (property != null)
			propertyHelper = new PropertyHelper(getSourceObject(), property);
		else
			propertyHelper = null;
	}

	/**
	 * Refresh this ObjectReference.  This implementation will update the 
	 * ObjectReference's cached PropertyHelper.
	 */
	public void refresh() {
		refreshPropertyHelper();
	}

	/**
	 * Set the property to be used for this ObjectReference.  If the property
	 * is null, the ObjectReference will reference the source object directly.
	 * 
	 * @param property is the property to use on {@link #getObject #getObject}
	 */
	public void setProperty(String property) {
		if (property != null && property.length() == 0)
			property = null;
		String old = this.property;
		boolean fire = false;
		if (property != old) {
			if (property != null) {
				if (!property.equals(old))
					fire = true;
			} else {
				fire = true;
			}
		}
		if (fire) {
			this.property = property;
			refreshPropertyHelper();
			firePropertyChanged(PROPERTY_OBJ_CHANGED, old, property);
		}
	}

	/**
	 * Get the source object that's bound to this ObjectReference
	 * @return the source object
	 */
	public Object getSourceObject() {
		return sourceObject;
	}

	/**
	 * Attempt to register for property change notification on an object.
	 * 
	 * @param o the object to register with
	 * @param add true to add registration, false to remove the registration
	 */
	private void invokePropChangeListener(Object o, boolean add) {
		if (o == null)
			return;
		String method;
		if (add)
			method = "addPropertyChangeListener";
		else
			method = "removePropertyChangeListener";
		try {
			Method m = o.getClass().getMethod(method,
					new Class[] { PropertyChangeListener.class });
			m.invoke(o, new Object[] { binderListener });
		} catch (Exception e) {
		}
	}

	protected void removePropertyListeners() {
		if (sourceObject instanceof IBoundObject)
			((IBoundObject) sourceObject)
					.removePropertyChangeListener(binderListener);
		else
			invokePropChangeListener(sourceObject, false);

		if (propertyHelper != null) {
			Object val = propertyHelper.getValue();
			invokePropChangeListener(val, false);
		}
	}

	protected void addPropertyListeners() {
		if (sourceObject instanceof IBoundObject)
			((IBoundObject) sourceObject)
					.addPropertyChangeListener(binderListener);
		else
			invokePropChangeListener(sourceObject, true);

		if (propertyHelper != null) {
			Object val = propertyHelper.getValue();
			invokePropChangeListener(val, true);
		}
	}

	/**
	 * Set the source object this ObjectReference will be bound to.
	 * @param obj the source object
	 */
	public void setSourceObject(Object obj) {
		if (obj != sourceObject) {
			Object oldValue = sourceObject;
			removePropertyListeners();
			sourceObject = obj;
			refreshPropertyHelper();

			addPropertyListeners();
			firePropertyChanged(PROPERTY_OBJ_CHANGED, oldValue, obj);
		}
	}

	public void setSourceObject(Object object, String property) {
		setProperty(property);
		setSourceObject(object);
	}

	/**
	 * Get the value of this ObjectReference.  If the ObjectReference has a
	 * sourceObject and property set, the return value will be the result of
	 * calling sourceObject.get[Property]().  Otherwise the return value will
	 * be the sourceObject.
	 * 
	 * @return resolved object reference.
	 */
	public Object getObject() {
		if (propertyHelper != null)
			return propertyHelper.getValue(); // resolve the property        
		return getPureSourceObject();
	}

	/**
	 * Add a propertyChangeListener that will listen to property changes on the
	 * object being referenced (the final object, including the value of subproperties).
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		if (!propListeners.contains(l))
			propListeners.add(l);
	}

	/**
	 * Remove an existing property change listener from the listener list.
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		propListeners.remove(l);
	}

	private IActionBinder processedAction = null;

	/**
	 * Notify an argument of an action.  
	 * This action will be delegated to the sourceObject if it is a {@link IBoundObject IBoundObject}.
	 * @see IActionBinder
	 * @param action the action that has occurred.
	 */
	public void actionPerformed(IActionBinder action) {
		if (processedAction != null)
			return; // avoid cycles

		processedAction = action;
		try {
			if (sourceObject instanceof IBoundObject)
				((IBoundObject) sourceObject).actionPerformed(action);
		} finally {
			processedAction = null;
		}
	}

	protected void firePropertyChanged(String property, Object oldVal,
			Object newVal) {
		PropertyChangeEvent event = new PropertyChangeEvent(this, property,
				oldVal, newVal);
		firePropertyChanged(event);
	}

	protected void firePropertyChanged(PropertyChangeEvent event) {
		refreshPropertyHelper();
		for (int i = 0; i < propListeners.size(); i++) {
			((PropertyChangeListener) propListeners.get(i))
					.propertyChange(event);
		}
	}

	/**
	 * Get the type of the ObjectReference.  If the property is set, this will be
	 * the return type of the sourceObject.get[Property]() method.  Otherwise it
	 * will be the type of the sourceObject.
	 */
	public Class getType() throws IllegalStateException {

		if (sourceObject == null)
			return null;

		if (propertyHelper != null)
			return propertyHelper.getType();
		else if (sourceObject instanceof IBoundObject)
			return ((IBoundObject) sourceObject).getType();
		else
			return sourceObject.getClass();
	}

	protected String getErrorMessage() {
		return fdebugMsg;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (getErrorMessage() != null) {
			sb.append("***Error**: ");
			sb.append(getErrorMessage());
			sb.append("\n");
		}
		sb.append("ObjectReference\n\tObject: ");
		sb.append(sourceObject);
		sb.append("\n\tProperty: ");
		sb.append(property);
		sb.append("\n");
		return sb.toString();
	}
}