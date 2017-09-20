package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import com.fazecast.jSerialComm.SerialPort;

import arduino.ArduinoCOM;
import main.Instrument;
import main.Instruments;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ItemEvent;

public class SerialConfig extends JDialog {
	/*TODO: Need to add some sort of global handling for ports being disconnected/reconnected...
	 *		Need to be able to preserve settings window after exiting...
	 *		Add handling to parent that sets window with relevant information 
	*/
	
	private final JLabel lblInstrument = new JLabel("Instrument");
	private final JLabel lblPort = new JLabel("Port");
	private final JLabel lblCh = new JLabel("Enable Channels");
	private final JLabel lblSendTest = new JLabel("Send Test");
	private final JComboBox<String> cBoxPortSnare = new JComboBox<String>();
	private final JComboBox<String> cBoxPortCHiHat = new JComboBox<String>();
	private final JComboBox<String> cBoxPortOHiHat = new JComboBox<String>();
	private final JComboBox<String> cBoxPortBass = new JComboBox<String>();
	private final JComboBox<String> cBoxPortToms = new JComboBox<String>();
	private final JComboBox<String> cBoxPortCymbals = new JComboBox<String>();
	private final JCheckBox chkEnSnareA = new JCheckBox("A");
	private final JCheckBox chkEnCHiHatA = new JCheckBox("A");
	private final JCheckBox chkEnOHiHatA = new JCheckBox("A");
	private final JCheckBox chkEnBassA = new JCheckBox("A");
	private final JCheckBox chkEnTomsA = new JCheckBox("A");
	private final JCheckBox chkEnCymbalsA = new JCheckBox("A");
	private final JCheckBox chkEnSnareB = new JCheckBox("B");
	private final JCheckBox chkEnCHiHatB = new JCheckBox("B");
	private final JCheckBox chkEnOHiHatB = new JCheckBox("B");
	private final JCheckBox chkEnBassB = new JCheckBox("B");
	private final JCheckBox chkEnTomsB = new JCheckBox("B");
	private final JCheckBox chkEnCymbalsB = new JCheckBox("B");
	private final JButton btnSendTestSnare = new JButton("SEND");
	private final JButton btnSendTestCHiHat = new JButton("SEND");
	private final JButton btnSendTestOHiHat = new JButton("SEND");
	private final JButton btnSendTestBass = new JButton("SEND");
	private final JButton btnSendTestToms = new JButton("SEND");
	private final JButton btnSendTestCymbals = new JButton("SEND");
	private final JPanel buttonPane = new JPanel();
	private final JButton btnOK = new JButton("OK");
	private final JButton btnCancel = new JButton("Cancel");
	private final JLabel lblSnare = new JLabel("Snare");
	private final JLabel lblCHiHat = new JLabel("Closed Hi-Hat");
	private final JLabel lblOHiHat = new JLabel("Open Hi-Hat");
	private final JLabel lblBass = new JLabel("Bass");
	private final JLabel lblToms = new JLabel("Low Tom (A) High Tom (B)");
	private final JLabel lblRideaCrash = new JLabel("Ride (A) Crash (B)");
	
	protected Instruments instruments = new Instruments();
	
	//Hardcoded lists of midi (Channel 10) notes for each instrument
	private final List<Integer> snareNotes = new ArrayList<Integer>(Stream.of(38,40).collect(Collectors.toList()));
	private final List<Integer> cHiHatNotes = new ArrayList<Integer>(Stream.of(42,44).collect(Collectors.toList()));
	private final List<Integer> oHiHatNotes = new ArrayList<Integer>(Stream.of(46).collect(Collectors.toList()));
	private final List<Integer> bassNotes = new ArrayList<Integer>(Stream.of(35,36).collect(Collectors.toList()));
	private final List<Integer> lowTomNotes = new ArrayList<Integer>(Stream.of(41,45,47).collect(Collectors.toList()));
	private final List<Integer> highTomNotes = new ArrayList<Integer>(Stream.of(43,50,48).collect(Collectors.toList()));
	private final List<Integer> rideNotes = new ArrayList<Integer>(Stream.of(49,57).collect(Collectors.toList()));
	private final List<Integer> crashNotes = new ArrayList<Integer>(Stream.of(51,59).collect(Collectors.toList()));
	
