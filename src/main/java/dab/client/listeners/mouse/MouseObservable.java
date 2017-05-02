package dab.client.listeners.mouse;

/**
 * This interface is used to follow the observer design pattern
 * with our events. This is the observable interface, the contains
 * Observers and notifies them as well
 * 
 * @author Eli Irvin
 */
public interface MouseObservable
{
	/**
	 * Used to add observers to an observer list.
	 * 
	 * @param observer - the MouseObserver to be added
	 */
	public void registerObserver(MouseObserver observer);
	
	/**
	 * Removes an observer from the observer list
	 * 
	 * @param observer - the MouseObserver to be removed
	 */
	public void unregisterObserver(MouseObserver observer);
	
	/**
	 * Notifies the observers to perform an action
	 * 
	 * @param window - the window that received the event
	 * @param button - the mouse button that was pressed or released
	 * @param action - either a press or a release of a mouse button
	 * @param modifier - bit field describing which modifier keys were held down
	 */
	public void notifyObservers(long window, int button, int action, int modifier);
}
