package dab.common.event;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue
{
	private Queue<Event> events;
	
	public EventQueue()
	{
		events = new LinkedList<Event>();
	}
	
	public void push(Event event)
	{
		events.add(event);
	}
	
	public Event pop()
	{
		return events.poll();
	}
	
	public Event peek()
	{
		return events.peek();
	}
}
