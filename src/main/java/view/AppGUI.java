package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import helpers.SharedData;
import view.lop.LopGUI;
import view.phong.PhongGUI;
import view.taikhoan.DoiMatKhauGUI;
import view.taikhoan.TaiKhoanGUI;

@SuppressWarnings("serial")
public class AppGUI extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu mnuQuanLy, mnuChucNang, mnuTienIch;
	private JMenuItem mnuLop, mnuGiangVien, mnuPhong, mnuTaiKhoan, mnuDangKy, mnuHuyDangKy, mnuDangXuat, mnuDoiMatKhau, mnuXemLich;
	public AppGUI() {
		khoiTaoFrame();
	}

	private void khoiTaoFrame() {
		khoiTaoMenuBar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(menuBar, BorderLayout.NORTH);
		ImageIcon bgicon = new ImageIcon(getClass().getResource("icon/bg.png"));
		JLabel bg = new JLabel(bgicon);
		add(bg, BorderLayout.CENTER);
		setBounds(300, 100, 1200, 800);
		ImageIcon icon = new ImageIcon(getClass().getResource("icon/icon.png"));
		setIconImage(icon.getImage());
		JLabel lblTaiKhoan = new JLabel("Mã giảng viên: "+ SharedData.CurentAccount.getMaGiangVien());// thêm lable hiện mã giảng viên đang đăng nhập
		lblTaiKhoan.setFont(new Font("Arial", Font.ITALIC, 20));
		add(lblTaiKhoan, BorderLayout.SOUTH);
		setTitle("Chương trình quản lí phòng máy thực hành");
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void khoiTaoMenuBar() {
		ImageIcon icon;
		mnuLop = new JMenuItem("Lớp");
		mnuLop.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/class.png"));
		mnuLop.setIcon(icon);
		mnuLop.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuLop.setMnemonic(KeyEvent.VK_L);
		
		mnuGiangVien = new JMenuItem("Giảng viên");
		mnuGiangVien.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/giangvien.png"));
		mnuGiangVien.setIcon(icon);
		mnuGiangVien.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuGiangVien.setMnemonic(KeyEvent.VK_G);
		
		mnuPhong = new JMenuItem("Phòng");
		mnuPhong.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/phong.png"));
		mnuPhong.setIcon(icon);
		mnuPhong.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuPhong.setMnemonic(KeyEvent.VK_P);
		
		mnuDangKy = new JMenuItem("Đăng ký");
		mnuDangKy.setMnemonic(KeyEvent.VK_N);
		mnuDangKy.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/dangky.png"));
		mnuDangKy.setIcon(icon);
		mnuDangKy.setFont(new Font("Arial", Font.ITALIC, 20));
		
		mnuHuyDangKy = new JMenuItem("Hủy đăng ký");
		mnuHuyDangKy.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/huy.png"));
		mnuHuyDangKy.setIcon(icon);
		mnuHuyDangKy.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuHuyDangKy.setMnemonic(KeyEvent.VK_H);
		
		mnuDangXuat = new JMenuItem("Đăng xuất");
		mnuDangXuat.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/logout.png"));
		mnuDangXuat.setIcon(icon);
		mnuDangXuat.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuDangXuat.setMnemonic(KeyEvent.VK_X);
		
		mnuDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		mnuDoiMatKhau.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/pass.png"));
		mnuDoiMatKhau.setIcon(icon);
		mnuDoiMatKhau.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuDoiMatKhau.setMnemonic(KeyEvent.VK_M);
		
		mnuTaiKhoan = new JMenuItem("Tài khoản");
		mnuTaiKhoan.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/taikhoan.png"));
		mnuTaiKhoan.setIcon(icon);
		mnuTaiKhoan.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuTaiKhoan.setMnemonic(KeyEvent.VK_T);
		
		mnuXemLich = new JMenuItem("Xem lịch");
		mnuXemLich.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/xem.png"));
		mnuXemLich.setIcon(icon);
		mnuXemLich.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuXemLich.setMnemonic(KeyEvent.VK_X);
		
		mnuQuanLy = new JMenu("Quản lý");
		mnuChucNang = new JMenu("Chức năng");
		mnuTienIch = new JMenu("Tài khoản");
		
		mnuQuanLy.add(mnuPhong);
		mnuQuanLy.add(mnuLop);
		mnuQuanLy.add(mnuGiangVien);
		mnuQuanLy.add(mnuTaiKhoan);
		mnuQuanLy.setFont(new Font("Arial", Font.BOLD, 30));
		mnuQuanLy.setForeground(Color.WHITE);
		mnuQuanLy.setMnemonic(KeyEvent.VK_Q);
		
		mnuChucNang.add(mnuDangKy);
		mnuChucNang.add(mnuHuyDangKy);
		mnuChucNang.add(mnuXemLich);
		mnuChucNang.setFont(new Font("Arial", Font.BOLD, 30));
		mnuChucNang.setForeground(Color.WHITE);
		mnuChucNang.setMnemonic(KeyEvent.VK_C);
		
		mnuTienIch.add(mnuDangXuat);
		mnuTienIch.add(mnuDoiMatKhau);
		mnuTienIch.setFont(new Font("Arial", Font.BOLD, 30));
		mnuTienIch.setForeground(Color.WHITE);
		mnuTienIch.setMnemonic(KeyEvent.VK_T);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(30, 55, 153));
		
		menuBar.add(mnuQuanLy);
		menuBar.add(mnuChucNang);
		menuBar.add(mnuTienIch);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mnuPhong) {
			new PhongGUI();
		}
		else if(e.getSource() == mnuLop) {
			new LopGUI();
		}
		else if(e.getSource() == mnuGiangVien) {
			new TaiKhoanGiangVienGUI();
		}
		else if(e.getSource() == mnuTaiKhoan) {
			new TaiKhoanGUI();
		}
		else if(e.getSource() == mnuDangKy) {
			new DangKyGUI();
		}
		else if(e.getSource() == mnuHuyDangKy) {
			new HuyDangKyGUI();
		}
		else if(e.getSource() == mnuXemLich) {
			new QLPM_XemLich();
		}
		else if(e.getSource() == mnuDoiMatKhau) {
			new DoiMatKhauGUI();
		}
		else if(e.getSource() == mnuDangXuat) {
			SharedData.CurentAccount = null;
			this.dispose();
			new QLPM_DangNhap("Đăng nhập");
		}
		
	}

}
