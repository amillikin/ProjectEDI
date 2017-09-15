package arduino;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AlertWindow {
	JFrame alertWindow;
	
	public AlertWindow(Dimension obj, String title, String message) {
		alertWindow = new JFrame();
		alertWindow.setTitle(title);
		alertWindow.setSize(obj);
		alertWindow.setPreferredSize(obj);
		alertWindow.setLayout(new BorderLayout());
		alertWindow.setLocationRelativeTo(null);
		JLabel lblMessage = new JLabel(message, SwingConstants.CENTER);
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				alertWindow.setVisible(false);
				alertWindow.dispose();
			}
			
		});
		alertWindow.add(btnOk, BorderLayout.SOUTH);
		alertWindow.add(lblMessage, BorderLayout.CENTER);
	}
	
	public void display(){
		alertWindow.setVisible(true);
	}
}
