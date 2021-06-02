package view.phong;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class ThongTin extends JPanel {
	JLabel lblMaPhong = new JLabel("Mã phòng:");
	JLabel lblTenPhong = new JLabel("Tên phòng:");
	JLabel lblSoLuongMay = new JLabel("Số lượng máy:");
	JTextField txtMaPhong = new JTextField(10);
	JTextField txtTenPhong = new JTextField(10);
	JTextField txtSoLuongMay = new JTextField(10);

	public ThongTin() {
		setup();
		setLayout(new GridBagLayout());
		setBorder(new TitledBorder(BorderFactory.createTitledBorder("Thông tin")));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 20, 10, 10);
		add(lblMaPhong, gbc);
		gbc.gridy++;
		add(lblTenPhong, gbc);
		gbc.gridy++;
		add(lblSoLuongMay, gbc);
		gbc.gridx++;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 10, 20);
		add(txtMaPhong, gbc);
		gbc.gridy++;
		add(txtTenPhong, gbc);
		gbc.gridy++;
		add(txtSoLuongMay, gbc);
		DKKBT();
	}

	public void setTxtMaPhong(String text) {
		txtMaPhong.setText(text);
	}
	
	public void DKKBT() {
		txtMaPhong.setEditable(false);
		txtTenPhong.setEditable(false);
		txtSoLuongMay.setEditable(false);
	}
	public void DKKT() {
		DKKS();
		txtMaPhong.setEditable(true);
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtSoLuongMay.setText("");
	}
	public void DKKS() {
		txtTenPhong.setEditable(true);
		txtSoLuongMay.setEditable(true);
	}
	public void setup() {
		 lblMaPhong.setFont(new Font("Arial", Font.BOLD, 18));
		 lblTenPhong.setFont(new Font("Arial", Font.BOLD, 18));
		 lblSoLuongMay.setFont(new Font("Arial", Font.BOLD, 18));
		 txtMaPhong.setFont(new Font("Arial",0, 18));
		 txtTenPhong.setFont(new Font("Arial",0, 18));
		 txtSoLuongMay.setFont(new Font("Arial",0, 18));
	}
		
}
