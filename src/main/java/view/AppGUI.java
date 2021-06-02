package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.phong.PhongGUI;

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
		setTitle("Chương trình quản lí phòng máy thực hành");
		setVisible(true);
	}

	private void khoiTaoMenuBar() {
		ImageIcon icon;
		mnuLop = new JMenuItem("Lớp");
		mnuLop.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/class.png"));
		mnuLop.setIcon(icon);
		mnuLop.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuGiangVien = new JMenuItem("Giảng viên");
		mnuGiangVien.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/giangvien.png"));
		mnuGiangVien.setIcon(icon);
		mnuGiangVien.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuPhong = new JMenuItem("Phòng");
		mnuPhong.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/phong.png"));
		mnuPhong.setIcon(icon);
		mnuPhong.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuDangKy = new JMenuItem("Đăng ký");
		mnuDangKy.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/dangky.png"));
		mnuDangKy.setIcon(icon);
		mnuDangKy.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuHuyDangKy = new JMenuItem("Hủy đăng ký");
		mnuHuyDangKy.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/huy.png"));
		mnuHuyDangKy.setIcon(icon);
		mnuHuyDangKy.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuDangXuat = new JMenuItem("Đăng xuất");
		mnuDangXuat.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/logout.png"));
		mnuDangXuat.setIcon(icon);
		mnuDangXuat.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		mnuDoiMatKhau.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/pass.png"));
		mnuDoiMatKhau.setIcon(icon);
		mnuDoiMatKhau.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuTaiKhoan = new JMenuItem("Tài khoản");
		mnuTaiKhoan.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/taikhoan.png"));
		mnuTaiKhoan.setIcon(icon);
		mnuTaiKhoan.setFont(new Font("Arial", Font.ITALIC, 20));
		mnuXemLich = new JMenuItem("Xem lịch");
		mnuXemLich.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/xem.png"));
		mnuXemLich.setIcon(icon);
		mnuXemLich.setFont(new Font("Arial", Font.ITALIC, 20));
		
		mnuQuanLy = new JMenu("Quản lý");
		mnuChucNang = new JMenu("Chức năng");
		mnuTienIch = new JMenu("Tài khoản");
		
		mnuQuanLy.add(mnuPhong);
		mnuQuanLy.add(mnuLop);
		mnuQuanLy.add(mnuGiangVien);
		mnuQuanLy.add(mnuTaiKhoan);
		mnuQuanLy.setFont(new Font("Arial", Font.BOLD, 30));
		mnuQuanLy.setForeground(Color.WHITE);
		
		mnuChucNang.add(mnuDangKy);
		mnuChucNang.add(mnuHuyDangKy);
		mnuChucNang.add(mnuXemLich);
		mnuChucNang.setFont(new Font("Arial", Font.BOLD, 30));
		mnuChucNang.setForeground(Color.WHITE);
		
		mnuTienIch.add(mnuDangXuat);
		mnuTienIch.add(mnuDoiMatKhau);
		mnuTienIch.setFont(new Font("Arial", Font.BOLD, 30));
		mnuTienIch.setForeground(Color.WHITE);
		
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
			System.out.println("Lop");
		}
		else if(e.getSource() == mnuGiangVien) {
			System.out.println("GiangVien");
		}
		else if(e.getSource() == mnuTaiKhoan) {
			System.out.println("TaiKhoan");
		}
		else if(e.getSource() == mnuDangKy) {
			System.out.println("DangKy");
		}
		else if(e.getSource() == mnuHuyDangKy) {
			System.out.println("HuyDangKy");
		}
		else if(e.getSource() == mnuXemLich) {
			new XemLichGUI();
		}
		else if(e.getSource() == mnuDangXuat) {
			System.out.println("DangXuat");
		}
		else if(e.getSource() == mnuDoiMatKhau) {
			System.out.println("DoiMatKhau");
		}
	}

}
