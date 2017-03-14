package dab.client.gui

/**
 * Interface for windows
 * 
 * @author Eli Irvin
 */

public interface Window {
	
	/*
	 * Activates the window
	 */
	public void activate();
	
	/*
	 * Deactivates the window
	 */
	public void deactivate();
	
	/*
	 * Registers an event listener to the window
	 */
	public void registerEventListener();
}
