package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.player.ManagedPlayer;

import playlist.Playlist;
import playlist.Song;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JSeparator;

public class MainWindow {

	public final JFrame mainWindow = new JFrame();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNew = new JMenuItem("New ");
	private final JMenuItem mntmLoad = new JMenuItem("Load");
	private final JMenuItem mntmSaveAs = new JMenuItem("SaveAs");
	private final JMenuItem mntmSave = new JMenuItem("Save");
	private final JMenu mnOptions = new JMenu("Options");
	private final JMenuItem mntmSerialComPort = new JMenuItem("Settings");
	private final JPanel mainPanel = new JPanel();
	private final JButton btnPlay = new JButton("Play");
	private final JButton btnPause = new JButton("Pause");
	private final JButton btnStop = new JButton("Stop");
	private final JPanel MediaControlPanel = new JPanel();
	private final JPanel MediaButtonsPanel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane(playlistTable);
	private final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	private final JMenuItem mntmSequencer = new JMenuItem("Sequencer");
	private final JSeparator separator = new JSeparator();
	private static final FileFilter midiFilter = new FileNameExtensionFilter("MIDI/Playlist","mid","epl");
		
	private static Timer timer;
	private static ManagedPlayer mplayer = new ManagedPlayer();
	private static List<Song> playlist = new ArrayList<Song>();
	private static JTable playlistTable = new JTable(0,3);
	private static JProgressBar trackBar = new JProgressBar();
	private static int activeTrackIndex = -1;
	private static JCheckBox chckbxLoop = new JCheckBox("Loop Track");
	private static File lastPlaylistFile = new File("");
	
	private final InputMap inputMap = playlistTable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	private final ActionMap actionMap = playlistTable.getActionMap();
	private static final String DELETE = "DELETE";
	
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
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		playlistTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		playlistTable.setModel(new UneditableTableModel());
		playlistTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		playlistTable.getColumnModel().getColumn(0).setMinWidth(20);
		playlistTable.getColumnModel().getColumn(0).setMaxWidth(20);
		playlistTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		playlistTable.getColumnModel().getColumn(1).setPreferredWidth(279);
		playlistTable.getColumnModel().getColumn(1).setMinWidth(31);
		playlistTable.getColumnModel().getColumn(2).setPreferredWidth(51);
		playlistTable.getColumnModel().getColumn(2).setMinWidth(51);
		playlistTable.getColumnModel().getColumn(2).setMaxWidth(51);
		playlistTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		playlistTable.setFillsViewportHeight(true);
		mainPanel.add(scrollPane, gbc_playlistTable);
		
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
		trackBar.setString("0:00 - 0:00");
		MediaControlPanel.add(trackBar, gbc_trackBar);
		
		GridBagConstraints gbc_MediaButtonsPanel = new GridBagConstraints();
		gbc_MediaButtonsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_MediaButtonsPanel.gridx = 0;
		gbc_MediaButtonsPanel.gridy = 1;
		MediaControlPanel.add(MediaButtonsPanel, gbc_MediaButtonsPanel);
		GridBagLayout gbl_MediaButtonsPanel = new GridBagLayout();
		gbl_MediaButtonsPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_MediaButtonsPanel.rowHeights = new int[]{0, 0};
		gbl_MediaButtonsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridx = 2;
		gbc_btnStop.gridy = 0;
		MediaButtonsPanel.add(btnStop, gbc_btnStop);		
		GridBagConstraints gbc_chckbxLoop = new GridBagConstraints();
		gbc_chckbxLoop.gridx = 3;
		gbc_chckbxLoop.gridy = 0;
		MediaButtonsPanel.add(chckbxLoop, gbc_chckbxLoop);
		
