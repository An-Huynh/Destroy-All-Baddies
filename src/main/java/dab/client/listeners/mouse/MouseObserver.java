package dab.client.listeners.mouse;

public interface MouseObserver
{
	public void invoke(long window, int button, int action, int modifier);
}
