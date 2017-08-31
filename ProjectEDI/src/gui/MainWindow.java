package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.Frame;
import javax.swing.AbstractAction;
import javax.swing.Action;
import com.fazecast.jSerialComm.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

	public JFrame frmProjectedi;

	public MainWindow() {
		initialize();
	}

	public void initialize() {
		frmProjectedi = new JFrame();
		frmProjectedi.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmProjectedi.setTitle("ProjectEDI");
		frmProjectedi.setBounds(100, 100, 640, 480);
		frmProjectedi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmProjectedi.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenu mnToolbars = new JMenu("Toolbars");
		menuBar.add(mnToolbars);
		
		JMenuItem mntmMusic = new JMenuItem("Music");
		mnToolbars.add(mntmMusic);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmSerialComPort = new JMenuItem("Serial COM Port Configuration");
		mntmSerialComPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSerialComPortActPerf(arg0);
			}
		});
		mnOptions.add(mntmSerialComPort);
		
		JMenuItem mntmStaffConfiguration = new JMenuItem("Staff Configuration");
		mnOptions.add(mntmStaffConfiguration);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}
	
	private static void mntmSerialComPortActPerf(ActionEvent arg0) {
		try {
			SerialSettings dialog = new SerialSettings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
