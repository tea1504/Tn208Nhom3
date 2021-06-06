package view.phong;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.PhongDAO;
import bean.Phong;
import helpers.SharedData;


@SuppressWarnings("serial")
public class PhongGUI extends JFrame implements ActionListener{
	private JLabel title = new JLabel("QUẢN LÝ PHÒNG HỌC");
	
	JLabel lblMaPhong = new JLabel("Mã phòng:");
	JLabel lblTenPhong = new JLabel("Tên phòng:");
	JLabel lblSoLuongMay = new JLabel("Số lượng máy:");
	JLabel lblTimKiem = new JLabel("Tìm phòng:");
	JTextField txtMaPhong = new JTextField(10);
	JTextField txtTenPhong = new JTextField(10);
	JTextField txtSoLuongMay = new JTextField(10);
	JTextField txtTimKiem = new JTextField(10);
	
	private JButton btnTimKiem = new JButton("Tìm kiếm");
	private JButton btnThem = new JButton("Thêm");
	private JButton btnSua = new JButton("Sửa");
	private JButton btnXoa = new JButton("Xóa");
	private JButton btnLuu = new JButton("Lưu");
	private JButton btnHuy = new JButton("Hủy");
	private JButton btnThoat = new JButton("Thoát");
	private boolean them = false;
	
	private PhongSetTableModel model = new PhongSetTableModel();
	private JTable table = new JTable();
	private JScrollPane pane;
	private boolean ok = true;

