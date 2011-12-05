package jve.generated;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * 
 * This helper provide the details of resolving an Object/Property string into the final object. <p>
 * For example:<br> 
 * Object=Foo, Property=address.zip will be resolved into Foo.getAddress().getZip()<br>
 * Object=Foo, Property=null will be resolved into Foo<p>
 * 
 * It serves as an element to be cached and thrown away when the underlying object
 * changes.<p>
 * 
 * Note: the object can not be a Collection or an array of Objects.
 * 
 */
public class PropertyHelper {

	protected int nesting = -1; // Number of property hops

	protected Object objectsChain[] = null; // [0] is the root object (for a potential nested property)

	protected String propertiesChain[]; // No properties implies fObjects[0] is it.

	protected PropertyDescriptor descriptorsChain[] = null;

	protected Object soureObject;

	protected String property;

	protected Class type = null;

	/**
	 * Create a property helper for the given object and property string.
	 * @param o the target object
	 * @param prop the property on the target object.  May be null.
	 */
	public PropertyHelper(Object o, String prop) {
		if (o instanceof Collection || o instanceof Object[])
			throw new InvalidParameterException("Invalid type");
		soureObject = o;
		property = prop;
	}

	/**
	 * Create a property helper for the given ObjectReference.  The helper will
	 * be initialized with the ObjectReference's object and property values.
	 * 
	 * @param val  the referenced object to resolve.
	 */
	public PropertyHelper(ObjectReference val) {
		soureObject = val.getSourceObject();
		if (soureObject instanceof Collection
				|| soureObject instanceof Object[])
			throw new InvalidParameterException("Invalid type");
		property = val.getProperty();
	}

	/**
	 * This object could have been constructed using an ObjectReference
	 * that has not been populated yet. This method will lazily parse the 
	 * ObjectReference.
	 * 
	 * @param b the object the ObjectReference is bound to
	 * @param p the property to bind to
	 */
	protected void initialize(Object b, String p) {
		if (objectsChain == null) {
			if (b instanceof IBoundObject)
				b = ((IBoundObject) b).getObject();
			// Tokenize the properties
			if (p != null && p.length() > 0 && b != null) {
				StringTokenizer st = new StringTokenizer(p,
						IBoundObject.DELIMITER);
				nesting = st.countTokens();
				propertiesChain = new String[nesting];
				descriptorsChain = new PropertyDescriptor[nesting];
				objectsChain = new Object[nesting];
				int i = 0;
				while (st.hasMoreElements()) {
					propertiesChain[i] = st.nextToken();
					if (i == 0)
						objectsChain[i] = b;
					else
						objectsChain[i] = null;
					descriptorsChain[i++] = null;
				}
			} else {
				if (b == null)
					objectsChain = new Object[] { null };
				else {
					objectsChain = new Object[] { b };
					nesting = 1;
					descriptorsChain = new PropertyDescriptor[nesting];
				}
				propertiesChain = new String[0];
			}
		}
	}

