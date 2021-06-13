package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import DAO.TaiKhoanDAOImpl;
import helpers.DataValidator;

/**
 * Class tạo giao diện cho chức năng quản lý tài khoản
 * 
 * @author Nguyễn Ngọc Trâm
 *
 */
@SuppressWarnings("serial")
public class QLPM_TaiKhoan extends JFrame implements ActionListener {
	private JLabel lblMSGV, lblTenGV, lblQuyenSD, lblTitle, lblTimKiem;
	private JTextField txtMSGV, txtTenGV, txtTimKiem;
	private JComboBox<String> cbbQuyenSD;
	private JButton btnSua, btnLuu, btnHuy, btnThoat, btnTimKiem, btnMatKhauMoi, btnTaiLai;
	private TaiKhoanSetTableModel model;
	private JTable table;
	private JScrollPane pane;

	public QLPM_TaiKhoan() {
		khoiTao();
		setLayout();
		ganGiaTri();
		TrangThaiKhiXem();

		// Sự kiện khi thao tác chuột trên bảng
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ganGiaTri();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	protected void khoiTao() {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("icon/a.png"));
		setIconImage(icon.getImage());
		setTitle("Quản lý tài khoản");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);

		// Khởi tạo cho các thuộc tính
		lblTitle = new JLabel("QUẢN LÝ TÀI KHOẢN");
		lblMSGV = new JLabel("Mã giảng viên");
		lblTenGV = new JLabel("Tên giảng viên");
		lblQuyenSD = new JLabel("Quyền sử dụng");
		lblTimKiem = new JLabel("Tìm kiếm");

		txtMSGV = new JTextField(50);
		txtTenGV = new JTextField(50);
		txtTimKiem = new JTextField(5);

		cbbQuyenSD = new JComboBox<String>();
		cbbQuyenSD.addItem("User");
		cbbQuyenSD.addItem("Admin");

		btnSua = new JButton("Thay đổi quyền");
		btnLuu = new JButton("Lưu");
		btnHuy = new JButton("Hủy");
		btnThoat = new JButton("Thoát");
		btnTimKiem = new JButton("Tìm kiếm theo mã GV");
		btnMatKhauMoi = new JButton("Cấp lại mật khẩu");
		btnTaiLai = new JButton("Làm mới");

		model = new TaiKhoanSetTableModel();
		table = new JTable(model);
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(2);
		tc.setCellRenderer(new QuyenRenderer());

		// Style cho các thành phần
		lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lblMSGV.setFont(new Font("Arial", Font.BOLD, 18));
		lblQuyenSD.setFont(new Font("Arial", Font.BOLD, 18));
		lblTenGV.setFont(new Font("Arial", Font.BOLD, 18));
		lblTimKiem.setFont(new Font("Arial", Font.BOLD, 18));

		txtMSGV.setFont(new Font("Arial", 0, 18));
		txtMSGV.setEditable(false);
		txtTenGV.setFont(new Font("Arial", 0, 18));
		txtTenGV.setEditable(false);
		txtTimKiem.setFont(new Font("Arial", 0, 18));

		cbbQuyenSD.setFont(new Font("Arial", 0, 18));

