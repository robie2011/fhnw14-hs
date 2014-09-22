package robertrajakone.colorpicker;

import java.util.ArrayList;

public class ColorModelAbstract implements Observable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	int value = 0;
	
	
	public void setValue(int v){
		int valueBefore = value;
		if(v>255) value = 255;
		else if(v<0) value = 0;
		else value = v;
		
		if(valueBefore != value) updateObservers();
	}

	@Override
	public void addObserver(Observer obj) {
		observers.add(obj);		
	}

	@Override
	public void removeObserver(Observer obj) {
		this.observers.remove(obj);
	}

	@Override
	public void updateObservers() {
		if(observers.size()==0)return;
		for (Observer observer : observers) {
			observer.doUpdate();
		}
		
	}

	public int getValue() {
		return value;
	}

}
