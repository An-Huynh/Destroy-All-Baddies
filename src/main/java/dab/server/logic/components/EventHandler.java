package dab.server.logic.components;

import dab.common.event.EventQueue;
import dab.common.logic.Component;
import dab.server.Server;

public class EventHandler implements Component
{
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
