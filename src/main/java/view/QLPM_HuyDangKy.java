package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import DAO.DangKyDAOImpl;

/**
 * Tạo giao diện cho chức năng <strong>hủy đăng ký</strong>
 * 
 * @author Trần Văn Hòa
 */
@SuppressWarnings("serial")
public class QLPM_HuyDangKy extends JFrame implements ActionListener {
	private JPanel pTitle, pTable, pButton;
	private JLabel title;
	private JTable table;
	private JButton btnHuy;

	/**
	 * Khởi tạo giao diện cho chức năng <strong>hủy đăng ký</strong>
	 * 
	 * @author Trần Văn Hòa
	 */
	public QLPM_HuyDangKy() {
		khoiTaoFrame();
	}

	/**
	 * Khởi tạo frame cho chức năng <strong>hủy đăng ký</strong>
	 * 
	 * @author Trần Văn Hòa
	 */
	private void khoiTaoFrame() {
		setupTitle();
		setupTable();
		setupButton();
		setBounds(300, 100, 1200, 800);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		getContentPane().add(pTitle, gbc); // Thêm panel Title
		gbc.weighty = 0.8;
		gbc.gridy++;
		gbc.insets = new Insets(10, 50, 10, 50);
		getContentPane().add(pTable, gbc); // Thêm panel Table
		gbc.weighty = 0.1;
		gbc.gridy++;
		getContentPane().add(pButton, gbc); // Thêm panel Button
		setVisible(true);
	}

	/**
	 * Khởi tạo <strong>panel Title</strong>, bao gồm:
	 * <ul>
	 * <li>JLabel title</li>
	 * </ul>
	 * 
	 * @author Trần Văn Hòa
	 */
	private void setupTitle() {
		title = new JLabel("Hủy đăng ký phòng máy");
		pTitle = new JPanel();
		title.setHorizontalAlignment(JLabel.HORIZONTAL);
		title.setFont(new Font("Arial", Font.BOLD, 60));
		title.setForeground(Color.WHITE);
		pTitle.add(title, BorderLayout.CENTER); // Thêm title
		pTitle.setBackground(new Color(9, 132, 227));
	}

	/**
	 * Khởi tạo <strong>panel Table</strong>, bao gồm:
	 * <ul>
	 * <li>JTable table</li>
	 * </ul>
	 * 
	 * @author Trần Văn Hòa
	 */
	private void setupTable() {
		pTable = new JPanel();
		table = new JTable(new DangKySetTableModel());
		// Render cho cột buổi
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(4);
		tc.setCellRenderer(new BuoiRenderer());
		// Set font cho table
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateRowSorter(true);
		table.changeSelection(0, 0, false, false);
		JScrollPane scorll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pTable.setLayout(new BorderLayout());
		pTable.add(scorll, BorderLayout.CENTER);
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Danh sách đã đăng ký");
		titleBorder.setTitleFont(new Font("Arial", Font.BOLD, 30));
		pTable.setBorder(titleBorder);
	}

	/**
	 * Khởi tạo <strong>panel Button</strong>, bao gồm:
	 * <ul>
	 * <li>JButton btnHuy</li>
	 * </ul>
	 * 
	 * @author Trần Văn Hòa
	 */
	private void setupButton() {
		btnHuy = new JButton("Hủy đăng ký");
		btnHuy.setFont(new Font("Arial", Font.BOLD, 30));
		btnHuy.setBackground(new Color(9, 132, 227));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBorderPainted(false);
		btnHuy.setFocusable(false);
		btnHuy.addActionListener(this);
		pButton = new JPanel();
		pButton.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		pButton.add(btnHuy, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DangKyDAOImpl dangKyDAO = new DangKyDAOImpl();
		if (e.getSource() == btnHuy) {
			int result = JOptionPane.showConfirmDialog(null, "Thầy/Cô muốn hủy đăng ký phòng", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				int res = dangKyDAO.DeleteDangKy((int) table.getValueAt(table.getSelectedRow(), 0));
				if (res == 1) {
					JOptionPane.showMessageDialog(null, "Hủy đăng ký thành công", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);
					table.setModel(new DangKySetTableModel());
					// Render cho cột buổi
					TableColumnModel tcm = table.getColumnModel();
					TableColumn tc = tcm.getColumn(4);
					tc.setCellRenderer(new BuoiRenderer());
				} else {
					JOptionPane.showMessageDialog(null, "Hủy đăng ký không thành công", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
