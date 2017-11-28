package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableColumn;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.player.ManagedPlayer;

import playlist.Song;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

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
	private final JPanel mainPanel = new JPanel();
	private final JButton btnPlay = new JButton("Play");
	private final JButton btnPause = new JButton("Pause");
	private final JButton btnStop = new JButton("Stop");
	
	private static ManagedPlayer mplayer = new ManagedPlayer();
	private static List<Song> playlist = new ArrayList<Song>();
	private static JTable playlistTable = new JTable(1,3);
	private final JPanel MediaControlPanel = new JPanel();
	private final JPanel MediaButtonsPanel = new JPanel();
	private final JProgressBar trackBar = new JProgressBar();
	
	public MainWindow() {		
		initialize();
	}

	public void initialize() {
		mainWindow.setTitle("ProjectEDI");
		mainWindow.setBounds(100, 100, 400, 350);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setJMenuBar(menuBar);
		mainWindow.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{350, 0};
		gbl_panel.rowHeights = new int[]{0, 70, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_playlistTable = new GridBagConstraints();
		gbc_playlistTable.insets = new Insets(0, 0, 5, 0);
		gbc_playlistTable.fill = GridBagConstraints.BOTH;
		gbc_playlistTable.gridx = 0;
		gbc_playlistTable.gridy = 0;
		playlistTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#", "Title", "Duration"
			}
		));
		playlistTable.setFillsViewportHeight(true);
		/*TableColumn tc1 = new TableColumn();
		tc1.setHeaderValue("#");
		TableColumn tc2 = new TableColumn();
		tc2.setHeaderValue("Title");
		TableColumn tc3 = new TableColumn();
		tc3.setHeaderValue("Duration");
		playlistTable.addColumn(tc1);
		playlistTable.addColumn(tc2);
		playlistTable.addColumn(tc3);
		playlistTable.addRowSelectionInterval(0, 1);*/
		mainPanel.add(playlistTable, gbc_playlistTable);
		
		GridBagConstraints gbc_MediaControlPanel = new GridBagConstraints();
		gbc_MediaControlPanel.fill = GridBagConstraints.BOTH;
		gbc_MediaControlPanel.gridx = 0;
		gbc_MediaControlPanel.gridy = 1;
		mainPanel.add(MediaControlPanel, gbc_MediaControlPanel);
		GridBagLayout gbl_MediaControlPanel = new GridBagLayout();
		gbl_MediaControlPanel.columnWidths = new int[]{350, 0};
		gbl_MediaControlPanel.rowHeights = new int[]{40, 25, 0};
		gbl_MediaControlPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_MediaControlPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		MediaControlPanel.setLayout(gbl_MediaControlPanel);
		
		GridBagConstraints gbc_trackBar = new GridBagConstraints();
		gbc_trackBar.fill = GridBagConstraints.BOTH;
		gbc_trackBar.insets = new Insets(0, 0, 5, 0);
		gbc_trackBar.gridx = 0;
		gbc_trackBar.gridy = 0;
		trackBar.setStringPainted(true);
		trackBar.setValue(0);
		trackBar.setString("00:00 - 00:00");
		MediaControlPanel.add(trackBar, gbc_trackBar);
		
		GridBagConstraints gbc_MediaButtonsPanel = new GridBagConstraints();
		gbc_MediaButtonsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_MediaButtonsPanel.gridx = 0;
		gbc_MediaButtonsPanel.gridy = 1;
		MediaControlPanel.add(MediaButtonsPanel, gbc_MediaButtonsPanel);
		GridBagLayout gbl_MediaButtonsPanel = new GridBagLayout();
		gbl_MediaButtonsPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_MediaButtonsPanel.rowHeights = new int[]{0, 0};
		gbl_MediaButtonsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_MediaButtonsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		MediaButtonsPanel.setLayout(gbl_MediaButtonsPanel);
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPlay.gridx = 0;
		gbc_btnPlay.gridy = 0;
		MediaButtonsPanel.add(btnPlay, gbc_btnPlay);
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 0, 0, 5);
		gbc_btnPause.gridx = 1;
		gbc_btnPause.gridy = 0;
		MediaButtonsPanel.add(btnPause, gbc_btnPause);
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.gridx = 2;
		gbc_btnStop.gridy = 0;
		MediaButtonsPanel.add(btnStop, gbc_btnStop);
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mplayer.isPlaying()) mplayer.finish();
			}
		});
		
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mplayer.isPlaying()) mplayer.pause();
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
						trackBar.setString("00:00 - " + playlist.get(0).getSongLengthLbl());
						try {
							mplayer.start(MidiFileManager.load(playlist.get(0).getSongPath()));
						} catch (InvalidMidiDataException | MidiUnavailableException | IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			}
		});
		
		menuBar.add(mnFile);
		mnFile.add(mntmNew);
		mnFile.add(mntmLoad);
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmSave);
		
		menuBar.add(mnOptions);
		mnOptions.add(mntmSerialComPort);
		
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
				//playlist.clear();
				playlist.add(new Song(file));
				addSongToTable(playlist.get(playlist.size()-1));
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
	
	private static void addSongToTable(Song song) {
		DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
		model.addRow(new Object[] {"0",song.getSongTitle(),song.getSongLengthLbl()});
	}
}
