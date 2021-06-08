package view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Class dùng để render buổi từ 0, 1, 2 thành sáng, chiều, tối
 * 
 * @author Trần Văn Hòa
 *
 */
@SuppressWarnings("serial")
public class BuoiRenderer extends JComboBox<String> implements TableCellRenderer {

	public BuoiRenderer() {
		super();
		addItem("Sáng");
		addItem("Chiều");
		addItem("Tối");
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
		int buoi = (int) value;
		setSelectedIndex(buoi);
		return this;
	}

}
