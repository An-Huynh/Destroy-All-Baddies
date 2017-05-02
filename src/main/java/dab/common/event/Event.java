package dab.common.event;

/**
 * This interface is the basis for the Events. These events simply 
 * a process method that will process each event. Having the interface
 * allows us to compile a list of all events, while keeping their
 * processes different.
 * 
 * @author Eli Irvin
 */
public interface Event
{
	/**
	 * Processes the event
	 */
	public void process();
}
