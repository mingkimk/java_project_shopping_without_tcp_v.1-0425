package Manager;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Manager.onemore.MyItemListener;

public class MyCheckbox extends DefaultTableCellRenderer {
//	@Override
//	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//			int row, int column) {
//		Component comp = null;
//		if (column == 4) {
//			// comp = new JCheckBox();
//			JCheckBox box = new JCheckBox();
//			box.setBorderPainted(true);
//			box.setHorizontalAlignment(JLabel.CENTER);
//			// box.setSelected(((Boolean) value).booleanValue());
//			comp = box;
//		}
//		return comp;
//	}

	@Override
	public JCheckBox getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JCheckBox box = null;
		if (column == 4) {
			box = new JCheckBox();
			box.setBorderPainted(true);
			box.setHorizontalAlignment(JLabel.CENTER);
		}
		return box;
	}

}