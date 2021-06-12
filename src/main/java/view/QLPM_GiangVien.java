package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.GiangVienDAOImpl;
import DAO.PhongMayDAOImpl;
import bean.GiangVien;
import bean.Phong;
import helpers.SharedData;

/**
 * Class tạo giao diện quản lý giảng viên
 * 
 * @author Trịnh Thanh Thảo
 *
 */
@SuppressWarnings("serial")
public class QLPM_GiangVien extends JFrame implements ActionListener {

	private JLabel title = new JLabel("QUẢN LÝ GIẢNG VIÊN");

	JLabel lblMaGiangVien = new JLabel("Mã giảng viên:");
	JLabel lblTenGiangVien = new JLabel("Tên giảng viên:");
	JLabel lblQuyenSD = new JLabel("Quyền sử dụng:");
	JLabel lblTKMGV = new JLabel("Tìm kiếm theo giảng viên:");

	JTextField txtMaGiangVien = new JTextField(10);
	JTextField txtTenGiangVien = new JTextField(10);
	JTextField txtTKMGV = new JTextField(10);

	String quyensd[] = { "Admin", "User" };
	JComboBox<String> cboQuyenSD = new JComboBox<String>(quyensd);

	private JButton btnTimKiem = new JButton("Tìm kiếm");
	private JButton btnThem = new JButton("Thêm");
	private JButton btnSua = new JButton("Sửa");
	private JButton btnXoa = new JButton("Xóa");
	private JButton btnLuu = new JButton("Lưu");
	private JButton btnHuy = new JButton("Hủy");
	private JButton btnThoat = new JButton("Thoát");
	private JButton btnExcel = new JButton("Xuất excel");
	private boolean them = false;

	private GiangVienSetTableModel model = new GiangVienSetTableModel();
	private JTable table = new JTable();
	private JScrollPane pane;
	private JFileChooser fileChooser = new JFileChooser();
	private boolean ok = true;

	public QLPM_GiangVien() {
		setup();
		phanQuyen();

		title.setFont(new Font("Arial", Font.BOLD, 50));
		title.setHorizontalAlignment(JLabel.HORIZONTAL);
		title.setForeground(Color.white);
		JPanel pTitle = new JPanel(new GridBagLayout());
		pTitle.add(title, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(20, 0, 20, 0), 0, 0));
		pTitle.setBackground(new Color(9, 132, 227));
		setSize(1400, 800);
		getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 12;
		gbc.insets = new Insets(20, 0, 20, 0);
		getContentPane().add(pTitle, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 30, 0, 30);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(lblMaGiangVien, gbc);
		gbc.gridy = 2;
		add(lblTenGiangVien, gbc);
		gbc.gridy = 3;
		add(lblQuyenSD, gbc);
		gbc.gridy = 4;
		add(lblTKMGV, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 10, 20);
		gbc.gridwidth = 6;
		add(txtMaGiangVien, gbc);
		gbc.gridy++;
		add(txtTenGiangVien, gbc);
		gbc.gridy++;
		add(cboQuyenSD, gbc);
		gbc.gridy++;
		gbc.gridwidth = 4;
		add(txtTKMGV, gbc);

		gbc.gridx = 5;
		gbc.gridwidth = 2;
		add(btnTimKiem, gbc);
		DKKBT();

