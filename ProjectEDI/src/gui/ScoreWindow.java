package gui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JMenu;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.swing.JButton;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.temporal.TemporalEvent;
import org.jfugue.temporal.TemporalEvents.NoteEvent;
import org.jfugue.temporal.TemporalEvents.TempoEvent;
import org.jfugue.temporal.TemporalPLP;
import org.staccato.StaccatoParser;

import playlist.Playlist;
import playlist.Song;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class ScoreWindow extends JDialog {
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNew = new JMenuItem("New ");
	private final JMenuItem mntmLoad = new JMenuItem("Load");
	private final JMenuItem mntmSaveAs = new JMenuItem("SaveAs");
	private final JMenuItem mntmSave = new JMenuItem("Save");
    private final JButton btnPlay = new JButton("Play");
    private final JButton btnStop = new JButton("Stop");
    private final static JCheckBox chckbxLoop = new JCheckBox("Loop");
    private final Component horizontalStrut = Box.createHorizontalStrut(75);
    private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);
    private final Component horizontalStrut_2 = Box.createHorizontalStrut(20);
	private static final FileFilter midiFilter = new FileNameExtensionFilter("MIDI","mid");
	private ButtonListener buttonListener = new ButtonListener();
	private static Timer timer;
	private static int lastColumn;
	private static File lastPlaylistFile = new File("");
	private NumberFormat nbrfmt = NumberFormat.getNumberInstance();
	private static JFormattedTextField txtTempo;
	private final JToolBar toolBar = new JToolBar();
	private final JLabel tempoLbl = new JLabel("Tempo:  ");
	private static ManagedPlayer mplayer = new ManagedPlayer();
	private static JButton[][] buttons = new JButton[8][16];
	private static JButton[][] buttonsCopy = new JButton[8][16];
	private JLabel[] labels = new JLabel[] {new JLabel("Snare"),
											new JLabel("Closed Hi-Hat"),
											new JLabel("Open Hi-Hat"),
											new JLabel("Bass"),
											new JLabel("Low Tom"),
											new JLabel("High Tom"),
											new JLabel("Ride Cymbal"),
											new JLabel("Crash Cymbal")};

	public ScoreWindow() {
		initialize();
	}
	
	private void initialize() {
		setResizable(false);
		setJMenuBar(menuBar);
		menuBar.add(mnFile);
		mnFile.add(mntmNew);
		mnFile.add(mntmLoad);
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmSave);
		
		menuBar.add(horizontalStrut);
		toolBar.setFloatable(false);
		menuBar.add(toolBar);
		nbrfmt.setMaximumIntegerDigits(3);
		txtTempo = new JFormattedTextField(nbrfmt);
		txtTempo.setToolTipText("");
		txtTempo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTempo.setColumns(3);
		txtTempo.setText("100");
		txtTempo.setMaximumSize(txtTempo.getPreferredSize());
		toolBar.add(horizontalStrut_2);
		toolBar.add(btnPlay);
		toolBar.add(btnStop);
		toolBar.add(chckbxLoop);
		toolBar.add(horizontalStrut_1);
		tempoLbl.setMaximumSize(tempoLbl.getPreferredSize());
		toolBar.add(tempoLbl);
		toolBar.add(txtTempo);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
		//Add buttons and labels to grid
		for (int i = 0; i < 8; i++) {
			GridBagConstraints gbcLabel = new GridBagConstraints();
			gbcLabel.fill = GridBagConstraints.BOTH;
			gbcLabel.insets = new Insets(0,0,5,5);
			gbcLabel.gridx = 0;
			gbcLabel.gridy = i;
			labels[i].setFont(new Font("Tahoma", Font.BOLD, 12));
			getContentPane().add(labels[i], gbcLabel);
			
			for (int j = 0; j < 16; j++) {
				GridBagConstraints gbcButton = new GridBagConstraints();
				gbcButton.fill = GridBagConstraints.BOTH;
				gbcButton.insets = new Insets(0,0,5,5);
				gbcButton.gridx = j+1;
				gbcButton.gridy = i;
				buttons[i][j] = new JButton("");
				buttons[i][j].addActionListener(buttonListener);
				getContentPane().add(buttons[i][j], gbcButton);
			}
		}
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPlay_Clicked();
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop_Clicked();
			}
		});
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmNew_Clicked();
			}
		});
		
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmLoad_Clicked();
			}
		});
	 
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSaveAs_Clicked();
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSave_Clicked();
			}
		});
		
		timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerEvent();
			}
		});
	}
	
	private static void btnPlay_Clicked() {
		if (mplayer.isPlaying()) mplayer.finish();
			
		mplayer = new ManagedPlayer();
		try {
			buttonsCopy = buttons;
			lastColumn = 0;
			mplayer.start(getSequence(getRhythmPattern()));
			timer.start();
		} catch (InvalidMidiDataException | MidiUnavailableException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private static void btnStop_Clicked() {
		if (mplayer.isPlaying()) {
			mplayer.finish();
			timer.stop();
		}
	}
	
	private static void mntmNew_Clicked() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 16; j++) {
				buttons[i][j].setBackground(new JButton().getBackground());
			}
		}
	}
	
	private static void mntmLoad_Clicked() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(midiFilter);
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int retVal = fc.showOpenDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if (file.getName().endsWith(".mid")) {
					mntmNew_Clicked();
					loadEDI(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSaveAs_Clicked() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(midiFilter);
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int retVal = fc.showSaveDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				File saveFile = file.getName().endsWith(".mid") ? file : new File(file.getAbsolutePath() + ".mid");
				MidiFileManager.savePatternToMidi(getRhythmPattern(), saveFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSave_Clicked() {
		try {
			if (lastPlaylistFile.exists()) {
				MidiFileManager.savePatternToMidi(getRhythmPattern(), lastPlaylistFile);
			}
			else {
				mntmSaveAs_Clicked();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void timerEvent() {
		
		if (mplayer.isFinished()) {
			timer.stop();
			if (chckbxLoop.isSelected()) {
				btnPlay_Clicked();
			}
		}
		/*if (mplayer.isPlaying()) {
			buttons[0][lastColumn].setBackground(buttonsCopy[0][lastColumn].getBackground());
			buttons[1][lastColumn].setBackground(buttonsCopy[1][lastColumn].getBackground());
			buttons[2][lastColumn].setBackground(buttonsCopy[2][lastColumn].getBackground());
			buttons[3][lastColumn].setBackground(buttonsCopy[3][lastColumn].getBackground());
			buttons[4][lastColumn].setBackground(buttonsCopy[4][lastColumn].getBackground());
			buttons[5][lastColumn].setBackground(buttonsCopy[5][lastColumn].getBackground());
			buttons[6][lastColumn].setBackground(buttonsCopy[6][lastColumn].getBackground());
			buttons[7][lastColumn].setBackground(buttonsCopy[7][lastColumn].getBackground());
			double percent = ((double)mplayer.getTickPosition())/((double)mplayer.getTickLength());
			int col = (int)(16.0*percent);
			buttons[0][col].setBackground(Color.RED);
			buttons[1][col].setBackground(Color.RED);
			buttons[2][col].setBackground(Color.RED);
			buttons[3][col].setBackground(Color.RED);
			buttons[4][col].setBackground(Color.RED);
			buttons[5][col].setBackground(Color.RED);
			buttons[6][col].setBackground(Color.RED);
			buttons[7][col].setBackground(Color.RED);
			lastColumn = col;
		} else if (mplayer.isFinished()) {
			timer.stop();
			if (chckbxLoop.isSelected()) {
				btnPlay_Clicked();
			}
		}
		*/
	}
	
	private static Pattern getRhythmPattern() {
		Rhythm rhythm = new Rhythm(Rhythm.ARDUINO_RHYTHM_KIT);
		String instrumentLayer = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 16; j++) {
				instrumentLayer += (buttons[i][j].getBackground() == Color.GREEN) ? rhythmBeatVal.get(i) : ".";
			}
			rhythm.addLayer(instrumentLayer);
			instrumentLayer = "";
		}
		Pattern rhythmPattern = new Pattern(rhythm);
		rhythmPattern.setTempo(Integer.parseInt(txtTempo.getText()));
		return rhythmPattern;
	}
	
	private static void loadEDI(File file) {
		try {
			Map<Long, List<TemporalEvent>> eventMap = new TreeMap<Long, List<TemporalEvent>>();
			StaccatoParser parser = new StaccatoParser();
			TemporalPLP plp = new TemporalPLP();
			int row = 0, col = 0;
			double tempoFactor = 1.0;
			Pattern pattern = MidiFileManager.loadPatternFromMidi(file);
			parser.addParserListener(plp);
			parser.parse(pattern.getPattern().toString());
			eventMap = plp.getTimeToEventMap();
			
			for (Map.Entry<Long, List<TemporalEvent>> entry : eventMap.entrySet()) {
				for (TemporalEvent event : entry.getValue()) {
					if (event.toString().contains("NoteEvent")) {
						NoteEvent ne = (NoteEvent) event;
						col = (int) (entry.getKey().doubleValue()/tempoFactor);
						byte b = ne.getNoteValue();
						if (noteRow.containsKey(Byte.toUnsignedInt(b))) {
							row = noteRow.get(Byte.toUnsignedInt(b));
							buttons[row][col].setBackground(Color.GREEN);
						}
					}
					else if (event.toString().contains("TempoEvent")) {
						TempoEvent te = (TempoEvent) event;
						txtTempo.setValue(te.getTempo());
						tempoFactor = (60.0/(double)te.getTempo())*1000.0;
					}
				}
			}
		} catch (IOException | InvalidMidiDataException e) {
			e.printStackTrace();
		}		
	}
	
	private static Sequence getSequence(Pattern pattern) {
		StaccatoParser staccatoParser = new StaccatoParser();
		MidiParserListener midiParserListener = new MidiParserListener();
		staccatoParser.addParserListener(midiParserListener);
		staccatoParser.parse(pattern);
		return midiParserListener.getSequence();
	}
	
    private static final Map<Integer, Character> rhythmBeatVal = new HashMap<Integer, Character>(){{
        put(0, 's');
        put(1, 'd');
        put(2, 'o');
        put(3, 'b');
        put(4, 'l');
        put(5, 'h');
        put(6, 'r');
        put(7, 'c');
    }};
    private static final Map<Integer, Integer> noteRow = new HashMap<Integer, Integer>(){{
        put(38, 0);
        put(42, 1);
        put(46, 2);
        put(36, 3);
        put(45, 4);
        put(50, 5);
        put(51, 6);
        put(49, 7);
    }};   
}
