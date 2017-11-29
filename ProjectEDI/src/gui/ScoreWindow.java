package gui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

import javax.swing.JSeparator;
import javax.swing.filechooser.FileSystemView;

import playlist.Song;

public class ScoreWindow extends JDialog {
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNew = new JMenuItem("New ");
	private final JMenuItem mntmLoad = new JMenuItem("Load");
	private final JMenuItem mntmSaveAs = new JMenuItem("SaveAs");
	private final JMenuItem mntmSave = new JMenuItem("Save");
	private ButtonListener buttonListener = new ButtonListener();
	private static JButton[][] buttons = new JButton[8][16];
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
			fc.setCurrentDirectory(FileSystemView.getFileSystemView().getDefaultDirectory());
			int retVal = fc.showOpenDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void mntmSaveAs_Clicked() {
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
	
	private static void mntmSave_Clicked() {
		try {
			//If file exists, save file, else, prompt with SaveAs dialog
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
