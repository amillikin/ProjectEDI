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

public class SerialSettings extends JDialog {
	private static SerialPort[] comPort = SerialPort.getCommPorts();
	private static final String[] instrumentList = new String[] {"","Snare","Tom","Hi-hat","Bass","Ride Cymbal","Crash Cymbal"};
	//private Integer[] blank
	
	public SerialSettings() {
		setTitle("Serial Port Configuration");
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new MigLayout("", "[grow][grow][][]", "[33px][][][][][][][][][][][][][]"));
		{
			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			getContentPane().add(toolBar, "flowx,cell 0 0");
			{
				JButton btnSave = new JButton("Save");
				toolBar.add(btnSave);
			}
			{
				JButton btnLoad = new JButton("Load");
				toolBar.add(btnLoad);
			}
		}
		{
			JLabel lblInstrument = new JLabel("Instrument");
			lblInstrument.setFont(new Font("Tahoma", Font.BOLD, 11));
			getContentPane().add(lblInstrument, "cell 0 1,alignx center,aligny center");
		}
		{
			JLabel lblPort = new JLabel("Port");
			lblPort.setFont(new Font("Tahoma", Font.BOLD, 11));
			getContentPane().add(lblPort, "cell 1 1,alignx center,aligny center");
		}
		{
			JLabel lblCh = new JLabel("CH");
			lblCh.setFont(new Font("Tahoma", Font.BOLD, 11));
			getContentPane().add(lblCh, "cell 2 1,alignx center,aligny center");
		}
		
