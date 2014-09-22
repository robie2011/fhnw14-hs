package robertrajakone.colorpicker.view;
import robertrajakone.colorpicker.ColorModelAbstract;
import robertrajakone.colorpicker.Observer;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorSlider extends JSlider implements Observer {
	private ColorModelAbstract model;
	
	public ColorSlider(ColorModelAbstract m){
		this.model = m;		
		setMaximum(255);
		setMinimum(0);
		doUpdate();
		
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ColorSlider slider = (ColorSlider) e.getSource();
				slider.model.setValue(slider.getValue());
			}
		});
	}

	@Override
	public void doUpdate() {	
		if (model.getValue() != getValue())
			setValue(model.getValue());
	}
	
}
