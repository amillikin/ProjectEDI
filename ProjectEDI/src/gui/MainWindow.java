package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileSystemView;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.parser.Parser;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.integration.MusicXmlParser;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.Frame;
import java.awt.ScrollPane;

import javax.swing.AbstractAction;
import javax.swing.Action;
import com.fazecast.jSerialComm.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import jm.gui.cpn.JmMidiPlayer;
import jm.gui.cpn.Notate;
import jm.gui.cpn.Stave;
import jm.gui.helper.HelperGUI;
import jm.gui.show.ShowScore;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import main.Instruments;

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
	private static Instruments instruments = new Instruments();
	
	public MainWindow() {
		initialize();
	}

	public void initialize() {
		mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainWindow.setTitle("ProjectEDI");
		mainWindow.setBounds(100, 100, 640, 480);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setJMenuBar(menuBar);
		mainWindow.getContentPane().add(scrollPane, BorderLayout.WEST);
		
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
		
	}
	
	private static void mntmNew_Clicked(ActionEvent arg0) {
		try {
			Score score = new Score("New Score");
			Part part1 = new Part("Drum Kit",0,9);
			Phrase phrase1 = new Phrase(0.0);
			Note note1 = new Note(jm.constants.DrumMap.ACOUSTIC_SNARE,jm.constants.Durations.EN);
			Note note2 = new Note(jm.constants.DrumMap.ACOUSTIC_SNARE,jm.constants.Durations.EN);
			Note note3 = new Note(jm.constants.DrumMap.ACOUSTIC_SNARE,jm.constants.Durations.EN);
			Note note4 = new Note(jm.constants.DrumMap.ACOUSTIC_SNARE,jm.constants.Durations.EN);
			phrase1.add(note1);
			phrase1.add(note2);
			phrase1.add(note3);
			phrase1.add(note4);
			part1.addPhrase(phrase1);
			//score.addPart(part1);
			//Part part2 = new Part("Drum Kit",0,9);
			Phrase phrase2 = new Phrase(0.0);
			note1 = new Note(jm.constants.DrumMap.OPEN_HI_HAT,jm.constants.Durations.EN);
			note2 = new Note(jm.constants.DrumMap.CLOSED_HI_HAT,jm.constants.Durations.EN);
			note3 = new Note(jm.constants.DrumMap.CLOSED_HI_HAT,jm.constants.Durations.EN);
			note4 = new Note(jm.constants.DrumMap.CLOSED_HI_HAT,jm.constants.Durations.EN);
			phrase2.add(note1);
			phrase2.add(note2);
			phrase2.add(note3);
			phrase2.add(note4);
			part1.addPhrase(phrase2);
			score.addPart(part1);
			Notate scoreFrame = new Notate(score, 0, 0);
			scoreFrame.pack();
			scoreFrame.setVisible(true);
			/*String snare = "[ACOUSTIC_SNARE]s";
			String bass = "[ACOUSTIC_BASS_DRUM]s";
			String oHiHat = "[OPEN_HI_HAT]s";
			String cHiHat = "[CLOSED_HI_HAT]s";
			String ride = "[RIDE_CYMBAL_1]s";
			String song = snare + " " + bass + " " + snare  + " " + bass + " " + snare + " " + bass + " " + snare;
			Pattern pattern = new Pattern();
			Player player = new Player();
			player.play("V9 " + snare + " " + bass + " " + snare  + " " + bass + " " + snare + " " + bass + " " + snare);*/
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
				Pattern pattern = new Pattern();
				//pattern = MusicXmlParser.parse(file);
				//Player player = new Player();
				//player.play(pattern);
				System.out.println(pattern);
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
