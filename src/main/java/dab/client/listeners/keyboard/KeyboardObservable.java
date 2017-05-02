package dab.client.listeners.keyboard;

public interface KeyboardObservable
{
	public void registerObserver(KeyboardObserver observer);
	public void unregisterObserver(KeyboardObserver observer);
	public void notifyObservers(int key, int action, int modifier);
}
