package view.lop;

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
	JLabel lblMaLop = new JLabel("Mã lớp:");
	JLabel lblTenLop = new JLabel("Tên lớp:");
	JLabel lblGiaoVien = new JLabel("Giảng viên dạy:");
	JLabel lblSiSo = new JLabel("Sỉ số lớp:");
	JTextField txtMaLop = new JTextField(10);
	JTextField txtTenLop = new JTextField(10);
	JTextField txtSiSo = new JTextField(10);
	JComboBox<GiangVien> cboGiangVien = new JComboBox<GiangVien>();

	public ThongTin() {
		cboGiangVien.addActionListener(this);
		setCombobox();
		setup();
		setLayout(new GridBagLayout());
		setBorder(new TitledBorder(BorderFactory.createTitledBorder("Nhập liệu")));
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
		DKKBT();
	}
	
	public void setCombobox() {
		GiangVienDAO gv = new GiangVienDAO();
		ArrayList<GiangVien> list = gv.getGiangVien();
		for(GiangVien item: list) {
			cboGiangVien.addItem(item);
		}
	}
	public void setTxtMaLop(String text) {
		txtMaLop.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cboGiangVien) {
		}
	}
	public void DKKBT() {
		cboGiangVien.setEnabled(false);
		cboGiangVien.setEditable(false);
		txtMaLop.setEditable(false);
		txtTenLop.setEditable(false);
		txtSiSo.setEditable(false);
	}
	public void DKKT() {
		DKKS();
		txtMaLop.setEditable(true);
		txtMaLop.setText("");
		txtTenLop.setText("");
		txtSiSo.setText("");
		cboGiangVien.setSelectedIndex(0);
	}
	public void DKKS() {
		txtTenLop.setEditable(true);
		txtSiSo.setEditable(true);
		cboGiangVien.setEnabled(true);
	}
	public void setup() {
		 lblMaLop.setFont(new Font("Arial", Font.BOLD, 18));
		 lblGiaoVien.setFont(new Font("Arial", Font.BOLD, 18));
		 lblTenLop.setFont(new Font("Arial", Font.BOLD, 18));
		 lblSiSo.setFont(new Font("Arial", Font.BOLD, 18));
		 txtMaLop.setFont(new Font("Arial",0, 18));
		 txtSiSo.setFont(new Font("Arial",0, 18));
		 txtTenLop.setFont(new Font("Arial",0, 18));
		 cboGiangVien.setFont(new Font("Arial", 0, 18));
	}
}
