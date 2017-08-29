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
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class SerialSettings extends JDialog {

	/*public static void main(String[] args) {
		try {
			SerialSettings dialog = new SerialSettings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public SerialSettings() {
		setTitle("Serial Port Configuration");
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new MigLayout("", "[grow][grow][]", "[33px][][][][][][][][][][][][][]"));
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
			JComboBox cBoxInst1 = new JComboBox();
			getContentPane().add(cBoxInst1, "cell 0 2,growx");
		}
		{
			JComboBox cBoxPort1 = new JComboBox();
			getContentPane().add(cBoxPort1, "cell 1 2,growx");
		}
		{
			JRadioButton rdbtnA1 = new JRadioButton("A");
			getContentPane().add(rdbtnA1, "flowx,cell 2 2");
		}
		{
			JComboBox cBoxInst2 = new JComboBox();
			getContentPane().add(cBoxInst2, "cell 0 3,growx");
		}
		{
			JComboBox cBoxPort2 = new JComboBox();
			getContentPane().add(cBoxPort2, "cell 1 3,growx");
		}
		{
			JRadioButton rdbtnA2 = new JRadioButton("A");
			getContentPane().add(rdbtnA2, "flowx,cell 2 3");
		}
		{
			JComboBox cBoxInst3 = new JComboBox();
			getContentPane().add(cBoxInst3, "cell 0 4,growx");
		}
		{
			JComboBox cBoxPort3 = new JComboBox();
			getContentPane().add(cBoxPort3, "cell 1 4,growx");
		}
		{
			JRadioButton rdbtnA3 = new JRadioButton("A");
			getContentPane().add(rdbtnA3, "flowx,cell 2 4");
		}
		{
			JComboBox cBoxInst4 = new JComboBox();
			getContentPane().add(cBoxInst4, "cell 0 5,growx");
		}
		{
			JComboBox cBoxPort4 = new JComboBox();
			getContentPane().add(cBoxPort4, "cell 1 5,growx");
		}
		{
			JRadioButton rdbtnA4 = new JRadioButton("A");
			getContentPane().add(rdbtnA4, "flowx,cell 2 5");
		}
		{
			JComboBox cBoxInst5 = new JComboBox();
			getContentPane().add(cBoxInst5, "cell 0 6,growx");
		}
		{
			JComboBox cBoxPort5 = new JComboBox();
			getContentPane().add(cBoxPort5, "cell 1 6,growx");
		}
		{
			JRadioButton rdbtnA5 = new JRadioButton("A");
			getContentPane().add(rdbtnA5, "flowx,cell 2 6");
		}
		{
			JComboBox cBoxInst6 = new JComboBox();
			getContentPane().add(cBoxInst6, "cell 0 7,growx");
		}
		{
			JComboBox cBoxPort6 = new JComboBox();
			getContentPane().add(cBoxPort6, "cell 1 7,growx");
		}
		{
			JRadioButton rdbtnA6 = new JRadioButton("A");
			getContentPane().add(rdbtnA6, "flowx,cell 2 7");
		}
		{
			JComboBox cBoxInst7 = new JComboBox();
			getContentPane().add(cBoxInst7, "cell 0 8,growx");
		}
		{
			JComboBox cBoxPort7 = new JComboBox();
			getContentPane().add(cBoxPort7, "cell 1 8,growx");
		}
		{
			JRadioButton rdbtnA7 = new JRadioButton("A");
			getContentPane().add(rdbtnA7, "flowx,cell 2 8");
		}
		{
			JComboBox cBoxInst8 = new JComboBox();
			getContentPane().add(cBoxInst8, "cell 0 9,growx");
		}
		{
			JComboBox cBoxPort8 = new JComboBox();
			getContentPane().add(cBoxPort8, "cell 1 9,growx");
		}
		{
			JRadioButton rdbtnA8 = new JRadioButton("A");
			getContentPane().add(rdbtnA8, "flowx,cell 2 9");
		}
		{
			JComboBox cBoxInst9 = new JComboBox();
			getContentPane().add(cBoxInst9, "cell 0 10,growx");
		}
		{
			JComboBox cBoxPort9 = new JComboBox();
			getContentPane().add(cBoxPort9, "cell 1 10,growx");
		}
		{
			JRadioButton rdbtnA9 = new JRadioButton("A");
			getContentPane().add(rdbtnA9, "flowx,cell 2 10");
		}
		{
			JComboBox cBoxInst10 = new JComboBox();
			getContentPane().add(cBoxInst10, "cell 0 11,growx");
		}
		{
			JComboBox cBoxPort10 = new JComboBox();
			getContentPane().add(cBoxPort10, "cell 1 11,growx");
		}
		{
			JRadioButton rdbtnA10 = new JRadioButton("A");
			getContentPane().add(rdbtnA10, "flowx,cell 2 11");
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
		{
			JRadioButton rdbtnB1 = new JRadioButton("B");
			getContentPane().add(rdbtnB1, "cell 2 2");
		}
		{
			JRadioButton rdbtnB2 = new JRadioButton("B");
			getContentPane().add(rdbtnB2, "cell 2 3");
		}
		{
			JRadioButton rdbtnB3 = new JRadioButton("B");
			getContentPane().add(rdbtnB3, "cell 2 4");
		}
		{
			JRadioButton rdbtnB4 = new JRadioButton("B");
			getContentPane().add(rdbtnB4, "cell 2 5");
		}
		{
			JRadioButton rdbtnB5 = new JRadioButton("B");
			getContentPane().add(rdbtnB5, "cell 2 6");
		}
		{
			JRadioButton rdbtnB6 = new JRadioButton("B");
			getContentPane().add(rdbtnB6, "cell 2 7");
		}
		{
			JRadioButton rdbtnB7 = new JRadioButton("B");
			getContentPane().add(rdbtnB7, "cell 2 8");
		}
		{
			JRadioButton rdbtnB8 = new JRadioButton("B");
			getContentPane().add(rdbtnB8, "cell 2 9");
		}
		{
			JRadioButton rdbtnB9 = new JRadioButton("B");
			getContentPane().add(rdbtnB9, "cell 2 10");
		}
		{
			JRadioButton rdbtnB10 = new JRadioButton("B");
			getContentPane().add(rdbtnB10, "cell 2 11");
		}
	}

}