	public SerialConfig(boolean isEmpty, Instruments savedInstruments) {
		initialize();
		if (!isEmpty) {
			importSavedInstruments(savedInstruments);
		}
	}
	
	private void initialize() {
		setTitle("Serial Port Configuration");
		setBounds(100, 100, 430, 280);
		setResizable(false);
		getContentPane().setLayout(new MigLayout("", "[198.00][78.00][110.00][]", "[][30.00][][][][][][][]"));
		lblSnare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		getContentPane().add(lblSnare, "cell 0 1,alignx center,aligny center");
		lblCHiHat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		getContentPane().add(lblCHiHat, "cell 0 2,alignx center,aligny center");
		lblOHiHat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		getContentPane().add(lblOHiHat, "cell 0 3,alignx center,aligny center");
		lblBass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		getContentPane().add(lblBass, "cell 0 4,alignx center,aligny center");
		lblToms.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		getContentPane().add(lblToms, "cell 0 5,alignx center,aligny center");
		lblRideaCrash.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		getContentPane().add(lblRideaCrash, "cell 0 6,alignx center");
		getContentPane().add(buttonPane, "cell 0 8,alignx left,aligny top");
		getContentPane().add(lblInstrument, "cell 0 0,alignx center,aligny center");
		getContentPane().add(lblPort, "cell 2 0,alignx center,aligny center");
		getContentPane().add(lblCh, "cell 1 0,alignx center,aligny center");
		getContentPane().add(lblSendTest, "cell 3 0,alignx center,aligny center");
		getContentPane().add(cBoxPortSnare, "cell 2 1,growx");
		getContentPane().add(cBoxPortCHiHat, "cell 2 2,growx");
		getContentPane().add(cBoxPortOHiHat, "cell 2 3,growx");
		getContentPane().add(cBoxPortBass, "cell 2 4,growx");
		getContentPane().add(cBoxPortToms, "cell 2 5,growx");
		getContentPane().add(cBoxPortCymbals, "cell 2 6,growx");
		getContentPane().add(chkEnSnareA, "flowx,cell 1 1,alignx center,aligny center");
		getContentPane().add(chkEnCHiHatA, "flowx,cell 1 2,alignx center,aligny center");
		getContentPane().add(chkEnOHiHatA, "flowx,cell 1 3,alignx center,aligny center");
		getContentPane().add(chkEnBassA, "flowx,cell 1 4,alignx center,aligny center");
		getContentPane().add(chkEnTomsA, "flowx,cell 1 5,alignx center,aligny center");
		getContentPane().add(chkEnCymbalsA, "flowx,cell 1 6,alignx center,aligny center");
		getContentPane().add(chkEnSnareB, "cell 1 1");
		getContentPane().add(chkEnCHiHatB, "cell 1 2");
		getContentPane().add(chkEnOHiHatB, "cell 1 3");
		getContentPane().add(chkEnBassB, "cell 1 4");
		getContentPane().add(chkEnTomsB, "cell 1 5");
		getContentPane().add(chkEnCymbalsB, "cell 1 6");
		getContentPane().add(btnSendTestSnare, "cell 3 1");
		getContentPane().add(btnSendTestCHiHat, "cell 3 2");
		getContentPane().add(btnSendTestOHiHat, "cell 3 3");
		getContentPane().add(btnSendTestBass, "cell 3 4");
		getContentPane().add(btnSendTestToms, "cell 3 5");
		getContentPane().add(btnSendTestCymbals, "cell 3 6");
		
		lblInstrument.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSendTest.setFont(new Font("Tahoma", Font.BOLD, 11));
		cBoxPortSnare.setEnabled(false);
		cBoxPortCHiHat.setEnabled(false);
		cBoxPortOHiHat.setEnabled(false);
		cBoxPortBass.setEnabled(false);
		cBoxPortToms.setEnabled(false);
		cBoxPortCymbals.setEnabled(false);
		cBoxPortSnare.addItem("");
		cBoxPortCHiHat.addItem("");
		cBoxPortOHiHat.addItem("");
		cBoxPortBass.addItem("");
		cBoxPortToms.addItem("");
		cBoxPortCymbals.addItem("");
		btnSendTestSnare.setEnabled(false);
		btnSendTestCHiHat.setEnabled(false);
		btnSendTestOHiHat.setEnabled(false);
		btnSendTestBass.setEnabled(false);
		btnSendTestToms.setEnabled(false);
		btnSendTestCymbals.setEnabled(false);
		
		//Currently disabled channels
		chkEnOHiHatB.setEnabled(false);
		chkEnBassB.setEnabled(false);
		
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnOK.setActionCommand("OK");
		buttonPane.add(btnOK);
		getRootPane().setDefaultButton(btnOK);
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);
		
