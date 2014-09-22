package robertrajakone.colorpicker;

public interface Observable {
	void addObserver(Observer obj);
	void removeObserver(Observer obj);
	void updateObservers();
}
