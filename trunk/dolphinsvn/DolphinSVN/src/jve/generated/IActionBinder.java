package jve.generated;

/**
 * IActionBinder has the ability to automatically change an enabled state on
 * an Action when the arguments for the Action change. The meaning of the
 * argument and the changes that trigger an enabled state change are up to
 * the implementation.  For example, an Action that invokes a service call could
 * change its enabled state when the parameters for that service call change. 
 */
public interface IActionBinder {

	/** Bound visual will be enabled when the argument is changed */
	public final static int ENABLE_ENABLE_ACTION = 0;

	/** Bound visual will be disabled when the argument is changed */
	public final static int ENABLE_DISABLE_ACTION = 1;

	/** Bound visual's enabled state will not change when the argument is changed */
	public final static int ENABLE_NO_ACTION = 2;

	/**
	 * Get the type of the action
	 * @return the action type. Will be ENABLE_ENABLE_ACTION, ENABLE_DISABLE_ACTION or ENABLE_NO_ACTION
	 */
	public int getActionType();

	/**
	 * Set the way the action binder will drive its enable
	 * state.  On {@link #ENABLE_ENABLE_ACTION ENABLE_ENABLE_ACTION} it will be enabled
	 * when an its argument property has changed, and vice versa when the 
	 * actionType is {@link #ENABLE_DISABLE_ACTION ENABLE_DISABLE_ACTION}.
	 * 
	 * @param type the action type.
	 */
	public void setActionType(int type);

	/**
	 * Retrieves the enabled state of the action.
	 * @return true if the action is enabled, false if it is disabled.
	 */
	public boolean isEnabled();

	/**
	 * Set the enabled state of the action.
	 * @param state true if the action should be enabled, false if disabled.
	 */
	public void setEnabled(boolean state);

	/**
	 * A action binder event is fired before and after the ActionBinder's action
	 * is performed.  This is useful to add pre and post processing logic
	 * that's triggered by performing the action. 
	 */
	public class ActionBinderEvent extends java.util.EventObject {
		public ActionBinderEvent(Object source) {
			super(source);
		}
	}

	/**
	 * Interface used to register for action triggering notification.
	 */
	public interface ActionBinderListener extends java.util.EventListener {
		/**
		 * beforeActionPerformed is called before the ActionBinder's action
		 * occurs.  The meaning of actionPeformed is dependant on the Action
		 * implementation.
		 * @param e the ActionBinderEvent
		 */
		public void beforeActionPerformed(ActionBinderEvent e);

		/**
		 * afterActionPeformed is called after the ActionBinder's action
		 * occurs.  The meaning of actionPeformed is dependant on the Action
		 * implementation.
		 * @param e the ActionBinderEvent
		 */
		public void afterActionPerformed(ActionBinderEvent e);
	}

	/**
	 * Add a new ActionBinderListner to the listener list.
	 * @param l the new listener
	 */
	public void addActionBinderListener(ActionBinderListener l);

	/**
	 * Remove an existing ActionBinderListener from the listener list.
	 * @param l the listener to remove
	 */
	public void removeActionBinderListener(ActionBinderListener l);
}