		cBoxPortSnare.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateSerialPorts(1, cBoxPortSnare.getSelectedItem().toString());
				if (cBoxPortSnare.getSelectedItem() != "") {
					btnSendTestSnare.setEnabled(true);
				} else {
					btnSendTestSnare.setEnabled(false);
				}
			}
		});
		cBoxPortCHiHat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateSerialPorts(2,cBoxPortCHiHat.getSelectedItem().toString());
				if (cBoxPortCHiHat.getSelectedItem() != "") {
					btnSendTestCHiHat.setEnabled(true);
				} else {
					btnSendTestCHiHat.setEnabled(false);
				}
			}
		});
		cBoxPortOHiHat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateSerialPorts(3,cBoxPortOHiHat.getSelectedItem().toString());
				if (cBoxPortOHiHat.getSelectedItem() != "") {
					btnSendTestOHiHat.setEnabled(true);
				} else {
					btnSendTestOHiHat.setEnabled(false);
				}
			}
		});
		cBoxPortBass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateSerialPorts(4,cBoxPortBass.getSelectedItem().toString());
				if (cBoxPortBass.getSelectedItem() != "") {
					btnSendTestBass.setEnabled(true);
				} else {
					btnSendTestBass.setEnabled(false);
				}
			}
		});
		cBoxPortToms.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateSerialPorts(5,cBoxPortToms.getSelectedItem().toString());
				if (cBoxPortToms.getSelectedItem() != "") {
					btnSendTestToms.setEnabled(true);
				} else {
					btnSendTestToms.setEnabled(false);
				}
			}
		});
		cBoxPortCymbals.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateSerialPorts(6,cBoxPortCymbals.getSelectedItem().toString());
				if (cBoxPortCymbals.getSelectedItem() != "") {
					btnSendTestCymbals.setEnabled(true);
				} else {
					btnSendTestCymbals.setEnabled(false);
				}
			}
		});
		

		chkEnSnareA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnSnareA.isSelected() || chkEnSnareB.isSelected()) {
					if (!cBoxPortSnare.isEnabled()) cBoxPortSnare.setEnabled(true);
				} else {
					cBoxPortSnare.removeAll();
					cBoxPortSnare.addItem("");
					cBoxPortSnare.setEnabled(false);
				}
				//updateSerialPorts(1);
			}
		});
		chkEnSnareB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnSnareA.isSelected() || chkEnSnareB.isSelected()) {
					if (!cBoxPortSnare.isEnabled()) cBoxPortSnare.setEnabled(true);
				} else {
					cBoxPortSnare.removeAll();
					cBoxPortSnare.addItem("");
					cBoxPortSnare.setEnabled(false);
				}
				//updateSerialPorts(1);
			}
		});
		chkEnCHiHatA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnCHiHatA.isSelected() || chkEnCHiHatB.isSelected()) {
					if (!cBoxPortCHiHat.isEnabled()) cBoxPortCHiHat.setEnabled(true);
				} else {
					cBoxPortCHiHat.removeAll();
					cBoxPortCHiHat.addItem("");
					cBoxPortCHiHat.setEnabled(false);
				}
				//updateSerialPorts(2);
			}
		});
		chkEnCHiHatB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnCHiHatA.isSelected() || chkEnCHiHatB.isSelected()) {
					if (!cBoxPortCHiHat.isEnabled()) cBoxPortCHiHat.setEnabled(true);
				} else {
					cBoxPortCHiHat.removeAll();
					cBoxPortCHiHat.addItem("");
					cBoxPortCHiHat.setEnabled(false);
				}
				//updateSerialPorts(2);
			}
		});
		chkEnOHiHatA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnOHiHatA.isSelected() || chkEnOHiHatB.isSelected()) {
					if (!cBoxPortOHiHat.isEnabled()) cBoxPortOHiHat.setEnabled(true);
				} else {
					cBoxPortOHiHat.removeAll();
					cBoxPortOHiHat.addItem("");
					cBoxPortOHiHat.setEnabled(false);
				}
				//updateSerialPorts(3);
			}
		});
		chkEnOHiHatB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnOHiHatA.isSelected() || chkEnOHiHatB.isSelected()) {
					if (!cBoxPortOHiHat.isEnabled()) cBoxPortOHiHat.setEnabled(true);
				} else {
					cBoxPortOHiHat.removeAll();
					cBoxPortOHiHat.addItem("");
					cBoxPortOHiHat.setEnabled(false);
				}
				//updateSerialPorts(3);
			}
		});
		chkEnBassA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnBassA.isSelected() || chkEnBassB.isSelected()) {
					if (!cBoxPortBass.isEnabled()) cBoxPortBass.setEnabled(true);
				} else {
					cBoxPortBass.removeAll();
					cBoxPortBass.addItem("");
					cBoxPortBass.setEnabled(false);
				}
				//updateSerialPorts(4);
			}
		});
		chkEnBassB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnBassA.isSelected() || chkEnBassB.isSelected()) {
					if (!cBoxPortBass.isEnabled()) cBoxPortBass.setEnabled(true);
				} else {
					cBoxPortBass.removeAll();
					cBoxPortBass.addItem("");
					cBoxPortBass.setEnabled(false);
				}
				//updateSerialPorts(4);
			}
		});
		chkEnTomsA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnTomsA.isSelected() || chkEnTomsB.isSelected()) {
					if (!cBoxPortToms.isEnabled()) cBoxPortToms.setEnabled(true);
				} else {
					cBoxPortToms.removeAll();
					cBoxPortToms.addItem("");
					cBoxPortToms.setEnabled(false);
				}
				//updateSerialPorts(5);
			}
		});
		chkEnTomsB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnTomsA.isSelected() || chkEnTomsB.isSelected()) {
					if (!cBoxPortToms.isEnabled()) cBoxPortToms.setEnabled(true);
				} else {
					cBoxPortToms.removeAll();
					cBoxPortToms.addItem("");
					cBoxPortToms.setEnabled(false);
				}
				//updateSerialPorts(5);
			}
		});
		chkEnCymbalsA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnCymbalsA.isSelected() || chkEnCymbalsB.isSelected()) {
					if (!cBoxPortCymbals.isEnabled()) cBoxPortCymbals.setEnabled(true);
				} else {
					cBoxPortCymbals.removeAll();
					cBoxPortCymbals.addItem("");
					cBoxPortCymbals.setEnabled(false);
				}
				//updateSerialPorts(6);
			}
		});
		chkEnCymbalsB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkEnCymbalsA.isSelected() || chkEnCymbalsB.isSelected()) {
					if (!cBoxPortCymbals.isEnabled()) cBoxPortCymbals.setEnabled(true);
				} else {
					cBoxPortCymbals.removeAll();
					cBoxPortCymbals.addItem("");
					cBoxPortCymbals.setEnabled(false);
				}
				//updateSerialPorts(6);
			}
		});
		
		
		btnSendTestSnare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkEnSnareA.isSelected() && chkEnSnareB.isSelected()) {
					sendCOMTest(cBoxPortSnare.getSelectedItem().toString(), "BOTH");
				} else if (chkEnSnareA.isSelected()) {
					sendCOMTest(cBoxPortSnare.getSelectedItem().toString(), "A");
				} else if (chkEnSnareB.isSelected()) {
					sendCOMTest(cBoxPortSnare.getSelectedItem().toString(), "B");
				}
			}
		});
		btnSendTestCHiHat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkEnCHiHatA.isSelected() && chkEnCHiHatB.isSelected()) {
					sendCOMTest(cBoxPortCHiHat.getSelectedItem().toString(), "BOTH");
				} else if (chkEnCHiHatA.isSelected()) {
					sendCOMTest(cBoxPortCHiHat.getSelectedItem().toString(), "A");
				} else if (chkEnCHiHatB.isSelected()) {
					sendCOMTest(cBoxPortCHiHat.getSelectedItem().toString(), "B");
				}
			}
		});
		btnSendTestOHiHat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkEnOHiHatA.isSelected() && chkEnOHiHatB.isSelected()) {
					sendCOMTest(cBoxPortCHiHat.getSelectedItem().toString(), "BOTH");
				} else if (chkEnOHiHatA.isSelected()) {
					sendCOMTest(cBoxPortCHiHat.getSelectedItem().toString(), "A");
				} else if (chkEnOHiHatB.isSelected()) {
					sendCOMTest(cBoxPortCHiHat.getSelectedItem().toString(), "B");
				}
			}
		});
		btnSendTestBass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkEnBassA.isSelected() && chkEnBassB.isSelected()) {
					sendCOMTest(cBoxPortBass.getSelectedItem().toString(), "BOTH");
				} else if (chkEnBassA.isSelected()) {
					sendCOMTest(cBoxPortBass.getSelectedItem().toString(), "A");
				} else if (chkEnBassB.isSelected()) {
					sendCOMTest(cBoxPortBass.getSelectedItem().toString(), "B");
				}
			}
		});
		btnSendTestToms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkEnTomsA.isSelected() && chkEnTomsB.isSelected()) {
					sendCOMTest(cBoxPortToms.getSelectedItem().toString(), "BOTH");
				} else if (chkEnTomsA.isSelected()) {
					sendCOMTest(cBoxPortToms.getSelectedItem().toString(), "A");
				} else if (chkEnTomsB.isSelected()) {
					sendCOMTest(cBoxPortToms.getSelectedItem().toString(), "B");
				}
			}
		});
		btnSendTestCymbals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkEnCymbalsA.isSelected() && chkEnCymbalsB.isSelected()) {
					sendCOMTest(cBoxPortCymbals.getSelectedItem().toString(), "BOTH");
				} else if (chkEnCymbalsA.isSelected()) {
					sendCOMTest(cBoxPortCymbals.getSelectedItem().toString(), "A");
				} else if (chkEnCymbalsB.isSelected()) {
					sendCOMTest(cBoxPortCymbals.getSelectedItem().toString(), "B");
				}
			}
		});
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer i = 0;
				
				//Clear Instruments list to create new list
				instruments.clearList();
				
				if (chkEnSnareA.isSelected() && chkEnSnareB.isSelected()) {
					Instrument snare = new Instrument(i, "Snare", 1, cBoxPortSnare.getSelectedItem().toString(), "BOTH", snareNotes);
					instruments.addInstrument(snare);
					i++;
				} else if (chkEnSnareA.isSelected()) {
					Instrument snare = new Instrument(i, "Snare", 1, cBoxPortSnare.getSelectedItem().toString(), "A", snareNotes);
					instruments.addInstrument(snare);
					i++;
				} else if (chkEnSnareB.isSelected()) {
					Instrument snare = new Instrument(i, "Snare", 1, cBoxPortSnare.getSelectedItem().toString(), "B", snareNotes);
					instruments.addInstrument(snare);
					i++;
				}
				
				if (chkEnCHiHatA.isSelected() && chkEnCHiHatB.isSelected()) {
					Instrument cHiHat = new Instrument(i, "Closed Hi-Hat", 2, cBoxPortCHiHat.getSelectedItem().toString(), "BOTH", cHiHatNotes);
					instruments.addInstrument(cHiHat);
					i++;
				} else if (chkEnCHiHatA.isSelected()) {
					Instrument cHiHat = new Instrument(i, "Closed Hi-Hat", 2, cBoxPortCHiHat.getSelectedItem().toString(), "A", cHiHatNotes);
					instruments.addInstrument(cHiHat);
					i++;
				} else if (chkEnCHiHatB.isSelected()) {
					Instrument cHiHat = new Instrument(i, "Closed Hi-Hat", 2, cBoxPortCHiHat.getSelectedItem().toString(), "B", cHiHatNotes);
					instruments.addInstrument(cHiHat);
					i++;
				}


				if (chkEnOHiHatA.isSelected() && chkEnOHiHatB.isSelected()) {
					Instrument oHiHat = new Instrument(i, "Open Hi-Hat", 3, cBoxPortOHiHat.getSelectedItem().toString(), "BOTH", oHiHatNotes);
					instruments.addInstrument(oHiHat);
					i++;
				} else if (chkEnOHiHatA.isSelected()) {
					Instrument oHiHat = new Instrument(i, "Open Hi-Hat", 3, cBoxPortOHiHat.getSelectedItem().toString(), "A", oHiHatNotes);
					instruments.addInstrument(oHiHat);
					i++;
				} else if (chkEnOHiHatB.isSelected()) {
					Instrument oHiHat = new Instrument(i, "Open Hi-Hat", 3, cBoxPortOHiHat.getSelectedItem().toString(), "B", oHiHatNotes);
					instruments.addInstrument(oHiHat);
					i++;
				}
				
				if (chkEnBassA.isSelected() && chkEnBassB.isSelected()) {
					Instrument bass = new Instrument(i, "Bass", 4, cBoxPortBass.getSelectedItem().toString(), "BOTH", bassNotes);
					instruments.addInstrument(bass);
					i++;
				} else if (chkEnBassA.isSelected()) {
					Instrument bass = new Instrument(i, "Bass", 4, cBoxPortBass.getSelectedItem().toString(), "A", bassNotes);
					instruments.addInstrument(bass);
					i++;
				} else if (chkEnBassB.isSelected()) {
					Instrument bass = new Instrument(i, "Bass", 4, cBoxPortBass.getSelectedItem().toString(), "B", bassNotes);
					instruments.addInstrument(bass);
					i++;
				}
				
				if (chkEnTomsA.isSelected() && chkEnTomsB.isSelected()) {
					Instrument lowToms = new Instrument(i, "Low Tom", 5, cBoxPortToms.getSelectedItem().toString(), "A", lowTomNotes);
					instruments.addInstrument(lowToms);
					i++;
					Instrument highToms = new Instrument(i, "High Tom", 6, cBoxPortToms.getSelectedItem().toString(), "B", highTomNotes);
					instruments.addInstrument(highToms);
					i++;
				} else if (chkEnTomsA.isSelected()) {
					Instrument lowToms = new Instrument(i, "Low Tom", 5, cBoxPortToms.getSelectedItem().toString(), "A", lowTomNotes);
					instruments.addInstrument(lowToms);
					i++;
				} else if (chkEnTomsB.isSelected()) {
					Instrument highToms = new Instrument(i, "High Tom", 6, cBoxPortToms.getSelectedItem().toString(), "B", highTomNotes);
					instruments.addInstrument(highToms);
					i++;
				}
				
				if (chkEnCymbalsA.isSelected() && chkEnCymbalsB.isSelected()) {
					Instrument ride = new Instrument(i, "Ride Cymbal", 7, cBoxPortCymbals.getSelectedItem().toString(), "A", rideNotes);
					instruments.addInstrument(ride);
					i++;
					Instrument crash = new Instrument(i, "Crash Cymbal", 8, cBoxPortCymbals.getSelectedItem().toString(), "B", crashNotes);
					instruments.addInstrument(crash);
					i++;
				} else if (chkEnCymbalsA.isSelected()) {
					Instrument ride = new Instrument(i, "Ride Cymbal", 7, cBoxPortCymbals.getSelectedItem().toString(), "A", rideNotes);
					instruments.addInstrument(ride);
					i++;
				} else if (chkEnCymbalsB.isSelected()) {
					Instrument crash = new Instrument(i, "Crash Cymbal", 8, cBoxPortCymbals.getSelectedItem().toString(), "B", crashNotes);
					instruments.addInstrument(crash);
					i++;
				}
				dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		
		this.pack();

	}
	
	private void updateSerialPorts(Integer cBoxSelected, String selectedItem) {
		/* Cases:
		 * 1:Snare
		 * 2:Closed Hi-Hat
		 * 3:Open Hi-Hat
		 * 4:Bass
		 * 5:Toms
		 * 6:Cymbals
		 * 
		 * Saves selected item
		 * Removes all items
		 * Adds previously selected item back
		 * Adds all items not currently selected
		 */
		String tempItem;		
		
		switch (cBoxSelected) {
			case 1:
				cBoxPortSnare.removeAllItems();
				cBoxPortSnare.addItem(selectedItem);
				for (SerialPort port : SerialPort.getCommPorts()) {
					if (!isPortSelected(port)) {
						cBoxPortSnare.addItem(port.getSystemPortName());
					}
				}
				break;
				
			case 2:
				cBoxPortCHiHat.removeAllItems();
				cBoxPortCHiHat.addItem(selectedItem);
				for (SerialPort port : SerialPort.getCommPorts()) {
					if (!isPortSelected(port)) {
						cBoxPortCHiHat.addItem(port.getSystemPortName());
					}
				}
				break;
				
			case 3:
				cBoxPortOHiHat.removeAllItems();
				cBoxPortOHiHat.addItem(selectedItem);
				for (SerialPort port : SerialPort.getCommPorts()) {
					if (!isPortSelected(port)) {
						cBoxPortOHiHat.addItem(port.getSystemPortName());
					}
				}
				break;
				
			case 4:
				cBoxPortBass.removeAllItems();
				cBoxPortBass.addItem(selectedItem);
				for (SerialPort port : SerialPort.getCommPorts()) {
					if (!isPortSelected(port)) {
						cBoxPortBass.addItem(port.getSystemPortName());
					}
				}
				break;
				
			case 5:
				cBoxPortToms.removeAllItems();
				cBoxPortToms.addItem(selectedItem);
				for (SerialPort port : SerialPort.getCommPorts()) {
					if (!isPortSelected(port)) {
						cBoxPortToms.addItem(port.getSystemPortName());
					}
				}
				break;
				
			case 6:
				cBoxPortCymbals.removeAllItems();
				cBoxPortCymbals.addItem(selectedItem);
				for (SerialPort port : SerialPort.getCommPorts()) {
					if (!isPortSelected(port)) {
						cBoxPortCymbals.addItem(port.getSystemPortName());
					}
				}
				break;
		}		
	}
	
	private boolean isPortSelected(SerialPort port) {
		// Checks if a given port is already selected when repopulating port comboboxes
		if (cBoxPortSnare.getSelectedItem().toString() == port.getSystemPortName()) {
			return true;
		}else if (cBoxPortCHiHat.getSelectedItem().toString() == port.getSystemPortName()) {
			return true;
		}else if (cBoxPortOHiHat.getSelectedItem().toString() == port.getSystemPortName()) {
			return true;
		}else if (cBoxPortBass.getSelectedItem().toString() == port.getSystemPortName()) {
			return true;
		}else if (cBoxPortToms.getSelectedItem().toString() == port.getSystemPortName()) {
			return true;
		}else if (cBoxPortCymbals.getSelectedItem().toString() == port.getSystemPortName()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	private void sendCOMTest(String portToTest, String channels) {
		ArduinoCOM port = new ArduinoCOM(portToTest);
		port.openConnection();
		
		if (channels == "BOTH") {
			port.serialWrite('Z');
		} else if (channels == "A" ) {
			port.serialWrite('X');
		} else if (channels == "B" ) {
			port.serialWrite('Y');
		}
		
		port.closeConnection();
	}

	private void importSavedInstruments(Instruments savedInstruments) {
		for (Instrument instrument : savedInstruments) {
			switch (instrument.getInstrumentID()) {
			case 1: setSnare(instrument);
				break;
			case 2: setCHiHat(instrument);
				break;
			case 3: setOHiHat(instrument);
				break;
			case 4: setBass(instrument);
				break;
			case 5: setLowTom(instrument);
				break;
			case 6: setHighTom(instrument);
				break;
			case 7: setRide(instrument);
				break;
			case 8: setCrash(instrument);
				break;
			}
		}
	}
	private void setSnare(Instrument instrument) {
		
		if (instrument.getChannel() == "BOTH") {
			
		} else if (instrument.getChannel() == "A") {
			
		} else if (instrument.getChannel() == "B") {
			
		}
	}
	private void setCHiHat(Instrument instrument) {
		
	}
	private void setOHiHat(Instrument instrument) {
		
	}
	private void setBass(Instrument instrument) {
		
	}
	private void setLowTom(Instrument instrument) {
		
	}
	private void setHighTom(Instrument instrument) {
		
	}
	private void setRide(Instrument instrument) {
		
	}
	private void setCrash(Instrument instrument) {
		
	}
}
