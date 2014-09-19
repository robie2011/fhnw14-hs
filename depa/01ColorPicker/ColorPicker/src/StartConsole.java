import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;


public class StartConsole {

	public static void main(String[] args) {
		System.out.println("Starting");
		JFrame frame = new JFrame("ColorPicker");
		frame.setSize(new Dimension(400, 300));
		
		Container pane = frame.getContentPane();
		JButton button = new JButton("Buttun1");
		button.setBackground(new Color(223,43,104));
		button.setOpaque(true);

		pane.add(button, BorderLayout.LINE_START);
		
		frame.setVisible(true);
	}

}
