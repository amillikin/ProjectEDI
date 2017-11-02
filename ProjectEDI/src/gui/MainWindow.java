package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.filechooser.FileSystemView;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.player.ManagedPlayer;


import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.io.File;

import javax.swing.JScrollPane;

public class MainWindow {

	public final JFrame mainWindow = new JFrame();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNew = new JMenuItem("New");
	private final JMenuItem mntmLoad = new JMenuItem("Load");
	private final JMenuItem mntmSaveAs = new JMenuItem("SaveAs");
	private final JMenuItem mntmSave = new JMenuItem("Save");
	private final JMenu mnToolbars = new JMenu("Toolbars");
	private final JMenuItem mntmMusic = new JMenuItem("Music");
	private final JMenu mnOptions = new JMenu("Options");
	private final JMenuItem mntmSerialComPort = new JMenuItem("Serial COM Port Configuration");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmAbout = new JMenuItem("About");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JFXPanel fxPanel = new JFXPanel();
	
	public MainWindow() {
		initialize();
	}

	public void initialize() {
		//mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainWindow.setTitle("ProjectEDI");
		mainWindow.setBounds(100, 100, 300, 100);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setJMenuBar(menuBar);
		mainWindow.getContentPane().add(scrollPane, BorderLayout.WEST);
		mainWindow.add(fxPanel);
		
		menuBar.add(mnFile);
		mnFile.add(mntmNew);
		mnFile.add(mntmLoad);
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmSave);
		
		menuBar.add(mnToolbars);
		mnToolbars.add(mntmMusic);
		
		menuBar.add(mnOptions);
		mnOptions.add(mntmSerialComPort);
		
		menuBar.add(mnHelp);
		mnHelp.add(mntmAbout);
		
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmNew_Clicked(arg0);
			}
		});
		
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmLoad_Clicked(arg0);
			}
		});
	 
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSaveAs_Clicked(arg0);
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSave_Clicked(arg0);
			}
		});
				
		mntmSerialComPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSerialComPort_Clicked(arg0);
			}
		});
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(fxPanel);
			}
		});
				
	}
	
	private static void initFX(JFXPanel fxPanel) {
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}
	
	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.ALICEBLUE);
		//MediaPlayer mp = new MediaPlayer();
		return scene;
		
	}
	
	private static void mntmNew_Clicked(ActionEvent arg0) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmLoad_Clicked(ActionEvent arg0) {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(FileSystemView.getFileSystemView().getDefaultDirectory());
			int retVal = fc.showOpenDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				ManagedPlayer mplayer = new ManagedPlayer();
				mplayer.start(MidiFileManager.load(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSaveAs_Clicked(ActionEvent arg0) {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(FileSystemView.getFileSystemView().getDefaultDirectory());
			int retVal = fc.showSaveDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				//Handle file to save
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSave_Clicked(ActionEvent arg0) {
		try {
			//If file exists, save file, else, prompt with SaveAs dialog
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSerialComPort_Clicked(ActionEvent arg0) {
		try {
			SerialConfig configWindow = new SerialConfig();
			configWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			configWindow.pack();
			configWindow.setModal(true);				
			configWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
