package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.NumberFormatter;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.player.ManagedPlayer;


import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainWindow {

	public final JFrame mainWindow = new JFrame();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNew = new JMenuItem("New");
	private final JMenuItem mntmLoad = new JMenuItem("Load");
	private final JMenuItem mntmSaveAs = new JMenuItem("SaveAs");
	private final JMenuItem mntmSave = new JMenuItem("Save");
	private final JMenu mnOptions = new JMenu("Options");
	private final JMenuItem mntmSerialComPort = new JMenuItem("Serial COM Port Configuration");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmAbout = new JMenuItem("About");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JFXPanel fxPanel = new JFXPanel();
	private final JPanel panel = new JPanel();
	private final JButton btnPlay = new JButton("Play");
	private final JButton btnPause = new JButton("Pause");
	private final JButton btnStop = new JButton("Stop");
	private final JSlider slider = new JSlider();
	private final JLabel lblSongDuration = new JLabel("00:00");
	private final static JLabel lblSongTitle = new JLabel("Current Song Title");
	private JFormattedTextField delay;
	private final JLabel lblDelay = new JLabel("Delay:");
	
	private static ManagedPlayer mplayer = new ManagedPlayer();
	private static List<File> playlist = new ArrayList<File>();
	
	public MainWindow() {
		NumberFormat fmt = NumberFormat.getInstance();
		NumberFormatter nf = new NumberFormatter(fmt);
		nf.setValueClass(Integer.class);
		nf.setMinimum(0);
		nf.setMaximum(Integer.MAX_VALUE);
		nf.setAllowsInvalid(false);
		nf.setCommitsOnValidEdit(false);
		delay = new JFormattedTextField(nf);
		delay.setHorizontalAlignment(SwingConstants.CENTER);
		delay.setToolTipText("Synthesizer Delay");
		delay.setColumns(3);
		
		initialize();
	}

	public void initialize() {
		//mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainWindow.setTitle("ProjectEDI");
		mainWindow.setBounds(100, 100, 310, 150);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setJMenuBar(menuBar);
		mainWindow.getContentPane().add(scrollPane, BorderLayout.WEST);
		mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.add(btnPlay);
		panel.add(btnPause);
		panel.add(btnStop);
		
		panel.add(lblDelay);
		
		panel.add(delay);
		panel.add(slider);
		panel.add(lblSongDuration);
		panel.add(lblSongTitle);
		//mainWindow.getContentPane().add(fxPanel);
		
		menuBar.add(mnFile);
		mnFile.add(mntmNew);
		mnFile.add(mntmLoad);
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmSave);
		
		menuBar.add(mnOptions);
		mnOptions.add(mntmSerialComPort);
		
		menuBar.add(mnHelp);
		mnHelp.add(mntmAbout);
		
		slider.setValue(0);
		
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
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mplayer.isPaused()) {
					mplayer.resume();
				} 
				else {
					if (playlist.size() > 0) {
						mplayer = new ManagedPlayer();
						
						try {
							mplayer.setSynthDelay(Integer.parseInt(delay.getText()));
						}
						catch (Exception e1){
							mplayer.setSynthDelay(0);
						}
						
						mplayer.getTickLength();
						
						try {
							mplayer.start(MidiFileManager.load(playlist.get(0)));
						} catch (InvalidMidiDataException | MidiUnavailableException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
		
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mplayer.isPlaying()) mplayer.pause();
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mplayer.isPlaying()) mplayer.finish();
			}
		});
		
		
		
		/*Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(fxPanel);
			}
		});*/
				
	}
	
	private static void initFX(JFXPanel fxPanel) {
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}
	
	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.ALICEBLUE);
		MediaView mv = new MediaView();
		
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
				playlist.clear();
				playlist.add(file);
				lblSongTitle.setText(playlist.get(0).getName());
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
