package gui;

import javax.swing.table.DefaultTableModel;

public class UneditableTableModel extends DefaultTableModel{
	
	public UneditableTableModel() {
			super(new Object[][] {},new String[] {"#", "Title", "Duration"});
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
