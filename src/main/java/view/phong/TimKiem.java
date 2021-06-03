package view.phong;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class TimKiem extends JPanel {
	JLabel lblTimMaPhong = new JLabel("Tìm mã phòng:");
	JLabel lblTimTenPhong = new JLabel("Tìm tên phòng:");
	JTextField txtTimMaPhong = new JTextField(10);
	JTextField txtTimTenPhong = new JTextField(10);

	public TimKiem() {
		setup();
		setLayout(new GridBagLayout());
		setBorder(new TitledBorder(BorderFactory.createTitledBorder("Tiềm kiếm")));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 20, 10, 10);
		add(lblTimMaPhong, gbc);
		gbc.gridx++;
		add(txtTimMaPhong, gbc);
		gbc.gridx++;
		add(lblTimTenPhong, gbc);
		gbc.gridx++;
		add(txtTimTenPhong, gbc);
		DKKBT();
	}



	public void DKKBT() {
		txtTimMaPhong.setEditable(true);
		txtTimTenPhong.setEditable(true);
	}
	public void DKKT() {
		DKKS();
	}
	public void DKKS() {
		txtTimMaPhong.setEditable(false);
		txtTimTenPhong.setEditable(false);
	}
	public void setup() {
		lblTimMaPhong.setFont(new Font("Arial", Font.BOLD, 18));
		lblTimTenPhong.setFont(new Font("Arial", Font.BOLD, 18));
		txtTimMaPhong.setFont(new Font("Arial",0, 18));
		txtTimTenPhong.setFont(new Font("Arial",0, 18));
	}
}
