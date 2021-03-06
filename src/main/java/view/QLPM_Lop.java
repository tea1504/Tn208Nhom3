package view;

import java.awt.BorderLayout;
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

import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.GiangVienDAOImpl;
import DAO.LopDAOImpl;
import bean.GiangVien;
import bean.Lop;
import helpers.SharedData;

/**
 * Class tạo giao diện quản lý lớp học
 * 
 * @author Trương Trung Trọng
 *
 */
@SuppressWarnings("serial")
public class QLPM_Lop extends JFrame {
	private ThongTin thongtin;
	private Table table;
	private dieukhien dk;
	private JLabel title = new JLabel("QUẢN LÝ LỚP HỌC");
	private JPanel pTitle = new JPanel();

// lop giao dien

	public QLPM_Lop() {
		thongtin = new ThongTin();
		table = new Table(thongtin);
		dk = new dieukhien(thongtin, table, this);
		title.setFont(new Font("Arial", Font.BOLD, 50));
		title.setHorizontalAlignment(JLabel.HORIZONTAL);
		title.setForeground(Color.white);
		pTitle.setLayout(new BorderLayout());
		pTitle.add(title, BorderLayout.CENTER);
		pTitle.setBackground(new Color(9, 132, 227));
		setSize(1400, 800);
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
//		gbc.insets = new Insets(10, 0, 20, 0);
		getContentPane().add(pTitle, gbc);

		gbc.insets = new Insets(0, 20, 5, 20);
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridy++;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		getContentPane().add(thongtin, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy++;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		getContentPane().add(table, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy++;
		getContentPane().add(dk, gbc);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("icon/a.png"));
		setLocationRelativeTo(null);
		setIconImage(icon.getImage());
		setTitle("Quản lý lớp học");
		setVisible(true);
		
	}

	/////// set table//////////
	private class Table extends JPanel {
		private LopSetTableModel model = new LopSetTableModel();
		private JTable table;
		private final ThongTin tt;
		private JScrollPane pane;
		private boolean ok = true;

		public Table(ThongTin _tt) {
			tt = _tt;
			table = new JTable(model);
			table.changeSelection(0, 0, false, false);
			table.setFont(new Font("Tahoma", Font.PLAIN, 18));
			table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
			table.setRowHeight(30);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridx = 0;
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			add(pane, gbc);
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
						tt.txtMaLop.setText(table.getValueAt(r, 0).toString());
						tt.txtTenLop.setText(table.getValueAt(r, 2).toString());
						String ma = table.getValueAt(r, 1).toString();
						GiangVienDAOImpl gvdao = new GiangVienDAOImpl();
						ArrayList<GiangVien> list = gvdao.getGiangVien();
						int index = -1;
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getMaGiangVien().compareTo(ma) == 0)
								index = i;
						}
						tt.cboGiangVien.setSelectedIndex(index);
						tt.txtSiSo.setText(table.getValueAt(r, 3).toString());
					}
				}
			});
		}

		public LopSetTableModel getModel() {
			return model;
		}

		public void setModel(LopSetTableModel model) {
			this.model = model;
		}

		public JTable getTable() {
			return table;
		}

		public void setTable(JTable table) {
			this.table = table;
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

		public void DKKT() {
			table.setEnabled(false);
			ok = false;
		}

		public void DKKBT() {
			table.setEnabled(true);
			ok = true;
		}

	}

	//////////////// nut chuc nang//////////

	public class dieukhien extends JPanel implements ActionListener {
		private JButton btnThem = new JButton("Thêm");
		private JButton btnSua = new JButton("Sửa");
		private JButton btnXoa = new JButton("Xóa");
		private JButton btnLuu = new JButton("Lưu");
		private JButton btnHuy = new JButton("Hủy");
		private JButton btnThoat = new JButton("Thoát");
		private JButton btnTimKiem = new JButton("Tìm kiếm");
		private JButton btnExcel = new JButton("Xuất excel");
		private JFileChooser fileChooser = new JFileChooser();
		private final ThongTin tt;
		private final Table tb;
		private final QLPM_Lop lop;
		private boolean them = false;

		public dieukhien(ThongTin _tt, Table _tb, QLPM_Lop l) {
			tt = _tt;
			tb = _tb;
			lop = l;
			setup();
			phanquyentrenLop();
			GanDL();
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weighty = 1.0;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0, 5, 0, 5);
			add(btnThem, gbc);
			gbc.gridx++;
			add(btnSua, gbc);
			gbc.gridx++;
			add(btnXoa, gbc);
			gbc.gridx++;
			add(btnLuu, gbc);
			gbc.gridx++;
			add(btnHuy, gbc);
			gbc.gridx++;
			add(btnExcel, gbc);
			gbc.gridx++;
			add(btnThoat, gbc);
			gbc.gridx++;
			add(btnTimKiem, gbc);
			DKKBT();
			
		}

		// cai dat cac nut chuc nang///////
		private void setup() {
			btnThem.setFont(new Font("Arial", Font.BOLD, 24));
			btnThem.setBackground(new Color(9, 132, 227));
			btnThem.setBorderPainted(false);
			btnThem.setFocusable(false);
			btnThem.setForeground(Color.white);
			btnThem.addActionListener(this);
			Icon icon = new ImageIcon(this.getClass().getResource("icon/add.png"));
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

			btnTimKiem.setFont(new Font("Arial", Font.BOLD, 24));
			btnTimKiem.setBackground(new Color(9, 132, 227));
			btnTimKiem.setBorderPainted(false);
			btnTimKiem.setFocusable(false);
			btnTimKiem.setForeground(Color.white);
			btnTimKiem.addActionListener(this);
			icon = new ImageIcon(this.getClass().getResource("icon/timkiem.png"));
			btnTimKiem.setIcon(icon);

			btnExcel.setFont(new Font("Arial", Font.BOLD, 24));
			btnExcel.setBackground(new Color(9, 132, 227));
			btnExcel.setBorderPainted(false);
			btnExcel.setFocusable(false);
			btnExcel.setForeground(Color.white);
			btnExcel.addActionListener(this);
			icon = new ImageIcon(this.getClass().getResource("icon/excel.png"));
			btnExcel.setIcon(icon);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Hủy":
					DKKBT();
					GanDL();
					them = false;
					break;
				case "Thêm":
					them = true;
					DKKT();
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
					lop.dispose();
					break;
				case "Tìm kiếm":
					timlop();
					;
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
			fileChooser.setSelectedFile(new File("Lop.xlsx"));
			int res = fileChooser.showSaveDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("Lop");
				GiangVienDAOImpl giangVienDAO = new GiangVienDAOImpl();
				LopDAOImpl LopDAO = new LopDAOImpl();
				ArrayList<Lop> list = LopDAO.getLop();

				org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 17);

				CellStyle headerStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);

				Row headerRow = sheet.createRow(0);
				String columns[] = { "Mã lớp", "Giảng viên", "Tên lớp", "Sỉ số lớp" };
				for (int i = 0; i < columns.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columns[i]);
					cell.setCellStyle(headerStyle);
				}

				int rowIndex = 1;

				for (Lop item : list) {
					Row row = sheet.createRow(rowIndex++);
					row.createCell(0).setCellValue(item.getMaLop());
					row.createCell(1).setCellValue(giangVienDAO.getGiangVien(item.getMaGiangVien()).getTenGiangVien());
					row.createCell(2).setCellValue(item.getTenLop());
					row.createCell(3).setCellValue(item.getSiSoLop());
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

//gan du lieu
		private void GanDL() {
			int r = tb.getTable().getSelectedRow();
			tt.txtMaLop.setText(tb.getTable().getValueAt(r, 0).toString());
			tt.txtTenLop.setText(tb.getTable().getValueAt(r, 2).toString());
			String ma = tb.getTable().getValueAt(r, 1).toString();
			GiangVienDAOImpl gvdao = new GiangVienDAOImpl();
			ArrayList<GiangVien> list = gvdao.getGiangVien();
			int index = -1;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMaGiangVien().compareTo(ma) == 0)
					index = i;
			}
			tt.cboGiangVien.setSelectedIndex(index);
			tt.txtSiSo.setText(tb.getTable().getValueAt(r, 3).toString());
		}

