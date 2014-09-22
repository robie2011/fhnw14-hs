package robertrajakone.colorpicker;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import robertrajakone.colorpicker.view.ColorSlider;
import robertrajakone.colorpicker.view.ColorText;

@SuppressWarnings("serial")
public class ColorFrame extends JFrame {
	ColorModelAbstract red;
	
	public ColorFrame(ColorModelAbstract red){
		this.red = red;
		initLayout();
		addComponents();
	}
	
	void initLayout(){
		setLayout(new GridBagLayout());
	}
	
	void addComponents(){
		ColorSlider slider = new ColorSlider(red);
		add(slider);
		
		add(new ColorText(red));
		
	}
	
	void addSlide1Observer(){
		
	}
}
