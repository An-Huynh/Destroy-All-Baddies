package dab.server.logic.components;

import dab.common.event.EventQueue;
import dab.common.logic.Component;
import dab.server.Server;

/**
 * This class is a Component that will execute and pop
 * each Event off the EventQueue as they come along.
 * 
 * @author Eli Irvin
 */
public class EventHandler implements Component
{
	/**
	 * Gets the EventQueue and then waits for it to
	 * not be empty. When it isn't empty, it will
	 * pop and process the first Event in the queue.
	 */
	@Override
	public void invoke()
	{
		EventQueue eventQueue = Server.getEventQueue();
		while (eventQueue.peek() != null)
		{
			eventQueue.pop().process();
		}
	}
}
