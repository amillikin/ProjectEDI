package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class ButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AbstractButton button = (AbstractButton) arg0.getSource();
		if (button.getBackground() == (new JButton().getBackground())) {
			button.setBackground(Color.GREEN);
		}
		else {
			button.setBackground((new JButton().getBackground()));
		}
	}

}
