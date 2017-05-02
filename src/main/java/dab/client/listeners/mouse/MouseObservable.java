package dab.client.listeners.mouse;

public interface MouseObservable
{
	public void registerObserver(MouseObserver observer);
	public void unregisterObserver(MouseObserver observer);
	public void notifyObservers(long window, int key, int action, int modifier);
}
