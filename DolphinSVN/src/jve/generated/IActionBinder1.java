package jve.generated;

public interface IActionBinder1 extends IActionBinder {

	// The following are option flags for enabling or disabling the action visual
	// as a reaction to an argument change, or a performed action
	public final static int FLAGS_NONE = 0x0;

	// A property of an argument has changed (e.g., the 'name' property of a UserObject)
	public final static int FLAGS_PROPERTY_CHANGE = 0x1;

	// A property of an argument has been unset (set to null).
	public final static int FLAGS_NULL_PROPERTY_CHANGE = 0x2;

	// The value of an argument itself is changed (e.g., argument now points to a different UserObject)
	public final static int FLAGS_OBJECT_CHANGE = 0x4;

	// The value of an argument itself has been set to null
	public final static int FLAGS_NULL_OBJECT_CHANGE = 0x8;

	// The action is executed
	public final static int FLAGS_ACTION_PERFORMED_OBJECT = 0x10;

	// The action is executed where the argument or its property is null
	public final static int FLAGS_ACTION_PERFORMED_NULL_OBJECT = 0x20;

	// The action is performed either with a valid or null argument     
	public final static int FLAGS_ACTION_PERFORMED = FLAGS_ACTION_PERFORMED_OBJECT
			| FLAGS_ACTION_PERFORMED_NULL_OBJECT;

	/**
	 * This method is used to configure when the binder will disable
	 * the visual.
	 * 
	 * @param flags
	 */
	public void setDisableFlags(int flags);

	/**
	 * This method is used to configure when the  binder will enable
	 * the visual.
	 * @param flags
	 */
	public void setEnableFlags(int flags);
}
