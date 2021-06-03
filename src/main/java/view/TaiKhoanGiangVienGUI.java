package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.IGiangVienDAO;
import DAO.ITaiKhoanDAO;
import bean.GiangVien;
import bean.TaiKhoan;
/**
 * Giao diện quản lý Tài khoản + Giảng viên
 * @author Trịnh Thanh Thảo
 *
 */
public class TaiKhoanGiangVienGUI extends JFrame{
	private JLabel title = new JLabel("QUẢN LÝ TÀI KHOẢN GIẢNG VIÊN");
	JLabel lblMaGiangVien = new JLabel("Mã giảng viên:");
	JLabel lblTenGiangVien = new JLabel("Tên giảng viên:");
	JLabel lblQuyenSD = new JLabel("Quyền sử dụng:");
	JTextField txtMaGiangVien = new JTextField(10);
	JTextField txtTenGiangVien = new JTextField(50);
	JComboBox cboQuyenSD = new JComboBox();
	
	public TaiKhoanGiangVienGUI() {
		khoiTaoFrame();
	}
	
	private void khoiTaoFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		setSize(1200, 700);
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 0, 20, 0);
		getContentPane().add(title, gbc);
		
		cboQuyenSD.addActionListener(cboQuyenSD);
//		setCombobox();
//		setup();
//				
		setTitle("Quản lý tài khoản giảng viên");
		setVisible(true);
	}
	
	
//	public void setCombobox() {
//		cboQuyenSD.addItem(new ComboItem(0,"Admin"));
//		cboQuyenSD.addItem(new ComboItem(1,"User"));
//	}
}
