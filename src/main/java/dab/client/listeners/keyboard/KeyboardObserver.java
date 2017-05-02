package dab.client.listeners.keyboard;


/**
 * This interface is used as the observer and follows
 * the Observer pattern. It simply contains an declaration
 * of an update function.
 * 
 * @author Eli Irvin
 */
public interface KeyboardObserver
{
	/**
	 * Updates the observer
	 * 
	 * @param key - the key that was pressed
	 * @param action - either GLFW.PRESSED, GLFW.RELEASED, or GLFW.REPEAT
	 * @param modifier - bitfield describing which modifiers keys were held down
	 */
	public void update(int key, int action, int modifier);
}
