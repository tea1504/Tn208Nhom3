package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class HuyDangKyGUI extends JFrame implements ActionListener {
	private JPanel pTitle, pTable, pButton;
	private JLabel title;
	private JTable table;
	
	public HuyDangKyGUI() {
		khoiTaoFrame();
	}
	
	private void khoiTaoFrame() {
		setupTitle();
		setupTable();
		setupButton();
		setBounds(300, 100, 1200, 800);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		getContentPane().add(pTitle, gbc);
		gbc.weighty = 0.8;
		gbc.gridy ++;
		getContentPane().add(pTable, gbc);
		gbc.weighty = 0.1;
		gbc.gridy ++;
		getContentPane().add(pButton, gbc);
		setVisible(true);
	}
	private void setupTitle() {
		title = new JLabel("Hủy đăng ký phòng máy");
		pTitle = new JPanel();
		title.setHorizontalAlignment(JLabel.HORIZONTAL);
		title.setFont(new Font("Arial", Font.BOLD, 60));
		title.setForeground(Color.WHITE);
		pTitle.add(title, BorderLayout.CENTER);
		pTitle.setBackground(new Color(9, 132, 227));
	}
	private void setupTable() {
		pTable = new JPanel();
	}
	private void setupButton() {
		pButton = new JPanel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
