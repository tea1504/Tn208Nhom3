package view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Class dùng để quyền sử dụng 0, 1 thành user, admin
 * 
 * @author Trần Văn Hòa
 *
 */
@SuppressWarnings("serial")
public class QuyenRenderer extends JComboBox<String> implements TableCellRenderer {
	public QuyenRenderer() {
		addItem("User");
		addItem("Admin");
		setFont(new Font("Tahoma", Font.PLAIN, 18));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		int quyen = (int) value;
		setSelectedIndex(quyen);
		return this;
	}

}