		{
			JComboBox<String> cBoxInst1 = new JComboBox<String>();
			cBoxInst1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					Object item = cBoxInst1.getSelectedItem();
					setInstCBox(1,item);
				}
			});
			for (String instrument: instrumentList) {
				cBoxInst1.addItem(instrument);
			}
			{
				JLabel lblSendTest = new JLabel("Send Test");
				lblSendTest.setFont(new Font("Tahoma", Font.BOLD, 11));
				getContentPane().add(lblSendTest, "cell 3 1");
			}
			getContentPane().add(cBoxInst1, "cell 0 2,growx");
		}
		{
			JComboBox<String> cBoxInst2 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst2.addItem(instrument);
			}
			{
				JButton btnSendTest1 = new JButton("SEND");
				btnSendTest1.setEnabled(false);
				btnSendTest1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				getContentPane().add(btnSendTest1, "cell 3 2");
			}
			getContentPane().add(cBoxInst2, "cell 0 3,growx");
		}
		{
			JComboBox<String> cBoxInst3 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst3.addItem(instrument);
			}
			{
				JButton btnSendTest2 = new JButton("SEND");
				btnSendTest2.setEnabled(false);
				getContentPane().add(btnSendTest2, "cell 3 3");
			}
			getContentPane().add(cBoxInst3, "cell 0 4,growx");
		}
		{
			JComboBox<String> cBoxInst4 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst4.addItem(instrument);
			}
			{
				JButton btnSendTest3 = new JButton("SEND");
				btnSendTest3.setEnabled(false);
				getContentPane().add(btnSendTest3, "cell 3 4");
			}
			getContentPane().add(cBoxInst4, "cell 0 5,growx");
		}
		{
			JComboBox<String> cBoxInst5 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst5.addItem(instrument);
			}
			{
				JButton btnSendTest4 = new JButton("SEND");
				btnSendTest4.setEnabled(false);
				getContentPane().add(btnSendTest4, "cell 3 5");
			}
			getContentPane().add(cBoxInst5, "cell 0 6,growx");
		}
		{
			JComboBox<String> cBoxInst6 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst6.addItem(instrument);
			}
			{
				JButton btnSendTest5 = new JButton("SEND");
				btnSendTest5.setEnabled(false);
				getContentPane().add(btnSendTest5, "cell 3 6");
			}
			getContentPane().add(cBoxInst6, "cell 0 7,growx");
		}
		{
			JComboBox<String> cBoxInst7 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst7.addItem(instrument);
			}
			{
				JButton btnSendTest6 = new JButton("SEND");
				btnSendTest6.setEnabled(false);
				getContentPane().add(btnSendTest6, "cell 3 7");
			}
			getContentPane().add(cBoxInst7, "cell 0 8,growx");
		}
		{
			JComboBox<String> cBoxInst8 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst8.addItem(instrument);
			}
			{
				JButton btnSendTest7 = new JButton("SEND");
				btnSendTest7.setEnabled(false);
				getContentPane().add(btnSendTest7, "cell 3 8");
			}
			getContentPane().add(cBoxInst8, "cell 0 9,growx");
		}
		{
			JComboBox<String> cBoxInst9 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst9.addItem(instrument);
			}
			{
				JButton btnSendTest8 = new JButton("SEND");
				btnSendTest8.setEnabled(false);
				getContentPane().add(btnSendTest8, "cell 3 9");
			}
			getContentPane().add(cBoxInst9, "cell 0 10,growx");
		}
		{
			JComboBox<String> cBoxInst10 = new JComboBox<String>();
			for (String instrument: instrumentList) {
				cBoxInst10.addItem(instrument);
			}
			{
				JButton btnSendTest9 = new JButton("SEND");
				btnSendTest9.setEnabled(false);
				getContentPane().add(btnSendTest9, "cell 3 10");
			}
			getContentPane().add(cBoxInst10, "cell 0 11,growx");
		}
		{
			JComboBox<String> cBoxPort1 = new JComboBox<String>();
			cBoxPort1.setEnabled(false);
			cBoxPort1.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort1.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort1, "cell 1 2,growx");
		}
		{
			JComboBox<String> cBoxPort2 = new JComboBox<String>();
			cBoxPort2.setEnabled(false);
			cBoxPort2.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort2.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort2, "cell 1 3,growx");
		}
		{
			JComboBox<String> cBoxPort3 = new JComboBox<String>();
			cBoxPort3.setEnabled(false);
			cBoxPort3.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort3.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort3, "cell 1 4,growx");
		}
		{
			JComboBox<String> cBoxPort4 = new JComboBox<String>();
			cBoxPort4.setEnabled(false);
			cBoxPort4.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort4.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort4, "cell 1 5,growx");
		}
		{
			JComboBox<String> cBoxPort5 = new JComboBox<String>();
			cBoxPort5.setEnabled(false);
			cBoxPort5.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort5.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort5, "cell 1 6,growx");
		}
		{
			JComboBox<String> cBoxPort6 = new JComboBox<String>();
			cBoxPort6.setEnabled(false);
			cBoxPort6.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort6.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort6, "cell 1 7,growx");
		}
		{
			JComboBox<String> cBoxPort7 = new JComboBox<String>();
			cBoxPort7.setEnabled(false);
			cBoxPort7.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort7.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort7, "cell 1 8,growx");
		}

		{
			JComboBox<String> cBoxPort8 = new JComboBox<String>();
			cBoxPort8.setEnabled(false);
			cBoxPort8.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort8.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort8, "cell 1 9,growx");
		}
		{
			JComboBox<String> cBoxPort9 = new JComboBox<String>();
			cBoxPort9.setEnabled(false);
			cBoxPort9.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort9.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort9, "cell 1 10,growx");
		}
		{
			JComboBox<String> cBoxPort10 = new JComboBox<String>();
			cBoxPort10.setEnabled(false);
			cBoxPort10.addItem("");
			for (SerialPort port : comPort) {
				cBoxPort10.addItem(port.getSystemPortName());
			}
			getContentPane().add(cBoxPort10, "cell 1 11,growx");
		}
		{
			JCheckBox chkA1 = new JCheckBox("A");
			chkA1.setEnabled(false);
			getContentPane().add(chkA1, "flowx,cell 2 2");
		}
		{
			JCheckBox chkA2 = new JCheckBox("A");
			chkA2.setEnabled(false);
			getContentPane().add(chkA2, "flowx,cell 2 3");
		}
		{
			JCheckBox chkA3 = new JCheckBox("A");
			chkA3.setEnabled(false);
			getContentPane().add(chkA3, "flowx,cell 2 4");
		}
		{
			JCheckBox chkA4 = new JCheckBox("A");
			chkA4.setEnabled(false);
			getContentPane().add(chkA4, "flowx,cell 2 5");
		}
		{
			JCheckBox chkA5 = new JCheckBox("A");
			chkA5.setEnabled(false);
			getContentPane().add(chkA5, "flowx,cell 2 6");
		}
		{
			JCheckBox chkA6 = new JCheckBox("A");
			chkA6.setEnabled(false);
			getContentPane().add(chkA6, "flowx,cell 2 7");
		}
		{
			JCheckBox chkA7 = new JCheckBox("A");
			chkA7.setEnabled(false);
			getContentPane().add(chkA7, "flowx,cell 2 8");
		}
		{
			JCheckBox chkA8 = new JCheckBox("A");
			chkA8.setEnabled(false);
			getContentPane().add(chkA8, "flowx,cell 2 9");
			
		}
		{
			JCheckBox chkA9 = new JCheckBox("A");
			chkA9.setEnabled(false);
			getContentPane().add(chkA9, "flowx,cell 2 10");
		}
		{
			JCheckBox chkA10 = new JCheckBox("A");
			chkA10.setEnabled(false);
			getContentPane().add(chkA10, "flowx,cell 2 11");
		}
		{
			JCheckBox chkB1 = new JCheckBox("B");
			chkB1.setEnabled(false);
			getContentPane().add(chkB1, "cell 2 2");
		}
		{
			JCheckBox chkB2 = new JCheckBox("B");
			chkB2.setEnabled(false);
			getContentPane().add(chkB2, "cell 2 3");
		}
		{
			JCheckBox chkB3 = new JCheckBox("B");
			chkB3.setEnabled(false);
			getContentPane().add(chkB3, "cell 2 4");
		}
		{
			JCheckBox chkB4 = new JCheckBox("B");
			chkB4.setEnabled(false);
			getContentPane().add(chkB4, "cell 2 5");
		}
		{
			JCheckBox chkB5 = new JCheckBox("B");
			chkB5.setEnabled(false);
			getContentPane().add(chkB5, "cell 2 6");
		}
		{
			JCheckBox chkB6 = new JCheckBox("B");
			chkB6.setEnabled(false);
			getContentPane().add(chkB6, "cell 2 7");
		}
		{
			JCheckBox chkB7 = new JCheckBox("B");
			chkB7.setEnabled(false);
			getContentPane().add(chkB7, "cell 2 8");
		}
		{
			JCheckBox chkB8 = new JCheckBox("B");
			chkB8.setEnabled(false);
			getContentPane().add(chkB8, "cell 2 9");
		}
		{
			JCheckBox chkB9 = new JCheckBox("B");
			chkB9.setEnabled(false);
			getContentPane().add(chkB9, "cell 2 10");
		}
		{
			JCheckBox chkB10 = new JCheckBox("B");
			chkB10.setEnabled(false);
			getContentPane().add(chkB10, "cell 2 11");
		}
		{
			JButton btnSendTest10 = new JButton("SEND");
			btnSendTest10.setEnabled(false);
			getContentPane().add(btnSendTest10, "cell 3 11");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, "cell 0 13,alignx left,aligny top");
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private static void setInstCBox(Integer cBoxRef, Object item) {
		switch (cBoxRef) {
			case 1:		if ("".equals(item)) {
							cBoxPort1.setEnabled(true);
						}
						else {
							cBoxPort1.setEnabled(false);
						}
						break;
	
			case 2:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
						break;
	
			case 3:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
						break;
	
			case 4:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
						break;
	
			case 5:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
						break;
	
			case 6:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
						break;
	
			case 7:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
						break;
	
			case 8:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
						break;
	
			case 9:		if ("".equals(item)) {
							activeCBox.setEnabled(true);
							}
						else {
							activeCBox.setEnabled(false);
							}
						break;
	
			case 10: 	if ("".equals(item)) {
							activeCBox.setEnabled(true);
						}
						else {
							activeCBox.setEnabled(false);
						}
					break;	
		
		}

	}
}
