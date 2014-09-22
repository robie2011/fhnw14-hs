package robertrajakone.colorpicker.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import robertrajakone.colorpicker.ColorModelAbstract;
import robertrajakone.colorpicker.Observer;

@SuppressWarnings("serial")
public class ColorText extends JTextField implements Observer {
	ColorModelAbstract model;
	public ColorText(ColorModelAbstract m){
		setPreferredSize(new Dimension(50,25));		
		model = m;		
		model.addObserver(this);
		doUpdate();
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setValue(Integer.parseInt(getText()));
				
			}
		});
	}
	@Override
	public void doUpdate() {
		setText( Integer.toString(model.getValue()) );
	}
	
	
}
