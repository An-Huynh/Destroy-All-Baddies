package dab.server.logic.component;

import java.io.IOException;
import java.util.ArrayList;

import dab.common.logic.Event;

public class EventUpdater implements Tickable_S {

	public static ArrayList<Event> events = new ArrayList<Event>();
	
	@Override
	public void invoke() throws IOException {
		if(!events.isEmpty()) {
			events.forEach(event -> event.process());
			events.clear();
		}
	}
}
