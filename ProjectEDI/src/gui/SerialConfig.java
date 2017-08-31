package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import com.jgoodies.forms.layout.FormLayout;
import com.fazecast.jSerialComm.SerialPort;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SerialConfig extends JDialog {
	private final JToolBar toolBar = new JToolBar();
	private final JButton btnSave = new JButton("Save");
	private final JButton btnLoad = new JButton("Load");
	private final JLabel lblInstrument = new JLabel("Instrument");
	private final JLabel lblPort = new JLabel("Port");
	private final JLabel lblCh = new JLabel("CH");
	private final JLabel lblSendTest = new JLabel("Send Test");
	private final JComboBox<String> cBoxInst1 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst2 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst3 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst4 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst5 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst6 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst7 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst8 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst9 = new JComboBox<String>();
	private final JComboBox<String> cBoxInst10 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort1 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort2 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort3 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort4 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort5 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort6 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort7 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort8 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort9 = new JComboBox<String>();
	private final JComboBox<String> cBoxPort10 = new JComboBox<String>();
	private final JCheckBox chkA1 = new JCheckBox("A");
	private final JCheckBox chkA2 = new JCheckBox("A");
	private final JCheckBox chkA3 = new JCheckBox("A");
	private final JCheckBox chkA4 = new JCheckBox("A");
	private final JCheckBox chkA5 = new JCheckBox("A");
	private final JCheckBox chkA6 = new JCheckBox("A");
	private final JCheckBox chkA7 = new JCheckBox("A");
	private final JCheckBox chkA8 = new JCheckBox("A");
	private final JCheckBox chkA9 = new JCheckBox("A");
	private final JCheckBox chkA10 = new JCheckBox("A");
	private final JCheckBox chkB1 = new JCheckBox("B");
	private final JCheckBox chkB2 = new JCheckBox("B");
	private final JCheckBox chkB3 = new JCheckBox("B");
	private final JCheckBox chkB4 = new JCheckBox("B");
	private final JCheckBox chkB5 = new JCheckBox("B");
	private final JCheckBox chkB6 = new JCheckBox("B");
	private final JCheckBox chkB7 = new JCheckBox("B");
	private final JCheckBox chkB8 = new JCheckBox("B");
	private final JCheckBox chkB9 = new JCheckBox("B");
	private final JCheckBox chkB10 = new JCheckBox("B");
	private final JButton btnSendTest1 = new JButton("SEND");
	private final JButton btnSendTest2 = new JButton("SEND");
	private final JButton btnSendTest3 = new JButton("SEND");
	private final JButton btnSendTest4 = new JButton("SEND");
	private final JButton btnSendTest5 = new JButton("SEND");
	private final JButton btnSendTest6 = new JButton("SEND");
	private final JButton btnSendTest7 = new JButton("SEND");
	private final JButton btnSendTest8 = new JButton("SEND");
	private final JButton btnSendTest9 = new JButton("SEND");
	private final JButton btnSendTest10 = new JButton("SEND");
	private final JPanel buttonPane = new JPanel();
	private final JButton okButton = new JButton("OK");
	private final JButton cancelButton = new JButton("Cancel");
	
	private static SerialPort[] comPort = SerialPort.getCommPorts();
	private static final String[] instrumentList = new String[] {"","Snare","Tom","Hi-hat","Bass","Ride Cymbal","Crash Cymbal"};
	
	public SerialConfig() {
		initialize();
	}
	
	private void initialize() {
		setTitle("Serial Port Configuration");
		setBounds(100, 100, 450, 450);
		setResizable(false);
		getContentPane().setLayout(new MigLayout("", "[grow][grow][][]", "[33px][][][][][][][][][][][][][]"));		
		getContentPane().add(toolBar, "flowx,cell 0 0");
		getContentPane().add(buttonPane, "cell 0 13,alignx left,aligny top");
		getContentPane().add(lblInstrument, "cell 0 1,alignx center,aligny center");
		getContentPane().add(lblPort, "cell 1 1,alignx center,aligny center");
		getContentPane().add(lblCh, "cell 2 1,alignx center,aligny center");
		getContentPane().add(lblSendTest, "cell 3 1,alignx center,aligny center");
		getContentPane().add(cBoxInst1, "cell 0 2,growx");
		getContentPane().add(cBoxInst2, "cell 0 3,growx");
		getContentPane().add(cBoxInst3, "cell 0 4,growx");
		getContentPane().add(cBoxInst4, "cell 0 5,growx");
		getContentPane().add(cBoxInst5, "cell 0 6,growx");
		getContentPane().add(cBoxInst6, "cell 0 7,growx");
		getContentPane().add(cBoxInst7, "cell 0 8,growx");
		getContentPane().add(cBoxInst8, "cell 0 9,growx");
		getContentPane().add(cBoxInst9, "cell 0 10,growx");
		getContentPane().add(cBoxInst10, "cell 0 11,growx");
		getContentPane().add(cBoxPort1, "cell 1 2,growx");
		getContentPane().add(cBoxPort2, "cell 1 3,growx");
		getContentPane().add(cBoxPort3, "cell 1 4,growx");
		getContentPane().add(cBoxPort4, "cell 1 5,growx");
		getContentPane().add(cBoxPort5, "cell 1 6,growx");
		getContentPane().add(cBoxPort6, "cell 1 7,growx");
		getContentPane().add(cBoxPort7, "cell 1 8,growx");
		getContentPane().add(cBoxPort8, "cell 1 9,growx");
		getContentPane().add(cBoxPort9, "cell 1 10,growx");
		getContentPane().add(cBoxPort10, "cell 1 11,growx");
		getContentPane().add(chkA1, "flowx,cell 2 2");
		getContentPane().add(chkA2, "flowx,cell 2 3");
		getContentPane().add(chkA3, "flowx,cell 2 4");
		getContentPane().add(chkA4, "flowx,cell 2 5");
		getContentPane().add(chkA5, "flowx,cell 2 6");
		getContentPane().add(chkA6, "flowx,cell 2 7");
		getContentPane().add(chkA7, "flowx,cell 2 8");
		getContentPane().add(chkA8, "flowx,cell 2 9");
		getContentPane().add(chkA9, "flowx,cell 2 10");
		getContentPane().add(chkA10, "flowx,cell 2 11");
		getContentPane().add(chkB1, "cell 2 2");
		getContentPane().add(chkB2, "cell 2 3");
		getContentPane().add(chkB3, "cell 2 4");
		getContentPane().add(chkB4, "cell 2 5");
		getContentPane().add(chkB5, "cell 2 6");
		getContentPane().add(chkB6, "cell 2 7");
		getContentPane().add(chkB7, "cell 2 8");
		getContentPane().add(chkB8, "cell 2 9");
		getContentPane().add(chkB9, "cell 2 10");
		getContentPane().add(chkB10, "cell 2 11");
		getContentPane().add(btnSendTest1, "cell 3 2");
		getContentPane().add(btnSendTest2, "cell 3 3");
		getContentPane().add(btnSendTest3, "cell 3 4");
		getContentPane().add(btnSendTest4, "cell 3 5");
		getContentPane().add(btnSendTest5, "cell 3 6");
		getContentPane().add(btnSendTest6, "cell 3 7");
		getContentPane().add(btnSendTest7, "cell 3 8");
		getContentPane().add(btnSendTest8, "cell 3 9");
		getContentPane().add(btnSendTest9, "cell 3 10");
		getContentPane().add(btnSendTest10, "cell 3 11");
		
		lblInstrument.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSendTest.setFont(new Font("Tahoma", Font.BOLD, 11));
		cBoxPort1.setEnabled(false);
		cBoxPort2.setEnabled(false);
		cBoxPort3.setEnabled(false);
		cBoxPort4.setEnabled(false);
		cBoxPort5.setEnabled(false);
		cBoxPort6.setEnabled(false);
		cBoxPort7.setEnabled(false);
		cBoxPort8.setEnabled(false);
		cBoxPort9.setEnabled(false);
		cBoxPort10.setEnabled(false);
		cBoxPort1.addItem("");
		cBoxPort2.addItem("");
		cBoxPort3.addItem("");
		cBoxPort4.addItem("");
		cBoxPort5.addItem("");
		cBoxPort6.addItem("");
		cBoxPort7.addItem("");
		cBoxPort8.addItem("");
		cBoxPort9.addItem("");
		cBoxPort10.addItem("");
		chkA1.setEnabled(false);
		chkA2.setEnabled(false);
		chkA3.setEnabled(false);
		chkA4.setEnabled(false);
		chkA5.setEnabled(false);
		chkA6.setEnabled(false);
		chkA7.setEnabled(false);
		chkA8.setEnabled(false);
		chkA9.setEnabled(false);
		chkA10.setEnabled(false);
		chkB1.setEnabled(false);
		chkB2.setEnabled(false);
		chkB3.setEnabled(false);
		chkB4.setEnabled(false);
		chkB5.setEnabled(false);
		chkB6.setEnabled(false);
		chkB7.setEnabled(false);
		chkB8.setEnabled(false);
		chkB9.setEnabled(false);
		chkB10.setEnabled(false);
		btnSendTest1.setEnabled(false);
		btnSendTest2.setEnabled(false);
		btnSendTest3.setEnabled(false);
		btnSendTest4.setEnabled(false);
		btnSendTest5.setEnabled(false);
		btnSendTest6.setEnabled(false);
		btnSendTest7.setEnabled(false);
		btnSendTest8.setEnabled(false);
		btnSendTest9.setEnabled(false);
		btnSendTest10.setEnabled(false);
		toolBar.setFloatable(false);
		toolBar.add(btnSave);
		toolBar.add(btnLoad);
		
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		updateInstruments();
		updateSerialPorts();
		
		cBoxInst1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst1.getSelectedItem() != "") {
					cBoxPort1.setEnabled(true);
				} else {
					cBoxPort1.setSelectedIndex(0);
					cBoxPort1.setEnabled(false);
				}
			}
		});
		cBoxInst2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst2.getSelectedItem() != "") {
					cBoxPort2.setEnabled(true);
				} else {
					cBoxPort2.setEnabled(false);
					cBoxPort2.setSelectedIndex(0);
				}
			}
		});
		cBoxInst3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst3.getSelectedItem() != "") {
					cBoxPort3.setEnabled(true);
				} else {
					cBoxPort3.setSelectedIndex(0);
					cBoxPort3.setEnabled(false);
				}
			}
		});
		cBoxInst4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst4.getSelectedItem() != "") {
					cBoxPort4.setEnabled(true);
				} else {
					cBoxPort4.setSelectedIndex(0);
					cBoxPort4.setEnabled(false);
				}
			}
		});
		cBoxInst5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst5.getSelectedItem() != "") {
					cBoxPort5.setEnabled(true);
				} else {
					cBoxPort5.setSelectedIndex(0);
					cBoxPort5.setEnabled(false);
				}
			}
		});
		cBoxInst6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst6.getSelectedItem() != "") {
					cBoxPort6.setEnabled(true);
				} else {
					cBoxPort6.setSelectedIndex(0);
					cBoxPort6.setEnabled(false);
				}
			}
		});
		cBoxInst7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst7.getSelectedItem() != "") {
					cBoxPort7.setEnabled(true);
				} else {
					cBoxPort7.setSelectedIndex(0);
					cBoxPort7.setEnabled(false);
				}
			}
		});
		cBoxInst8.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst8.getSelectedItem() != "") {
					cBoxPort8.setEnabled(true);
				} else {
					cBoxPort8.setSelectedIndex(0);
					cBoxPort8.setEnabled(false);
				}
			}
		});
		cBoxInst9.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst9.getSelectedItem() != "") {
					cBoxPort9.setEnabled(true);
				} else {
					cBoxPort9.setSelectedIndex(0);
					cBoxPort9.setEnabled(false);
				}
			}
		});
		cBoxInst10.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxInst10.getSelectedItem() != "") {
					cBoxPort10.setEnabled(true);
				} else {
					cBoxPort10.setSelectedIndex(0);
					cBoxPort10.setEnabled(false);
				}
			}
		});
		
		cBoxPort1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort1.getSelectedItem() != "") {
					chkA1.setEnabled(true);
					chkB1.setEnabled(true);
				} else {
					chkA1.setSelected(false);
					chkB1.setSelected(false);
					chkA1.setEnabled(false);
					chkB1.setEnabled(false);
				}
			}
		});
		cBoxPort2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort2.getSelectedItem() != "") {
					chkA2.setEnabled(true);
					chkB2.setEnabled(true);
				} else {
					chkA2.setSelected(false);
					chkB2.setSelected(false);
					chkA2.setEnabled(false);
					chkB2.setEnabled(false);
				}
			}
		});
		cBoxPort3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort3.getSelectedItem() != "") {
					chkA3.setEnabled(true);
					chkB3.setEnabled(true);
				} else {
					chkA3.setSelected(false);
					chkB3.setSelected(false);
					chkA3.setEnabled(false);
					chkB3.setEnabled(false);
				}
			}
		});
		cBoxPort4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort4.getSelectedItem() != "") {
					chkA4.setEnabled(true);
					chkB4.setEnabled(true);
				} else {
					chkA4.setSelected(false);
					chkB4.setSelected(false);
					chkA4.setEnabled(false);
					chkB4.setEnabled(false);
				}
			}
		});
		cBoxPort5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort5.getSelectedItem() != "") {
					chkA5.setEnabled(true);
					chkB5.setEnabled(true);
				} else {
					chkA5.setSelected(false);
					chkB5.setSelected(false);
					chkA5.setEnabled(false);
					chkB5.setEnabled(false);
				}
			}
		});
		cBoxPort6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort6.getSelectedItem() != "") {
					chkA6.setEnabled(true);
					chkB6.setEnabled(true);
				} else {
					chkA6.setSelected(false);
					chkB6.setSelected(false);
					chkA6.setEnabled(false);
					chkB6.setEnabled(false);
				}
			}
		});
		cBoxPort7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort7.getSelectedItem() != "") {
					chkA7.setEnabled(true);
					chkB7.setEnabled(true);
				} else {
					chkA7.setSelected(false);
					chkB7.setSelected(false);
					chkA7.setEnabled(false);
					chkB7.setEnabled(false);
				}
			}
		});
		cBoxPort8.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort8.getSelectedItem() != "") {
					chkA8.setEnabled(true);
					chkB8.setEnabled(true);
				} else {
					chkA8.setSelected(false);
					chkB8.setSelected(false);
					chkA8.setEnabled(false);
					chkB8.setEnabled(false);
				}
			}
		});
		cBoxPort9.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort9.getSelectedItem() != "") {
					chkA9.setEnabled(true);
					chkB9.setEnabled(true);
				} else {
					chkA9.setSelected(false);
					chkB9.setSelected(false);
					chkA9.setEnabled(false);
					chkB9.setEnabled(false);
				}
			}
		});
		cBoxPort10.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cBoxPort10.getSelectedItem() != "") {
					chkA10.setEnabled(true);
					chkB10.setEnabled(true);
				} else {
					chkA10.setSelected(false);
					chkB10.setSelected(false);
					chkA10.setEnabled(false);
					chkB10.setEnabled(false);
				}
			}
		});
		

		chkA1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA1.isSelected() || chkB1.isSelected()) {
					btnSendTest1.setEnabled(true);
				} else {
					btnSendTest1.setEnabled(false);
				}
			}
		});
		chkA2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA2.isSelected() || chkB2.isSelected()) {
					btnSendTest2.setEnabled(true);
				} else {
					btnSendTest2.setEnabled(false);
				}
			}
		});
		chkA3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA3.isSelected() || chkB3.isSelected()) {
					btnSendTest3.setEnabled(true);
				} else {
					btnSendTest3.setEnabled(false);
				}
			}
		});
		chkA4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA4.isSelected() || chkB4.isSelected()) {
					btnSendTest4.setEnabled(true);
				} else {
					btnSendTest4.setEnabled(false);
				}
			}
		});
		chkA5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA5.isSelected() || chkB5.isSelected()) {
					btnSendTest5.setEnabled(true);
				} else {
					btnSendTest5.setEnabled(false);
				}
			}
		});
		chkA6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA6.isSelected() || chkB6.isSelected()) {
					btnSendTest6.setEnabled(true);
				} else {
					btnSendTest6.setEnabled(false);
				}
			}
		});
		chkA7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA7.isSelected() || chkB7.isSelected()) {
					btnSendTest7.setEnabled(true);
				} else {
					btnSendTest7.setEnabled(false);
				}
			}
		});
		chkA8.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA8.isSelected() || chkB8.isSelected()) {
					btnSendTest8.setEnabled(true);
				} else {
					btnSendTest8.setEnabled(false);
				}
			}
		});
		chkA9.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA9.isSelected() || chkB9.isSelected()) {
					btnSendTest9.setEnabled(true);
				} else {
					btnSendTest9.setEnabled(false);
				}
			}
		});
		chkA10.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA10.isSelected() || chkB10.isSelected()) {
					btnSendTest10.setEnabled(true);
				} else {
					btnSendTest10.setEnabled(false);
				}
			}
		});

		chkB1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA1.isSelected() || chkB1.isSelected()) {
					btnSendTest1.setEnabled(true);
				} else {
					btnSendTest1.setEnabled(false);
				}
			}
		});
		chkB2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA2.isSelected() || chkB2.isSelected()) {
					btnSendTest2.setEnabled(true);
				} else {
					btnSendTest2.setEnabled(false);
				}
			}
		});
		chkB3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA3.isSelected() || chkB3.isSelected()) {
					btnSendTest3.setEnabled(true);
				} else {
					btnSendTest3.setEnabled(false);
				}
			}
		});
		chkB4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA4.isSelected() || chkB4.isSelected()) {
					btnSendTest4.setEnabled(true);
				} else {
					btnSendTest4.setEnabled(false);
				}
			}
		});
		chkB5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA5.isSelected() || chkB5.isSelected()) {
					btnSendTest5.setEnabled(true);
				} else {
					btnSendTest5.setEnabled(false);
				}
			}
		});
		chkB6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA6.isSelected() || chkB6.isSelected()) {
					btnSendTest6.setEnabled(true);
				} else {
					btnSendTest6.setEnabled(false);
				}
			}
		});
		chkB7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA7.isSelected() || chkB7.isSelected()) {
					btnSendTest7.setEnabled(true);
				} else {
					btnSendTest7.setEnabled(false);
				}
			}
		});
		chkB8.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA8.isSelected() || chkB8.isSelected()) {
					btnSendTest8.setEnabled(true);
				} else {
					btnSendTest8.setEnabled(false);
				}
			}
		});
		chkB9.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA9.isSelected() || chkB9.isSelected()) {
					btnSendTest9.setEnabled(true);
				} else {
					btnSendTest9.setEnabled(false);
				}
			}
		});
		chkB10.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chkA10.isSelected() || chkB10.isSelected()) {
					btnSendTest10.setEnabled(true);
				} else {
					btnSendTest10.setEnabled(false);
				}
			}
		});
		
		
		btnSendTest1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort1.getSelectedIndex()-1], chkA1.isSelected(), chkB1.isSelected());
			}
		});
		btnSendTest2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort2.getSelectedIndex()-1], chkA2.isSelected(), chkB2.isSelected());
			}
		});
		btnSendTest3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort3.getSelectedIndex()-1], chkA3.isSelected(), chkB3.isSelected());
			}
		});
		btnSendTest4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort4.getSelectedIndex()-1], chkA4.isSelected(), chkB4.isSelected());
			}
		});
		btnSendTest5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort5.getSelectedIndex()-1], chkA5.isSelected(), chkB5.isSelected());
			}
		});
		btnSendTest6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort6.getSelectedIndex()-1], chkA6.isSelected(), chkB6.isSelected());
			}
		});
		btnSendTest7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort7.getSelectedIndex()-1], chkA7.isSelected(), chkB7.isSelected());
			}
		});
		btnSendTest8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort8.getSelectedIndex()-1], chkA8.isSelected(), chkB8.isSelected());
			}
		});
		btnSendTest9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort9.getSelectedIndex()-1], chkA9.isSelected(), chkB9.isSelected());
			}
		});
		btnSendTest10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCOMTest(comPort[cBoxPort10.getSelectedIndex()-1], chkA10.isSelected(), chkB10.isSelected());
			}
		});
		
	}
	
	private void updateInstruments() {
		for (String instrument: instrumentList) {
			cBoxInst1.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst2.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst3.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst4.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst5.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst6.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst7.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst8.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst9.addItem(instrument);
		}
		for (String instrument: instrumentList) {
			cBoxInst10.addItem(instrument);
		}
	}
	
	private void updateSerialPorts() {
		for (SerialPort port : comPort) {
			cBoxPort1.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort2.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort3.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort4.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort5.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort6.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort7.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort8.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort9.addItem(port.getSystemPortName());
		}
		for (SerialPort port : comPort) {
			cBoxPort10.addItem(port.getSystemPortName());
		}
	}
	
	private void sendCOMTest(SerialPort portToTest, boolean A, boolean B) {
		//Send a test signal to the selected COM port
	}
}

