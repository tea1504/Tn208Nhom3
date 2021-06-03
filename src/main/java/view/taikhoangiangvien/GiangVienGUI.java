package view.taikhoangiangvien;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GiangVienGUI extends JFrame {
	private ThongTin thongtin;
	private Table table;
	private dieukhien dk;
	private JLabel title = new JLabel("QUẢN LÝ TÀI KHOẢN GIẢNG VIÊN");

	public GiangVienGUI() {
		thongtin = new ThongTin();
		table = new Table(thongtin);
		dk = new dieukhien(thongtin, table, this);
		title.setFont(new Font("Arial", Font.BOLD, 50));
		setSize(1000, 700);
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20, 0, 20, 0);
		getContentPane().add(title, gbc);
		
		gbc.insets = new Insets(0, 30, 0, 30 );
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.weightx = 1;
		getContentPane().add(thongtin, gbc);
				
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.gridwidth = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 20;
		gbc.insets = new Insets(20, 30, 0, 30 );
		getContentPane().add(table, gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 0, 5, 0);
		getContentPane().add(dk, gbc);
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("a.png"));
		setIconImage(icon.getImage());
		setTitle("Quản lý giảng viên");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

}
