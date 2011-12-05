package jve.generated;

/**
 *  
 * This interface extends the object reference capability of IBoundObject to allow the
 * dynamic getting and setting of property values on the referenced object.  The property
 * names are deliminated by the DELIMITER character, allowing nesting into sub-properties
 * (e.g. contact.address.zip).
 */
public interface IDataObject extends IBoundObject {

	/**
	 * Use the getter of an object to get a property
	 * @param property the property to get from the target object
	 * @return property value
	 */
	public Object getValue(String property);

	/**
	 * Use the setter of an object to set a property.  Using this method will
	 * fire a single PropertyChange event to registered PropertyChangeListeners.
	 * @param property property to set on the target object
	 * @param value the value to set
	 */
	public void setValue(String property, Object value);

	/**
	 * Check if the given property has a setter method.
	 * @param property the property to check
	 * @return true, if the property does not have a setter method, false otherwise
	 * @throws IllegalArgumentException if the target property is invalid
	 */
	public boolean isReadOnly(String property) throws IllegalArgumentException;

	/**
	 * Get to see if the type of the given property is a Java primitive type.
	 * @param property the property to check
	 * @return true if the property is a primitive type, false otherwise
	 * @throws IllegalArgumentException if the target property is invalid
	 */
	public boolean isPrimitive(String property) throws IllegalArgumentException;
}