	/**
	 * The a property descriptor for a particular hop
	 * 
	 * @param index the index in the properties chain. i.e. bar in foo.bar.baz would be 1.
	 * @return the property descriptor for the property at the given index.
	 */
	protected PropertyDescriptor getPropertyDescriptor(int index) {
		if (index < 0 || objectsChain[index] == null
				|| index >= propertiesChain.length)
			return null;
		if (descriptorsChain[index] != null)
			return descriptorsChain[index];
		try {
			Class clazz = objectsChain[index].getClass();
			// BeanInfo caches property descriptors already
			BeanInfo bi = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] descriptors = bi.getPropertyDescriptors();
			for (int i = 0; i < descriptors.length; i++) {
				if (descriptors[i].getName().equals(propertiesChain[index])) {
					descriptorsChain[index] = descriptors[i];
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return descriptorsChain[index];
	}

	/**
	 * Get the value of a property from a given bean
	 * @param bean the bean to target
	 * @param pd the descriptor of the target property
	 * 
	 * @return the value retrieved by calling the get method of the property on the bean 
	 */
	protected Object getValue(Object bean, PropertyDescriptor pd) {
		try {
			return pd.getReadMethod().invoke(bean, new Object[0]);
		} catch (Exception e) {
			throw new InvalidParameterException(pd.getName());
		}
	}

	/**
	 * Set the value of a property to a given bean
	 * 
	 * @param bean the bean to target
	 * @param pd the descriptor for the target property
	 * @param value the value to set
	 */
	protected void setValue(Object bean, PropertyDescriptor pd, Object value) {
		try {
			pd.getWriteMethod().invoke(bean, new Object[] { value });
		} catch (Exception e) {
			throw new InvalidParameterException(pd.getName());
		}
	}

	/**
	 * Get the object value for the property at the given index.
	 * @param index the property index in the dotted property
	 * @return the value of the given property
	 */
	protected Object getObject(int index) {
		if (index == 0)
			return objectsChain[0];
		if (objectsChain[index] != null)
			return objectsChain[index];
		Object parent = getObject(index - 1);
		if (parent == null)
			return null;
		PropertyDescriptor pd = getPropertyDescriptor(index - 1);
		Object obj;
		if (pd != null)
			obj = getValue(parent, pd);
		else
			obj = null;
		objectsChain[index] = obj;
		return obj;
	}

	/**
	 * Get the final value of the property (the last property in the
	 * dotted property value).
	 * @return the property's value
	 */
	public Object getValue() {
		initialize(soureObject, property);
		// No property implies the object itself
		if (propertiesChain == null || propertiesChain.length == 0)
			return objectsChain[0];
		Object obj = getObject(nesting - 1);
		return getValue(obj, getPropertyDescriptor(nesting - 1));
	}

	/**
	 * Check to see if the target property is read only.  It is read
	 * only if does not have a write (set) method.
	 * @return true if the property cannot be written, false otherwise.
	 */
	public boolean isReadOnly() {
		getValue();
		PropertyDescriptor pd = getPropertyDescriptor(nesting - 1);
		return (pd == null || pd.getWriteMethod() == null);
	}

	/**
	 * Check to see if the target property's type is a Java primitive
	 * type.
	 * 
	 * @return true if it is a primitive type, false otherwise.
	 */
	public boolean isPrimitive() {
		getValue();
		PropertyDescriptor pd = getPropertyDescriptor(nesting - 1);
		try {
			if (pd != null) {
				Class c = pd.getReadMethod().getReturnType();
				return c.isPrimitive();
			}
		} catch (RuntimeException e) {
		}
		throw new IllegalAccessError();

	}

	/**
	 * Set the value of the target property to the given value.
	 * @param val the value to set
	 */
	public void setValue(Object val) {
		initialize(soureObject, property);
		Object obj = getObject(nesting - 1);
		PropertyDescriptor pd = getPropertyDescriptor(nesting - 1);
		if (pd == null) {
			if (property == null) {
				objectsChain[0] = soureObject = val;
			}
		} else
			setValue(obj, pd, val);
	}

	public String toString() {
		return "[" + property + "]";
	}

	/**
	 * Get the type of the given dotted property, rooted from the given
	 * class.
	 * 
	 * @param root the base class to retrieve the property from
	 * @param property the target property (may be dotted - foo.bar.baz)
	 * @return the type of the target property
	 */
	public static Class getType(Class root, String property) {
		if (property == null || property.length() == 0)
			return root;

		StringTokenizer st = new StringTokenizer(property,
				IBoundObject.DELIMITER);
		Class result = root;
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			BeanInfo bi;
			try {
				bi = Introspector.getBeanInfo(result);
			} catch (IntrospectionException e) {
				e.printStackTrace();
				return null;
			}
			PropertyDescriptor[] descriptors = bi.getPropertyDescriptors();
			PropertyDescriptor desc = null;
			for (int i = 0; i < descriptors.length; i++) {
				if (descriptors[i].getName().equals(token)) {
					desc = descriptors[i];
					break;
				}
			}
			if (desc == null)
				throw new InvalidParameterException("Invalid property "
						+ property);
			result = desc.getPropertyType();
		}
		return result;
	}

	/**
	 * Get the type of the property for this PropertyHelper.
	 * @return the type of the property.
	 * @throws IllegalStateException if the property value is invalid.
	 */
	public Class getType() throws IllegalStateException {
		if (type != null)
			return type;

		if (soureObject != null) {
			Class startClass;
			if (soureObject instanceof IBoundObject)
				startClass = ((IBoundObject) soureObject).getType();
			else
				startClass = soureObject.getClass();
			type = getType(startClass, property);
		}
		return type;
	}
}