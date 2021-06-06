package view.taikhoangiangvien;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import DAO.GiangVienDAO;
import bean.GiangVien;



@SuppressWarnings("serial")
public class ThongTin extends JPanel implements ActionListener {
	JLabel lblMaGiangVien = new JLabel("Mã giảng viên:");
	JLabel lblTenGiangVien = new JLabel("Tên giảng viên:");
	JTextField txtMaGiangVien = new JTextField(10);
	JTextField txtTenGiangVien = new JTextField(10);	
	JLabel lblQuyenSD = new JLabel("Quyền sử dụng:");
	String quyensd[] = {"Admin","User"};
	JComboBox cboQuyenSD = new JComboBox(quyensd);

	public ThongTin() {
//		cboQuyenSD.addActionListener(this);
		setup();
		setLayout(new GridBagLayout());
		setBorder(new TitledBorder(BorderFactory.createTitledBorder("Nhập liệu")));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 10, 0);
		add(lblMaGiangVien, gbc);
		gbc.gridy++;
		add(lblTenGiangVien, gbc);
		gbc.gridy++;
		add(lblQuyenSD, gbc);
		gbc.gridx++;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 5, 0, 0);
		add(txtMaGiangVien, gbc);
		gbc.gridy++;
		add(txtTenGiangVien, gbc);
		gbc.gridy++;
		add(cboQuyenSD, gbc);
		DKKBT();
	}

	public void settxtMaGiangVien(String text) {
		txtMaGiangVien.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cboQuyenSD) {
			
		}
	}
	public void DKKBT() {
		cboQuyenSD.setEnabled(false);
		cboQuyenSD.setEditable(false);
		txtMaGiangVien.setEditable(false);
		txtTenGiangVien.setEditable(false);
	}
	public void DKKT() {
		DKKS();
		txtMaGiangVien.setEditable(true);
		txtTenGiangVien.setEditable(true);
		txtMaGiangVien.setText("");
		txtTenGiangVien.setText("");
		cboQuyenSD.setSelectedIndex(1);
		cboQuyenSD.setEditable(false);
	}
	public void DKKS() {
		txtTenGiangVien.setEditable(true);
		cboQuyenSD.setSelectedIndex(1);
		cboQuyenSD.setEditable(false);
	}
	public void setup() {
		 lblMaGiangVien.setFont(new Font("Arial", Font.BOLD, 18));
		 lblTenGiangVien.setFont(new Font("Arial", Font.BOLD, 18));
		 txtMaGiangVien.setFont(new Font("Arial",0, 18));
		 txtTenGiangVien.setFont(new Font("Arial",0, 18));
		 lblQuyenSD.setFont(new Font("Arial", Font.BOLD, 18));
		 cboQuyenSD.setFont(new Font("Arial", 0, 18));
	}
}
