package dab.client.event.mouse;

public interface M_Observable {
	public void registerObserver(M_Observer observer);
	public void unregisterObserver(M_Observer observer);
	public void notifyObservers(long window, int button, int action, int modifier);
}