//dieu khien khi binh thuong
		private void DKKBT() {
			tt.DKKBT();
			tb.DKKBT();
			btnLuu.setEnabled(false);
			btnHuy.setEnabled(false);
			btnThem.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
			btnThoat.setEnabled(true);
			btnTimKiem.setEnabled(true);
			btnExcel.setEnabled(true);
			tt.txttimkiem.setEnabled(true);
			phanquyentrenLop();
		}

//dieu khien khi them
		private void DKKT() {
			DKKS();
			tt.DKKT();
			btnTimKiem.setEnabled(false);
			tt.txttimkiem.setEnabled(false);
		}

// dieu khien khi chinh sua
		private void DKKS() {
			tb.DKKT();
			tt.DKKS();
			btnLuu.setEnabled(true);
			btnHuy.setEnabled(true);
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
			btnThoat.setEnabled(false);
			btnTimKiem.setEnabled(false);
			tt.txttimkiem.setEnabled(false);
			btnExcel.setEnabled(false);
		}

// chuc nang luu
		private void Luu() throws SQLException {
			if (check()) {
				int row = tb.getTable().getSelectedRow();
				if (them) {
					GiangVien gv = (GiangVien) tt.cboGiangVien.getSelectedItem();
					Lop l = new Lop(tt.txtMaLop.getText(), gv.getMaGiangVien(), tt.txtTenLop.getText(),
							Integer.parseInt(tt.txtSiSo.getText()));
					LopDAOImpl ctrl = new LopDAOImpl();
					if (ctrl.checkKhoaChinh(tt.txtMaLop.getText())) {
						JOptionPane.showMessageDialog(null, "Mã lớp bị trùng không thể lưu", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						
						return;
					} else {
						boolean r = ctrl.ThemLop(l);
						LopSetTableModel model = new LopSetTableModel();
						tb.getTable().setModel(model);
						tb.getTable().changeSelection(row, 0, false, false);
						if (r)
							JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
						them = false;
					}
				} else {
					GiangVien gv = (GiangVien) tt.cboGiangVien.getSelectedItem();
					Lop l = new Lop(tt.txtMaLop.getText(), gv.getMaGiangVien(), tt.txtTenLop.getText(),
							Integer.parseInt(tt.txtSiSo.getText()));
					LopDAOImpl ctrl = new LopDAOImpl();
					boolean r = ctrl.SuaLop(l);
					tb.getTable().setModel(new LopSetTableModel());
					tb.getTable().changeSelection(row, 0, false, false);
					if (r)
						JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
				}
				DKKBT();
				GanDL();
			}
		};

// chuc nang xoa
		private void Xoa() throws SQLException {
			int result = JOptionPane.showConfirmDialog(null, "Xác nhận xóa dữ liệu", "thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
			if (result == JOptionPane.YES_OPTION) {
				GiangVien gv = (GiangVien) tt.cboGiangVien.getSelectedItem();
				Lop l = new Lop(tt.txtMaLop.getText(), tt.txtTenLop.getText(), gv.getMaGiangVien(),
						Integer.parseInt(tt.txtSiSo.getText()));
				LopDAOImpl ctrl = new LopDAOImpl();
				boolean r = ctrl.XoaLop(l);
				LopSetTableModel model = new LopSetTableModel();
				tb.getTable().setModel(model);
				tb.getTable().changeSelection(0, 0, false, false);
				if (r)
					JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Đã hủy thao tác xóa dữ liệu", "thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DKKBT();
			GanDL();
		}

		// ham tim kiem lop

		public void timlop() {
			LopDAOImpl lopDAO = new LopDAOImpl();
			ArrayList<Lop> lop = lopDAO.timLop(tt.txttimkiem.getText());
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(new Object[] { "Mã lớp", "Giảng viên", "Tên lớp", "Sỉ số lớp" });
			Object[] row = new Object[4];
			if (lop.size() > 0) {
				for (int i = 0; i < lop.size(); i++) {
					row[0] = lop.get(i).getMaLop();
					row[1] = lop.get(i).getMaGiangVien();
					row[2] = lop.get(i).getTenLop();
					row[3] = lop.get(i).getSiSoLop();
					model.addRow(row);
				}
				tb.getTable().setModel(model);
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		private boolean isNumeric(String str) {
			try {
				Double.parseDouble(str);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		private boolean check() {
			if (tt.txtMaLop.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập mã lớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (tt.txtTenLop.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập tên lớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (tt.txtSiSo.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập sỉ số lớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (!isNumeric(tt.txtSiSo.getText())) {
				JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập số vào trường sỉ số lớp", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (tt.txtMaLop.getText().length() > 7) {
				JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập mã lớp tối đa 7 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (tt.txtTenLop.getText().length() > 50) {
				JOptionPane.showMessageDialog(null, "Thầy/cô phải nhập tên lớp tối đa 50 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}

		// phan quyen tren lop

		private void phanquyentrenLop() {
			if (SharedData.CurentAccount.getQuyenSD() == 1) {
				btnXoa.setEnabled(true);
				btnThem.setEnabled(true);
				btnSua.setEnabled(true);

			} else {
				btnThem.setEnabled(false);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);

			}
		}
	}

	/////// cai view cho khung hien thi ////////////

	private class ThongTin extends JPanel implements ActionListener {
		JLabel lblMaLop = new JLabel("Mã lớp:");
		JLabel lblTenLop = new JLabel("Tên lớp:");
		JLabel lblGiaoVien = new JLabel("Giảng viên dạy:");
		JLabel lblSiSo = new JLabel("Sỉ số lớp:");
		JLabel lbltimkiem = new JLabel("Tìm kiếm:");
		JTextField txtMaLop = new JTextField(10);
		JTextField txtTenLop = new JTextField(10);
		JTextField txtSiSo = new JTextField(10);
		JTextField txttimkiem = new JTextField(10);
		JComboBox<GiangVien> cboGiangVien = new JComboBox<GiangVien>();

// set bang lop
		public ThongTin() {
			cboGiangVien.addActionListener(this);
			setCombobox();
			setup();
			setLayout(new GridBagLayout());
//			setBorder(new TitledBorder(BorderFactory.createTitledBorder("Nhập liệu")));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			add(lblMaLop, gbc);
			gbc.gridy++;

			add(lblTenLop, gbc);
			gbc.gridy++;

			add(lblGiaoVien, gbc);
			gbc.gridy++;

			add(lblSiSo, gbc);
			gbc.gridy++;

			add(lbltimkiem, gbc);
			gbc.gridx++;

			gbc.gridy = 0;
			gbc.weightx = 1.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(0, 5, 0, 0);
			add(txtMaLop, gbc);
			gbc.gridy++;
			add(txtTenLop, gbc);
			gbc.gridy++;
			add(cboGiangVien, gbc);
			gbc.gridy++;
			add(txtSiSo, gbc);
			gbc.gridy++;
			add(txttimkiem, gbc);
			DKKBT();
		}

		// cai dat list giang vien
		public void setCombobox() {
			GiangVienDAOImpl gv = new GiangVienDAOImpl();
			ArrayList<GiangVien> list = gv.getGiangVien();
			for (GiangVien item : list) {
				cboGiangVien.addItem(item);
			}
		}

		public void setTxtMaLop(String text) {
			txtMaLop.setText(text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == cboGiangVien) {
			}
		}

		// dieu khien khi binh thuong
		public void DKKBT() {
			cboGiangVien.setEnabled(false);
			cboGiangVien.setEditable(false);
			txtMaLop.setEditable(false);
			txtTenLop.setEditable(false);
			txtSiSo.setEditable(false);
			txtMaLop.requestFocus(false);
			txtTenLop.requestFocus(false);
		}

		// dieu khien khi them
		public void DKKT() {
			DKKS();
			txtMaLop.setEditable(true);
			txtMaLop.setText("");
			txtTenLop.setText("");
			txtSiSo.setText("");
			cboGiangVien.setSelectedIndex(0);
			txtMaLop.requestFocus();
		}

		// dieu khien khi chinh sua
		public void DKKS() {
			txtTenLop.setEditable(true);
			txtSiSo.setEditable(true);
			cboGiangVien.setEnabled(true);
			txtTenLop.requestFocus();
		}

		// set các nút chức năng
		public void setup() {
			lblMaLop.setFont(new Font("Arial", Font.BOLD, 18));
			lblGiaoVien.setFont(new Font("Arial", Font.BOLD, 18));
			lblTenLop.setFont(new Font("Arial", Font.BOLD, 18));
			lblSiSo.setFont(new Font("Arial", Font.BOLD, 18));
			lbltimkiem.setFont(new Font("Arial", Font.BOLD, 18));
			txtMaLop.setFont(new Font("Arial", 0, 18));
			txtSiSo.setFont(new Font("Arial", 0, 18));
			txtTenLop.setFont(new Font("Arial", 0, 18));
			cboGiangVien.setFont(new Font("Arial", 0, 18));
			txttimkiem.setFont(new Font("Arial", 0, 18));
		}
	}
	
}