		table.changeSelection(0, 0, false, false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		btnSua.setFont(new Font("Arial", Font.BOLD, 24));
		btnSua.setBackground(new Color(9, 132, 227));
		btnSua.setBorderPainted(false);
		btnSua.setFocusable(false);
		btnSua.setForeground(Color.white);
		btnSua.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/sua.png"));
		btnSua.setIcon(icon);

		btnHuy.setFont(new Font("Arial", Font.BOLD, 24));
		btnHuy.setBackground(new Color(9, 132, 227));
		btnHuy.setBorderPainted(false);
		btnHuy.setFocusable(false);
		btnHuy.setForeground(Color.white);
		btnHuy.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/huy2.png"));
		btnHuy.setIcon(icon);

		btnLuu.setFont(new Font("Arial", Font.BOLD, 24));
		btnLuu.setBackground(new Color(9, 132, 227));
		btnLuu.setBorderPainted(false);
		btnLuu.setFocusable(false);
		btnLuu.setForeground(Color.white);
		btnLuu.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/luu.png"));
		btnLuu.setIcon(icon);

		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 24));
		btnTimKiem.setBackground(new Color(9, 132, 227));
		btnTimKiem.setBorderPainted(false);
		btnTimKiem.setFocusable(false);
		btnTimKiem.setForeground(Color.white);
		btnTimKiem.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/timkiem.png"));
		btnTimKiem.setIcon(icon);

		btnMatKhauMoi.setFont(new Font("Arial", Font.BOLD, 24));
		btnMatKhauMoi.setBackground(new Color(9, 132, 227));
		btnMatKhauMoi.setBorderPainted(false);
		btnMatKhauMoi.setFocusable(false);
		btnMatKhauMoi.setForeground(Color.white);
		btnMatKhauMoi.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/password.png"));
		btnMatKhauMoi.setIcon(icon);

		btnTaiLai.setFont(new Font("Arial", Font.BOLD, 24));
		btnTaiLai.setBackground(new Color(9, 132, 227));
		btnTaiLai.setBorderPainted(false);
		btnTaiLai.setFocusable(false);
		btnTaiLai.setForeground(Color.white);
		btnTaiLai.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/Refresh.png"));
		btnTaiLai.setIcon(icon);

		btnThoat.setFont(new Font("Arial", Font.BOLD, 24));
		btnThoat.setBackground(new Color(9, 132, 227));
		btnThoat.setBorderPainted(false);
		btnThoat.setFocusable(false);
		btnThoat.setForeground(Color.white);
		btnThoat.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/close.png"));
		btnThoat.setIcon(icon);
	}

	protected void setLayout() {
		setLayout(new GridBagLayout());
		// cho tiêu đề
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 12;
		gbc.insets = new Insets(20, 0, 20, 0);
		JPanel pTitle = new JPanel(new GridBagLayout());
		pTitle.add(lblTitle, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(20, 0, 20, 0), 0, 0));
		pTitle.setBackground(new Color(9, 132, 227));
		lblTitle.setForeground(Color.white);
		lblTitle.setHorizontalAlignment(JLabel.HORIZONTAL);
		getContentPane().add(pTitle, gbc);

		// cho các label
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 30, 0, 30);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(lblMSGV, gbc);
		gbc.gridy = 2;
		add(lblTenGV, gbc);
		gbc.gridy = 3;
		add(lblQuyenSD, gbc);
		gbc.gridy = 4;
		add(lblTimKiem, gbc);

		// cho các field
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 10, 20);
		gbc.gridwidth = 5;
		add(txtMSGV, gbc);
		gbc.gridy++;
		add(txtTenGV, gbc);
		gbc.gridy++;
		add(cbbQuyenSD, gbc);
		gbc.gridy++;
		gbc.gridwidth = 4;
		add(txtTimKiem, gbc);
		gbc.gridx = 5;
		gbc.gridwidth = 1;
		add(btnTimKiem, gbc);

		// cho bảng
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10, 20, 20, 20);
		add(pane, gbc);

		// cho các button
		JPanel jp = new JPanel();
		jp.setLayout(new GridBagLayout());
		GridBagConstraints p_gbc = new GridBagConstraints();
		p_gbc.fill = GridBagConstraints.BOTH;
		p_gbc.gridx = 0;
		p_gbc.gridy = 0;
		p_gbc.insets = new Insets(0, 5, 0, 5);
		jp.add(btnSua, p_gbc);
		p_gbc.gridx++;
		jp.add(btnLuu, p_gbc);
		p_gbc.gridx++;
		jp.add(btnHuy, p_gbc);
		p_gbc.gridx++;
		jp.add(btnMatKhauMoi, p_gbc);
		p_gbc.gridx++;
		jp.add(btnTaiLai, p_gbc);
		p_gbc.gridx++;
		jp.add(btnThoat, p_gbc);

		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridy = 7;
		add(jp, gbc);

		// Hàm để combobox khi bị disable vẫn hiện text màu đen cho dễ đọc
		cbbQuyenSD.setRenderer(new DefaultListCellRenderer() {
			@Override
			public void paint(Graphics g) {
				setForeground(Color.BLACK);
				super.paint(g);
			}
		});
	}

	private void ganGiaTri() {
		txtTimKiem.setText("");
		int r = table.getSelectedRow();

		txtMSGV.setText(table.getValueAt(r, 0).toString());
		txtTenGV.setText(table.getValueAt(r, 1).toString());

		if (Integer.parseInt(table.getValueAt(r, 2).toString()) == 0)
			cbbQuyenSD.setSelectedIndex(0);
		else
			cbbQuyenSD.setSelectedIndex(1);
	}

	private void TrangThaiKhiXem() {
		cbbQuyenSD.setEnabled(false);
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);

		txtTimKiem.setEditable(true);
		btnMatKhauMoi.setEnabled(true);
		btnSua.setEnabled(true);
		btnTaiLai.setEnabled(true);
		btnThoat.setEnabled(true);
		btnTimKiem.setEnabled(true);

		table.setEnabled(true);
	}

	private void TrangThaiKhiSua() {
		cbbQuyenSD.setEnabled(true);
		btnLuu.setEnabled(true);
		btnHuy.setEnabled(true);

		txtTimKiem.setEditable(false);
		btnMatKhauMoi.setEnabled(false);
		btnSua.setEnabled(false);
		btnTaiLai.setEnabled(false);
		btnThoat.setEnabled(false);
		btnTimKiem.setEnabled(false);

		table.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSua) {
			TrangThaiKhiSua();
		} else if (e.getSource() == btnHuy) {
			ganGiaTri();
			TrangThaiKhiXem();
		} else if (e.getSource() == btnTaiLai) {
			model = new TaiKhoanSetTableModel();
			table.setModel(model);
			TableColumnModel tcm = table.getColumnModel();
			TableColumn tc = tcm.getColumn(2);
			tc.setCellRenderer(new QuyenRenderer());
			table.changeSelection(0, 0, false, false);
			ganGiaTri();
		} else if (e.getSource() == btnThoat) {
			this.dispose();
		} else if (e.getSource() == btnLuu) {
			Luu();
		} else if (e.getSource() == btnTimKiem) {
			TimKiem();
		} else if (e.getSource() == btnMatKhauMoi) {
			String maGV = txtMSGV.getText();
			new QLPM_CapLaiMatKhau(maGV);
		}

	}

	private void Luu() {
		int result = 0;
		String maGiangVien = txtMSGV.getText();// Lấy mã giảng viên
		int quyen_moi;

		// Lấy quyền sử dụng mới
		if (cbbQuyenSD.getSelectedItem() == "Admin")
			quyen_moi = 1;
		else
			quyen_moi = 0;

		TaiKhoanDAOImpl tkDAO = new TaiKhoanDAOImpl();
		if (tkDAO.changeGrant(maGiangVien, quyen_moi) > 0) {
			JOptionPane.showMessageDialog(null, "Lưu thay đổi thành công!", "Thành công",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Lưu thay đổi không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int r = table.getSelectedRow();
		model = new TaiKhoanSetTableModel();
		table.setModel(model);
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(2);
		tc.setCellRenderer(new QuyenRenderer());
		table.changeSelection(r, 0, false, false);
		TrangThaiKhiXem();
		ganGiaTri();
	}

	private void TimKiem() {
		StringBuilder str = new StringBuilder();

		// Kiểm tra dữ liệu đã nhập hợp lệ chưa
		DataValidator.validateEmptyTextField(txtTimKiem, str, "Thầy/cô phải nhập vào ô tìm kiếm!");
		DataValidator.validateTextFieldLength(txtTimKiem, 5, str, "Thầy/cô chú ý mã giảng viên phải ít hơn hoặc bằng 5 ký tự!");

		// Nếu nhập chưa đủ thì hiện thông báo lỗi và return
		if (str.length() > 0) {
			JOptionPane.showMessageDialog(null, str, "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Nếu nhập vào hợp lệ thì tìm kiếm
		model = new TaiKhoanSetTableModel(txtTimKiem.getText());
		if (model.getRowCount() > 0) {
			table.setModel(model);
			TableColumnModel tcm = table.getColumnModel();
			TableColumn tc = tcm.getColumn(2);
			tc.setCellRenderer(new QuyenRenderer());
			table.changeSelection(0, 0, false, false);
			ganGiaTri();
		} else
			JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Lỗi", JOptionPane.ERROR_MESSAGE);

	}
}
