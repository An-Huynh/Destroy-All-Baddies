package dab.common.event;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is a Queue of Events that can be pushed onto and
 * popped off of. It works like a Queue in that the things in
 * first will be the first to pop off of the Queue.
 * 
 * @author Eli Irvin
 */
public class EventQueue
{
	private Queue<Event> events;
	
	/**
	 * Default Constructor for the EventQueue
	 */
	public EventQueue()
	{
		events = new LinkedList<Event>();
	}
	
	/**
	 * Pushes an event into the Queue
	 * 
	 * @param event - the Event to be pushed
	 */
	public void push(Event event)
	{
		events.add(event);
	}
	
	/**
	 * Pops an event off of the Queue
	 * 
	 * @return the Event that was popped off the front
	 */
	public Event pop()
	{
		return events.poll();
	}
	
	/**
	 * Peeks into the queue and retrieves the front of it
	 * without removing it
	 * 
	 * @return the Event at the front of the Queue
	 */
	public Event peek()
	{
		return events.peek();
	}
}
