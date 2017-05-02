package dab.client.listeners.mouse;

/**
 * This interface is used as the observer and follows
 * the Observer pattern. It simply contains an declaration
 * of an update function.
 * 
 * @author Eli Irvin
 */
public interface MouseObserver
{
	/**
	 * Invokes the observer
	 * 
	 * @param window - the window that received the event
	 * @param button - the mouse button that was pressed or released
	 * @param action - either GLFW.PRESSED or GLFW.RELEASED
	 * @param modifier - bitfield describing which modifiers keys were held down
	 */
	public void invoke(long window, int button, int action, int modifier);
}
