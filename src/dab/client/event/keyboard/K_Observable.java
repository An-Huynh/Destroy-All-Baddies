package dab.client.event.keyboard;

public interface K_Observable {
	public void registerObserver(K_Observer observer);
	public void unregisterObserver(K_Observer observer);
	public void notifyObservers(int key, int action, int modifier);
}
