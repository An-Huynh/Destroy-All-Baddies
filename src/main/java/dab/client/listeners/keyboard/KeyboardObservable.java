package dab.client.listeners.keyboard;


/**
 * This interface is used to follow the observer design pattern
 * with our events. This is the observable interface, the contains
 * Observers and notifies them as well
 * 
 * @author Eli Irvin
 */
public interface KeyboardObservable
{
	/**
	 * Used to add observers to an observer list.
	 * 
	 * @param observer - the KeyboardObserver to be added
	 */
	public void registerObserver(KeyboardObserver observer);
	
	/**
	 * Removes an observer from the observer list
	 * 
	 * @param observer - the KeyboardObserver to be removed
	 */
	public void unregisterObserver(KeyboardObserver observer);
	
	/**
	 * Notifies all observers to perform an action
	 * 
	 * @param key - the key that was pressed
	 * @param action - either GLFW.PRESSED, GLFW.RELEASED, or GLFW.REPEAT
	 * @param modifier - bitfield describing which modifiers keys were held down
	 */
	public void notifyObservers(int key, int action, int modifier);
}