		table = new JTable(model);
		table.changeSelection(0, 0, false, false);
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(2);
		tc.setCellRenderer(new QuyenRenderer());
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 7;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10, 20, 20, 20);
		add(pane, gbc);

		GanDL();

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (ok) {
					int r = table.getSelectedRow();
					txtMaGiangVien.setText(table.getValueAt(r, 0).toString());
					txtTenGiangVien.setText(table.getValueAt(r, 1).toString());
					String ma = table.getValueAt(r, 0).toString();
					GiangVienDAOImpl gvdao = new GiangVienDAOImpl();
					ArrayList<GiangVien> list = gvdao.getGiangVien();
					int index = -1;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getMaGiangVien().compareTo(ma) == 0)
							index = i;
					}
					if (Integer.parseInt(table.getValueAt(r, 2).toString()) == 0) {
						cboQuyenSD.setSelectedIndex(1);
					} else {
						cboQuyenSD.setSelectedIndex(0);
					}
				}
			}

		});

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 20, 10, 20);
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.weightx = 1;
		add(btnThem, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(btnSua, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(btnXoa, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(btnLuu, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(btnHuy, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(btnExcel, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(btnThoat, gbc);

		ImageIcon icon = new ImageIcon(this.getClass().getResource("icon/a.png"));
		setIconImage(icon.getImage());
		setTitle("Quản lý giảng viên");
//			setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public GiangVienSetTableModel getModel() {
		return model;
	}

	public void setModel(GiangVienSetTableModel model) {
		this.model = model;
	}

	public JScrollPane getPane() {
		return pane;
	}

	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public void setTxtMGiangVien(String text) {
		txtMaGiangVien.setText(text);
	}

	private void setup() {
		lblMaGiangVien.setFont(new Font("Arial", Font.BOLD, 18));
		lblTenGiangVien.setFont(new Font("Arial", Font.BOLD, 18));
		lblQuyenSD.setFont(new Font("Arial", Font.BOLD, 18));
		lblTKMGV.setFont(new Font("Arial", Font.BOLD, 18));
		txtMaGiangVien.setFont(new Font("Arial", 0, 18));
		txtTenGiangVien.setFont(new Font("Arial", 0, 18));
		cboQuyenSD.setFont(new Font("Arial", 0, 18));
		txtTKMGV.setFont(new Font("Arial", 0, 18));

		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 24));
		btnTimKiem.setBackground(new Color(9, 132, 227));
		btnTimKiem.setBorderPainted(false);
		btnTimKiem.setFocusable(false);
		btnTimKiem.setForeground(Color.white);
		btnTimKiem.addActionListener(this);
		Icon icon = new ImageIcon(this.getClass().getResource("icon/timkiem.png"));
		btnTimKiem.setIcon(icon);

		btnThem.setFont(new Font("Arial", Font.BOLD, 24));
		btnThem.setBackground(new Color(9, 132, 227));
		btnThem.setBorderPainted(false);
		btnThem.setFocusable(false);
		btnThem.setForeground(Color.white);
		btnThem.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/add.png"));
		btnThem.setIcon(icon);

		btnHuy.setFont(new Font("Arial", Font.BOLD, 24));
		btnHuy.setBackground(new Color(9, 132, 227));
		btnHuy.setBorderPainted(false);
		btnHuy.setFocusable(false);
		btnHuy.setForeground(Color.white);
		btnHuy.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/huy2.png"));
		btnHuy.setIcon(icon);

		btnSua.setFont(new Font("Arial", Font.BOLD, 24));
		btnSua.setBackground(new Color(9, 132, 227));
		btnSua.setBorderPainted(false);
		btnSua.setFocusable(false);
		btnSua.setForeground(Color.white);
		btnSua.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/sua.png"));
		btnSua.setIcon(icon);

		btnXoa.setFont(new Font("Arial", Font.BOLD, 24));
		btnXoa.setBackground(new Color(9, 132, 227));
		btnXoa.setBorderPainted(false);
		btnXoa.setFocusable(false);
		btnXoa.setForeground(Color.white);
		btnXoa.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/xoa.png"));
		btnXoa.setIcon(icon);

		btnLuu.setFont(new Font("Arial", Font.BOLD, 24));
		btnLuu.setBackground(new Color(9, 132, 227));
		btnLuu.setBorderPainted(false);
		btnLuu.setFocusable(false);
		btnLuu.setForeground(Color.white);
		btnLuu.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/luu.png"));
		btnLuu.setIcon(icon);

		btnThoat.setFont(new Font("Arial", Font.BOLD, 24));
		btnThoat.setBackground(new Color(9, 132, 227));
		btnThoat.setBorderPainted(false);
		btnThoat.setFocusable(false);
		btnThoat.setForeground(Color.white);
		btnThoat.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/close.png"));
		btnThoat.setIcon(icon);

		btnExcel.setFont(new Font("Arial", Font.BOLD, 24));
		btnExcel.setBackground(new Color(9, 132, 227));
		btnExcel.setBorderPainted(false);
		btnExcel.setFocusable(false);
		btnExcel.setForeground(Color.white);
		btnExcel.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("icon/excel.png"));
		btnExcel.setIcon(icon);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "Hủy":
				model = new GiangVienSetTableModel();
				table.setModel(model);
				table.changeSelection(0, 0, false, false);
				DKKBT();
				GanDL();
				them = false;
				break;
			case "Thêm":
				DKKT();
				them = true;
				break;
			case "Lưu":
				try {
					Luu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case "Xóa":
				try {
					Xoa();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Sửa":
				DKKS();
				break;
			case "Thoát":
				this.dispose();
				break;
			case "Tìm kiếm":
				TimGiangVien();
				break;
			case "Xuất excel":
				XuatExcel();
				break;
			default:
				break;
			}
		}
	}

	private void XuatExcel() {
		fileChooser.setSelectedFile(new File("GiangVien.xlsx"));
		int res = fileChooser.showSaveDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("GiangVien");
			GiangVienDAOImpl giangVienDAO = new GiangVienDAOImpl();
			ArrayList<GiangVien> list = giangVienDAO.TimGiangVien("");

			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 17);

			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);
			String columns[] = { "Mã giảng viên", "Tên giảng viên", "Quyền sử dụng" };
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerStyle);
			}

			int rowIndex = 1;

			for (GiangVien giangVien : list) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(giangVien.getMaGiangVien());
				row.createCell(1).setCellValue(giangVien.getTenGiangVien());
				if (giangVien.getQuyenSD() == 0)
					row.createCell(2).setCellValue("User");
				else
					row.createCell(2).setCellValue("Admin");
			}
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				workbook.write(fileOutputStream);
				fileOutputStream.close();
				workbook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void GanDL() {
		int r = table.getSelectedRow();
		txtMaGiangVien.setText(table.getValueAt(r, 0).toString());
		txtTenGiangVien.setText(table.getValueAt(r, 1).toString());
		String ma = table.getValueAt(r, 0).toString();
		GiangVienDAOImpl gvdao = new GiangVienDAOImpl();
		ArrayList<GiangVien> list = gvdao.getGiangVien();
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaGiangVien().compareTo(ma) == 0)
				index = i;
		}
		if (Integer.parseInt(table.getValueAt(r, 2).toString()) == 0) {
			cboQuyenSD.setSelectedIndex(1);
		} else {
			cboQuyenSD.setSelectedIndex(0);
		}
	}

	private void DKKBT() {
		cboQuyenSD.setEnabled(false);
		txtMaGiangVien.setEditable(false);
		txtTenGiangVien.setEditable(false);
		txtTKMGV.setEditable(true);
		txtTKMGV.setText("");
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		btnThem.setEnabled(true);
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);
		btnThoat.setEnabled(true);
		btnTimKiem.setEnabled(true);
		btnExcel.setEnabled(true);
		table.setEnabled(true);
		txtTenGiangVien.requestFocus(false);
		txtMaGiangVien.requestFocus(false);
		ok = true;
		phanQuyen();
	}

	private void DKKT() {
		cboQuyenSD.setEnabled(false);
		cboQuyenSD.setSelectedIndex(1);

		txtMaGiangVien.setEditable(true);
		txtMaGiangVien.requestFocus();
		txtMaGiangVien.setText("");

		txtTenGiangVien.setEditable(true);
		txtTenGiangVien.setText("");

		txtTKMGV.setEditable(false);
		table.setEnabled(false);
		ok = false;

		btnLuu.setEnabled(true);
		btnHuy.setEnabled(true);
		btnThem.setEnabled(false);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnThoat.setEnabled(false);
		btnTimKiem.setEnabled(false);
		btnExcel.setEnabled(false);
	}

	private void DKKS() {
		txtTenGiangVien.setEditable(true);
		txtTenGiangVien.requestFocus();
		cboQuyenSD.setEditable(true);
		txtTKMGV.setText("");
		txtTKMGV.setEditable(false);
		btnLuu.setEnabled(true);
		btnHuy.setEnabled(true);
		btnThem.setEnabled(false);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnThoat.setEnabled(false);
		btnTimKiem.setEnabled(false);
		btnExcel.setEnabled(false);
		table.setEnabled(false);
	}

	private void phanQuyen() {
		if (SharedData.CurentAccount.getQuyenSD() == 1) {
			btnThem.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		} else {
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}
	}

	private void Luu() throws SQLException {
		if (check()) {
			int row = table.getSelectedRow();
			if (them) {
				GiangVien gv = new GiangVien(txtMaGiangVien.getText(), txtTenGiangVien.getText());
				GiangVienDAOImpl ctrl = new GiangVienDAOImpl();
				if (ctrl.checkKhoaChinh(txtMaGiangVien.getText())) {
					JOptionPane.showMessageDialog(null, "Mã giảng viên bị trùng không thể lưu", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean r = ctrl.ThemGiangVien(gv);
				GiangVienSetTableModel model = new GiangVienSetTableModel();
				table.setModel(model);
				TableColumnModel tcm = table.getColumnModel();
				TableColumn tc = tcm.getColumn(2);
				tc.setCellRenderer(new QuyenRenderer());
				table.changeSelection(row, 0, false, false);
				if (r)
					JOptionPane.showMessageDialog(null, "Không lưu được dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,
							"Đã lưu \n Mã giảng viên: " + txtMaGiangVien.getText() + " \nTên giảng viên: "
									+ txtTenGiangVien.getText() + "\n Mật khẩu: 12345 " + "\n Quyền sử dụng: User",
							"OK", JOptionPane.INFORMATION_MESSAGE);
				them = false;
			} else {
				GiangVien gv = new GiangVien(txtMaGiangVien.getText(), txtTenGiangVien.getText());
				GiangVienDAOImpl ctrl = new GiangVienDAOImpl();
				boolean r = ctrl.SuaGiangVien(gv);
				table.setModel(new GiangVienSetTableModel());
				TableColumnModel tcm = table.getColumnModel();
				TableColumn tc = tcm.getColumn(2);
				tc.setCellRenderer(new QuyenRenderer());
				table.changeSelection(row, 0, false, false);
				if (r)
					JOptionPane.showMessageDialog(null, "Không lưu được dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,
							"Đã lưu \n Mã giảng viên: " + txtMaGiangVien.getText() + " \nTên giảng viên: "
									+ txtTenGiangVien.getText()
									+ "\n Lưu ý: Chỉ có thể sửa quyền sử dụng ở bảng Tài Khoản",
							"OK", JOptionPane.INFORMATION_MESSAGE);
			}
			DKKBT();
			GanDL();
		}
	};

	private void Xoa() throws SQLException {
		int result = JOptionPane.showConfirmDialog(null, "Thầy/cô chắc chắn muốn xóa dữ liệu ?", "Thông báo",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		if (result == JOptionPane.YES_OPTION) {
			GiangVien gv = new GiangVien(txtMaGiangVien.getText(), txtTenGiangVien.getText());
			GiangVienDAOImpl ctrl = new GiangVienDAOImpl();
			boolean r = ctrl.XoaGiangVien(gv);
			GiangVienSetTableModel model = new GiangVienSetTableModel();
			table.setModel(model);
			TableColumnModel tcm = table.getColumnModel();
			TableColumn tc = tcm.getColumn(2);
			tc.setCellRenderer(new QuyenRenderer());
			table.changeSelection(0, 0, false, false);
			if (r)
				JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Đã xóa dữ liệu của giảng viên " + txtTenGiangVien.getText(),
						"Thông báo", JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, "Đã hủy thao tác xóa", "Nhắc nhở", JOptionPane.INFORMATION_MESSAGE);
		}
		DKKBT();
		GanDL();
	}

	private boolean check() {
		if (txtMaGiangVien.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Thầy/cô chưa nhập mã giảng viên !!!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (txtTenGiangVien.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Thầy/cô chưa nhập tên giảng viên !!!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}else if (txtMaGiangVien.getText().length() > 5) {
			JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập mã giảng viên tối đa 5 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (txtTenGiangVien.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập tên giảng viên tối đa 50 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void TimGiangVien() {
		GiangVienDAOImpl ctrl = new GiangVienDAOImpl();
		ArrayList<GiangVien> gv = ctrl.TimGiangVien(txtTKMGV.getText());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] { "Mã giảng viên", "Tên giảng viên", "Quyền sử dụng" });
		Object[] row = new Object[3];
		if (gv.size() > 0) {
			for (int i = 0; i < gv.size(); i++) {
				row[0] = gv.get(i).getMaGiangVien();
				row[1] = gv.get(i).getTenGiangVien();
				row[2] = gv.get(i).getQuyenSD();
				model.addRow(row);
			}
			table.setModel(model);
			TableColumnModel tcm = table.getColumnModel();
			TableColumn tc = tcm.getColumn(2);
			tc.setCellRenderer(new QuyenRenderer());
			table.setRowSelectionInterval(0, 0);
			GanDL();
		} else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