	public PhongGUI() {
		setup();
		phanQuyen();
		title.setFont(new Font("Arial", Font.BOLD, 50));
		setSize(1000, 700);
		getContentPane().setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 12;
		gbc.insets = new Insets(20, 0, 20, 0);
		getContentPane().add(title, gbc);
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 30, 0, 30 );
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(lblMaPhong, gbc);
		gbc.gridy = 2;
		add(lblTenPhong, gbc);
		gbc.gridy = 3;
		add(lblSoLuongMay, gbc);
		gbc.gridy = 4;
		add(lblTimKiem, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 10, 20);
		gbc.gridwidth = 5;
		add(txtMaPhong, gbc);
		gbc.gridy++;
		add(txtTenPhong, gbc);
		gbc.gridy++;
		add(txtSoLuongMay, gbc);
		gbc.gridy++;
		add(txtTimKiem, gbc);
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 20, 0, 20);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 5;
		add(btnTimKiem, gbc);
		DKKBT();
		
		table = new JTable(model);
		table.changeSelection(0, 0, false, false);
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
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10, 20, 20, 20);
		add(pane, gbc);
		GanDL();

		table.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				if (ok) {
					int r = table.getSelectedRow();
					txtMaPhong.setText(table.getValueAt(r, 0).toString());
					txtTenPhong.setText(table.getValueAt(r, 1).toString());
					txtSoLuongMay.setText(table.getValueAt(r, 2).toString());
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
		add(btnThoat, gbc);

		ImageIcon icon = new ImageIcon(this.getClass().getResource("a.png"));
		setIconImage(icon.getImage());
		setTitle("Quản lý phòng học");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void setTxtMaPhong(String text) {
		txtMaPhong.setText(text);
	}
	
	public PhongSetTableModel getModel() {
		return model;
	}

	public void setModel(PhongSetTableModel model) {
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
	
	private void setup() {
		lblMaPhong.setFont(new Font("Arial", Font.BOLD, 18));
		lblTenPhong.setFont(new Font("Arial", Font.BOLD, 18));
		lblSoLuongMay.setFont(new Font("Arial", Font.BOLD, 18));
		lblTimKiem.setFont(new Font("Arial", Font.BOLD, 18));
		txtMaPhong.setFont(new Font("Arial",0, 18));
		txtTenPhong.setFont(new Font("Arial",0, 18));
		txtSoLuongMay.setFont(new Font("Arial",0, 18));
		txtTimKiem.setFont(new Font("Arial",0, 18));
		
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 24));
		btnTimKiem.setBackground(new Color(9, 132, 227));
		btnTimKiem.setBorderPainted(false);
		btnTimKiem.setFocusable(false);
		btnTimKiem.setForeground(Color.white);
		btnTimKiem.addActionListener(this);
		Icon icon = new ImageIcon(this.getClass().getResource("timkiem.png"));
		btnTimKiem.setIcon(icon);
		
		btnThem.setFont(new Font("Arial", Font.BOLD, 24));
		btnThem.setBackground(new Color(9, 132, 227));
		btnThem.setBorderPainted(false);
		btnThem.setFocusable(false);
		btnThem.setForeground(Color.white);
		btnThem.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("add.png"));
		btnThem.setIcon(icon);

		btnHuy.setFont(new Font("Arial", Font.BOLD, 24));
		btnHuy.setBackground(new Color(9, 132, 227));
		btnHuy.setBorderPainted(false);
		btnHuy.setFocusable(false);
		btnHuy.setForeground(Color.white);
		btnHuy.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("huy.png"));
		btnHuy.setIcon(icon);

		btnSua.setFont(new Font("Arial", Font.BOLD, 24));
		btnSua.setBackground(new Color(9, 132, 227));
		btnSua.setBorderPainted(false);
		btnSua.setFocusable(false);
		btnSua.setForeground(Color.white);
		btnSua.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("sua.png"));
		btnSua.setIcon(icon);

		btnXoa.setFont(new Font("Arial", Font.BOLD, 24));
		btnXoa.setBackground(new Color(9, 132, 227));
		btnXoa.setBorderPainted(false);
		btnXoa.setFocusable(false);
		btnXoa.setForeground(Color.white);
		btnXoa.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("xoa.png"));
		btnXoa.setIcon(icon);

		btnLuu.setFont(new Font("Arial", Font.BOLD, 24));
		btnLuu.setBackground(new Color(9, 132, 227));
		btnLuu.setBorderPainted(false);
		btnLuu.setFocusable(false);
		btnLuu.setForeground(Color.white);
		btnLuu.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("luu.png"));
		btnLuu.setIcon(icon);

		btnThoat.setFont(new Font("Arial", Font.BOLD, 24));
		btnThoat.setBackground(new Color(9, 132, 227));
		btnThoat.setBorderPainted(false);
		btnThoat.setFocusable(false);
		btnThoat.setForeground(Color.white);
		btnThoat.addActionListener(this);
		icon = new ImageIcon(this.getClass().getResource("close.png"));
		btnThoat.setIcon(icon);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "Hủy":
				table.setRowSelectionInterval(0, 0);
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
				them = false;
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
				TimPhong();
				break;
			default:
				break;
			}
		}
	}

	private void GanDL() {
		int r = table.getSelectedRow();
		txtMaPhong.setText(table.getValueAt(r, 0).toString());
		txtTenPhong.setText(table.getValueAt(r, 1).toString());
		txtSoLuongMay.setText(table.getValueAt(r, 2).toString());
	}

	private void DKKBT() {
		txtMaPhong.setEditable(false);
		txtTenPhong.setEditable(false);
		txtSoLuongMay.setEditable(false);
		txtTimKiem.setEditable(true);
		txtTimKiem.setText("");
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		btnThem.setEnabled(true);
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);
		btnThoat.setEnabled(true);
		btnTimKiem.setEnabled(true);
		table.setEnabled(true);
		ok = true;
		phanQuyen();
	}

	private void DKKT() {
		DKKS();
		txtMaPhong.setEditable(true);
		txtMaPhong.requestFocus();
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtSoLuongMay.setText("");
		table.setEnabled(false);
		ok = false;
	}

	private void DKKS() {
		txtTenPhong.setEditable(true);
		txtTenPhong.requestFocus();
		txtSoLuongMay.setEditable(true);
		txtTimKiem.setText("");
		txtTimKiem.setEditable(false);
		btnLuu.setEnabled(true);
		btnHuy.setEnabled(true);
		btnThem.setEnabled(false);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnThoat.setEnabled(false);
		btnTimKiem.doClick();
		btnTimKiem.setEnabled(false);
	}
	
	private void phanQuyen() {
		if (SharedData.CurentAccount.getQuyenSD() == 1) {
			btnThem.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		} 
		else {
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}
	}

	private void Luu() throws SQLException {
		if (check()) {
			int row = table.getSelectedRow();
			if (them) {
				Phong p = new Phong(txtMaPhong.getText(), txtTenPhong.getText(), Integer.parseInt(txtSoLuongMay.getText()));
				PhongDAO ctrl = new PhongDAO();
				boolean r = ctrl.ThemPhong(p);
				PhongSetTableModel model = new PhongSetTableModel();
				table.setModel(model);
				table.changeSelection(row, 0, false, false);
				if (r)
					JOptionPane.showMessageDialog(null, "Lưu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Lưu Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				table.setRowSelectionInterval(0, 0);
			}
			else {
				Phong p = new Phong(txtMaPhong.getText(), txtTenPhong.getText(), Integer.parseInt(txtSoLuongMay.getText()));
				PhongDAO ctrl = new PhongDAO();
				boolean r = ctrl.SuaPhong(p);
				table.setModel(new PhongSetTableModel());
				table.changeSelection(row, 0, false, false);
				if (r)
					JOptionPane.showMessageDialog(null, "Lưu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Lưu Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
			DKKBT();
			GanDL();
		}
	};

	private void Xoa() throws SQLException {
		int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ?", "Thông báo", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null);
		if (result == JOptionPane.YES_OPTION) {
			Phong p = new Phong(txtMaPhong.getText(), txtTenPhong.getText(),Integer.parseInt(txtSoLuongMay.getText()));
			PhongDAO ctrl = new PhongDAO();
			boolean r = ctrl.XoaPhong(p);
			PhongSetTableModel model = new PhongSetTableModel();
			table.setModel(model);
			table.changeSelection(0, 0, false, false);
			if (r)
				JOptionPane.showMessageDialog(null, "Xóa không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		}
		DKKBT();
		GanDL();
	}

	public void TimPhong()
    {
		PhongDAO ctrl = new PhongDAO();
        ArrayList<Phong> phongs = ctrl.TimPhong(txtTimKiem.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Mã phòng","Tên phòng","Số lượng máy"});
        Object[] row = new Object[3];
        
        for(int i = 0; i < phongs.size(); i++)
        {
            row[0] = phongs.get(i).getMaPhong();
            row[1] = phongs.get(i).getTenPhong();
            row[2] = phongs.get(i).getSoLuongMay();
            model.addRow(row);
        }
       table.setModel(model);
       table.setRowSelectionInterval(0, 0);
       GanDL();
    }
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	private boolean check() {
		if (txtMaPhong.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập mã phòng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (txtTenPhong.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (txtSoLuongMay.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng máy", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if(!isNumeric(txtSoLuongMay.getText())) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	

}