		menuBar.add(mnFile);
		mnFile.add(mntmNew);
		mnFile.add(mntmLoad);
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmSave);
		mnFile.add(separator);
		mnFile.add(mntmSequencer);
		menuBar.add(mnOptions);
		mnOptions.add(mntmSerialComPort);
		
		trackBar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				trackBar_Clicked(e);
			}
		});
		
		trackBar.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				trackBar_MouseMoved(e);
			}
		});
		
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPause_Clicked();
			}
		});
		
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
		
		mntmSequencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSequencer_Clicked();
			}
		});
				
		mntmSerialComPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmSerialComPort_Clicked();
			}
		});
		
		playlistTable.setDropTarget(new DropTarget() {
			@Override
			public synchronized void drop(DropTargetDropEvent dtde) {
				playListFileDrop(dtde);
			}
		});
		
		playlistTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					btnPlay_Clicked();	
				}
			}
		});
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);
		actionMap.put(DELETE, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				deleteSongFromTable(playlistTable.getSelectedRow());
			}
		});
		
		timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerEvent();
			}
		});
	}
	
	private static void mntmNew_Clicked() {
		try {
			lastPlaylistFile = new File("");
			playlist.clear();
			DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
			for (int i = 0; i <= model.getRowCount(); i++) {
				model.removeRow(i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmLoad_Clicked() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fc.setFileFilter(midiFilter);
			int retVal = fc.showOpenDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if (file.getName().endsWith(".epl")) {
					mntmNew_Clicked();
					playlist = Playlist.loadPlaylist(file);
					for (Song song : playlist) {
						addSongToTable(song);
					}
				}
				else if (file.getName().endsWith(".mid")) {
					mntmNew_Clicked();
					playlist.add(new Song(file));
					addSongToTable(playlist.get(playlist.size()-1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSaveAs_Clicked() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int retVal = fc.showSaveDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				File saveFile = file.getName().endsWith(".epl") ? file : new File(file.getAbsolutePath() + ".epl");
				Playlist.savePlaylist(playlist, saveFile);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSave_Clicked() {
		try {
			if (lastPlaylistFile.exists()) {
				Playlist.savePlaylist(playlist, lastPlaylistFile);
			}
			else {
				mntmSaveAs_Clicked();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSequencer_Clicked() {
		try {
			ScoreWindow scoreWindow = new ScoreWindow();
			scoreWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			scoreWindow.pack();
			scoreWindow.setModal(true);				
			scoreWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSerialComPort_Clicked() {
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
	
	private static void btnPlay_Clicked() {
		if (mplayer.isPaused()) {
			mplayer.resume();
			timer.start();
		} 
		else {
			if (mplayer.isPlaying()) mplayer.finish();
			if (playlist.size() > 0 && playlistTable.getSelectedRow() > -1) {
				mplayer = new ManagedPlayer();
				activeTrackIndex = playlistTable.getSelectedRow();
				trackBar.setString("0:00 - " + playlist.get(activeTrackIndex).getSongLengthLbl());
				trackBar.setValue(0);
				try {
					mplayer.start(MidiFileManager.load(playlist.get(activeTrackIndex).getSongPath()));
					timer.start();
				} catch (InvalidMidiDataException | MidiUnavailableException | IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private static void btnPause_Clicked() {
		if (mplayer.isPlaying()) {
			timer.stop();
			mplayer.pause();
		}
	}
	
	private static void btnStop_Clicked() {
		if (mplayer.isPlaying()) {
			timer.stop();
			mplayer.finish();
			trackBar.setValue(0);
			trackBar.setString("0:00 - " + playlist.get(playlistTable.getSelectedRow()).getSongLengthLbl());
		}
		else if (mplayer.isPaused()) {
			mplayer.finish();
			trackBar.setValue(0);
			trackBar.setString("0:00 - " + playlist.get(playlistTable.getSelectedRow()).getSongLengthLbl());
		}
	}
	
	private static void timerEvent() {
		double tickRatio = (double)mplayer.getTickPosition()/(double)mplayer.getTickLength();
		trackBar.setString(
				formatTrackPosition(tickRatio)
				+ " - " 
				+ playlist.get(activeTrackIndex).getSongLengthLbl());
		trackBar.setValue((int) (tickRatio*100));
		if (mplayer.isFinished()) {
			timer.stop();
			if (chckbxLoop.isSelected()) {
				btnPlay_Clicked();
			}
		}
	}
	
	private static void trackBar_Clicked(MouseEvent arg0) {
		updateTrackBar(arg0);
	}
	
	private static void trackBar_MouseMoved(MouseEvent arg0) {
		if (activeTrackIndex == -1) return;
		int mouseX = arg0.getX();
		int newPos = (int)Math.round(((double)mouseX / (double)trackBar.getWidth()) * trackBar.getMaximum());
		double percentPos = (double) newPos/100.0;
		trackBar.setToolTipText(formatTrackPosition(percentPos));
	}
	
	private static void updateTrackBar(MouseEvent arg0) {
		if (activeTrackIndex == -1) return;
		int mouseX = arg0.getX();
		int newPos = (int)Math.round(((double)mouseX / (double)trackBar.getWidth()) * trackBar.getMaximum());
		double percentPos = (double) newPos/100.0;
		trackBar.setValue(newPos);
		trackBar.setString(formatTrackPosition(percentPos) + " - " + playlist.get(activeTrackIndex).getSongLengthLbl());
		long newTick = Math.round(playlist.get(activeTrackIndex).getTickLength() * percentPos);
		if (mplayer.isFinished()) {
			try {
				mplayer.start(MidiFileManager.load(playlist.get(activeTrackIndex).getSongPath()));
			} catch (InvalidMidiDataException | MidiUnavailableException | IOException e) {
				e.printStackTrace();
			}
			mplayer.pause();
		}
		mplayer.seek(newTick);
	}
	
	private static void playListFileDrop(DropTargetDropEvent dtde) {
	    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
	        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
	        Transferable t = dtde.getTransferable();
	        List fileList = null;
	        try {
	            fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
	            if (fileList != null && fileList.size() > 0) {
	                for (Object value : fileList) {
	                    if (value instanceof File) {
	                        File file = (File) value;
	                        if (file.getName().endsWith(".epl")) {
	                        	int prevPLSize = playlist.size();
	                        	playlist.addAll(Playlist.loadPlaylist(file));
	                        	for (int i = prevPLSize; i < playlist.size(); i++) {
	                        		addSongToTable(playlist.get(i));
	                        	}
	                        }
	                        else if (file.getName().endsWith(".mid")) {
		                        playlist.add(new Song(file));
		        				addSongToTable(playlist.get(playlist.size()-1));
	                        }
	                    }
	                }
	            }
	        } catch (UnsupportedFlavorException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        dtde.rejectDrop();
	    }
	}
	
	private static void addSongToTable(Song song) {
		DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
		model.addRow(new Object[] {playlistTable.getRowCount()+1,song.getSongTitle(),song.getSongLengthLbl()});
	}
	
	private static void deleteSongFromTable(int rowIndex) {
		if (rowIndex == activeTrackIndex) {
			timer.stop();
			mplayer.finish();
			trackBar.setValue(0);
			trackBar.setString("0:00 - 0:00");
		}
		playlist.remove(rowIndex);
		DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
		model.removeRow(rowIndex);
		for (int i = rowIndex+1; i <= model.getRowCount(); i++) {
			model.setValueAt(i, i-1, 0);
		}
	}
	
	private static String formatTrackPosition(double percentPos) {
		int newSeconds = (int)((double)playlist.get(activeTrackIndex).getSongLengthSeconds() * percentPos);
		return String.format("%2d:%02d", (newSeconds % 3600) / 60, newSeconds % 60);
	}
}
