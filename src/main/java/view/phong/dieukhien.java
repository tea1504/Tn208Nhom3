package view.phong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import bean.Phong;
import DAO.PhongDAO;

@SuppressWarnings("serial")
public class dieukhien extends JPanel implements ActionListener {
	private JButton btnThem = new JButton("Thêm");
	private JButton btnSua = new JButton("Sửa");
	private JButton btnXoa = new JButton("Xóa");
	private JButton btnLuu = new JButton("Lưu");
	private JButton btnHuy = new JButton("Hủy");
	private JButton btnThoat = new JButton("Thoát");
	private final ThongTin tt;
	private final Table tb;
	private final PhongGUI phong;
	private boolean them = false;

	public dieukhien(ThongTin _tt, Table _tb, PhongGUI p) {
		tt = _tt;
		tb = _tb;
		phong = p;
		setup();
		GanDL();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
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
		add(btnThoat, gbc);
		DKKBT();
	}

	private void setup() {
		btnThem.setFont(new Font("Arial", Font.BOLD, 24));
		btnThem.setBackground(new Color(9, 132, 227));
		btnThem.setBorderPainted(false);
		btnThem.setFocusable(false);
		btnThem.setForeground(Color.white);
		btnThem.addActionListener(this);
		Icon icon = new ImageIcon(this.getClass().getResource("add.png"));
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
				Luu();
				them = false;
				break;
			case "Xóa":
				Xoa();
				break;
			case "Sửa":
				DKKS();
				break;
			case "Thoát":
				phong.dispose();
				break;
			default:
				break;
			}
		}
	}

	private void GanDL() {
		int r = tb.getTable().getSelectedRow();
		tt.txtMaPhong.setText(tb.getTable().getValueAt(r, 0).toString());
		tt.txtTenPhong.setText(tb.getTable().getValueAt(r, 1).toString());
		tt.txtSoLuongMay.setText(tb.getTable().getValueAt(r, 2).toString());
	}

	private void DKKBT() {
		tt.DKKBT();
		tb.DKKBT();
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		btnThem.setEnabled(true);
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);
		btnThoat.setEnabled(true);
	}

	private void DKKT() {
		DKKS();
		tt.DKKT();
	}

	private void DKKS() {
		tb.DKKT();
		tt.DKKS();
		btnLuu.setEnabled(true);
		btnHuy.setEnabled(true);
		btnThem.setEnabled(false);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnThoat.setEnabled(false);
	}

	private void Luu() {
		if (check()) {
			int row = tb.getTable().getSelectedRow();
			if (them) {
				Phong p = new Phong(tt.txtMaPhong.getText(), tt.txtTenPhong.getText(), Integer.parseInt(tt.txtSoLuongMay.getText()));
				PhongDAO ctrl = new PhongDAO();
				int r = ctrl.ThemPhong(p);
				PhongSetTableModel model = new PhongSetTableModel();
				tb.getTable().setModel(model);
				tb.getTable().changeSelection(row, 0, false, false);
				if (r == -1)
					JOptionPane.showMessageDialog(null, "Lưu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Lưu Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Phong p = new Phong(tt.txtMaPhong.getText(), tt.txtTenPhong.getText(), Integer.parseInt(tt.txtSoLuongMay.getText()));
				PhongDAO ctrl = new PhongDAO();
				int r = ctrl.SuaPhong(p);
				tb.getTable().setModel(new PhongSetTableModel());
				tb.getTable().changeSelection(row, 0, false, false);
				if (r == -1)
					JOptionPane.showMessageDialog(null, "Lưu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Lưu Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
			DKKBT();
			GanDL();
		}
	};

	private void Xoa() {
		int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ?", "Thông báo", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null);
		if (result == JOptionPane.YES_OPTION) {
			Phong p = new Phong(tt.txtMaPhong.getText(), tt.txtTenPhong.getText(),Integer.parseInt(tt.txtSoLuongMay.getText()));
			PhongDAO ctrl = new PhongDAO();
			int r = ctrl.XoaPhong(p);
			PhongSetTableModel model = new PhongSetTableModel();
			tb.getTable().setModel(model);
			tb.getTable().changeSelection(0, 0, false, false);
			if (r == -1)
				JOptionPane.showMessageDialog(null, "Xóa không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		}
		DKKBT();
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
		if (tt.txtMaPhong.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập mã phòng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (tt.txtTenPhong.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (tt.txtSoLuongMay.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng máy", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if(!isNumeric(tt.txtSoLuongMay.getText())) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
