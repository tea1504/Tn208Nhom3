package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


@SuppressWarnings("serial")
public class DangKyGUI extends JFrame implements ActionListener {
	private JPanel pTitle, pNhapLieu, pButton;
	private JLabel title = new JLabel();
	private JButton btnDangKy, btnNhapLai;
	private JLabel lblPhong, lblLop, lblNgay, lblBuoi;
	private JComboBox<String> cboPhong, cboLop, cboBuoi;
	private UtilDateModel model;
	private JDatePanelImpl datePane;
	private JDatePickerImpl datePicker;

	public DangKyGUI() {
		khoiTaoFrame();
	}

	private void khoiTaoFrame() {
		setupTitle();
		setupNhapLieu();
		setupButton();
		setBounds(300, 100, 1200, 800);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.333;
		getContentPane().add(pTitle, gbc);
		gbc.insets = new Insets(20, 100, 0, 100);
		gbc.gridy++;
		getContentPane().add(pNhapLieu, gbc);
		gbc.gridy++;
		getContentPane().add(pButton, gbc);
		setTitle("Đăng ký phòng máy");
		setVisible(true);
	}

	private void setupNhapLieu() {
		// lblNgay
		lblNgay = new JLabel("Chọn ngày :");
		lblNgay.setFont(new Font("Arial", Font.BOLD, 30));
		// lblNgay
		lblBuoi = new JLabel("Chọn buổi :");
		lblBuoi.setFont(new Font("Arial", Font.BOLD, 30));
		// lblPhong
		lblPhong = new JLabel("Chọn phòng :");
		lblPhong.setFont(new Font("Arial", Font.BOLD, 30));
		// lblLop
		lblLop = new JLabel("Chọn lớp :");
		lblLop.setFont(new Font("Arial", Font.BOLD, 30));
		// JDatePicker
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hôm nay");
		p.put("text.month", "Tháng");
		p.put("text.year", "Năm");
		datePane = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePane, new AbstractFormatter() {
			@Override
			public String valueToString(Object value) throws ParseException {
				if (value == null)
					return "Nhập ngày";
				GregorianCalendar temp = (GregorianCalendar) value;
				int year = temp.getTime().getYear() + 1900;
				int month = temp.getTime().getMonth() + 1;
				int date = temp.getTime().getDate();
				return date + "/" + month + "/" + year;
			}

			@Override
			public Object stringToValue(String text) throws ParseException {
				System.out.println(text);
				return "123";
			}
		});
		datePicker.setPreferredSize(new Dimension(120, 40));
		datePicker.getComponent(0).setPreferredSize(new Dimension(100, 40));
		datePicker.getComponent(1).setPreferredSize(new Dimension(40, 40));
		JFormattedTextField textField = datePicker.getJFormattedTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 30));
		// cboBuoi
		cboBuoi = new JComboBox<String>();
		cboBuoi.addItem("Sáng");
		cboBuoi.addItem("Chiều");
		cboBuoi.addItem("Tối");
		cboBuoi.setFont(new Font("Arial", Font.PLAIN, 30));
		cboBuoi.addActionListener(this);
		// cboPhong
		cboPhong = new JComboBox<String>();
		cboPhong.setFont(new Font("Arial", Font.PLAIN, 30));
		cboPhong.addActionListener(this);
		// cboLop
		cboLop = new JComboBox<String>();
		cboLop.setFont(new Font("Arial", Font.PLAIN, 30));
		cboLop.addActionListener(this);
		// pNhapLieu
		pNhapLieu = new JPanel(new GridBagLayout());
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Chọn thông tin");
		titleBorder.setTitleJustification(TitledBorder.CENTER);
		titleBorder.setTitleFont(new Font("Arial", Font.BOLD, 30));
		pNhapLieu.setBorder(titleBorder);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 50, 10, 5);
		gbc.weightx = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		pNhapLieu.add(lblNgay, gbc);
		gbc.gridy++;
		pNhapLieu.add(lblBuoi, gbc);
		gbc.gridy++;
		pNhapLieu.add(lblPhong, gbc);
		gbc.gridy++;
		pNhapLieu.add(lblLop, gbc);
		gbc.weightx = 0.9;
		gbc.insets = new Insets(10, 0, 10, 50);
		gbc.gridx = 1;
		gbc.gridy = 0;
		pNhapLieu.add(datePicker, gbc);
		gbc.gridy++;
		pNhapLieu.add(cboBuoi, gbc);
		gbc.gridy++;
		pNhapLieu.add(cboPhong, gbc);
		gbc.gridy++;
		pNhapLieu.add(cboLop, gbc);
	}

	private void setupButton() {
		pButton = new JPanel(new GridBagLayout());
		btnDangKy = new JButton("Đăng ký");
		btnDangKy.setFont(new Font("Arial", Font.BOLD, 30));
		btnDangKy.setBackground(new Color(9, 132, 227));
		btnDangKy.setForeground(Color.WHITE);
		btnDangKy.setBorderPainted(false);
		btnDangKy.setFocusable(false);
		btnDangKy.addActionListener(this);
		ImageIcon icon = new ImageIcon(getClass().getResource("icon/dangky2.png"));
		btnDangKy.setIcon(icon);
		btnNhapLai = new JButton("Nhập lại");
		btnNhapLai.setFont(new Font("Arial", Font.BOLD, 30));
		btnNhapLai.setBackground(new Color(9, 132, 227));
		btnNhapLai.setForeground(Color.WHITE);
		btnNhapLai.setBorderPainted(false);
		btnNhapLai.setFocusable(false);
		btnNhapLai.addActionListener(this);
		icon = new ImageIcon(getClass().getResource("icon/reset.png"));
		btnNhapLai.setIcon(icon);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pButton.add(btnDangKy, gbc);
		gbc.gridx++;
		pButton.add(btnNhapLai, gbc);
	}

	private void setupTitle() {
		pTitle = new JPanel(new BorderLayout());
		title.setText("Đăng ký phòng máy");
		title.setHorizontalAlignment(JLabel.HORIZONTAL);
		title.setFont(new Font("Arial", Font.BOLD, 60));
		title.setForeground(Color.WHITE);
		pTitle.add(title, BorderLayout.CENTER);
		pTitle.setBackground(new Color(9, 132, 227));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDangKy) {
			GregorianCalendar day = new GregorianCalendar();
			GregorianCalendar day2 = new GregorianCalendar(datePicker.getModel().getYear(),
					datePicker.getModel().getMonth(), datePicker.getModel().getDay());
			if (day2.compareTo(day) == -1) {
				JOptionPane.showMessageDialog(null, "Bạn phải chọn ngày khác", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnNhapLai) {
			datePicker.getModel().setValue(null);
		} else if (e.getSource() == cboBuoi) {
			System.out.println("CboBuoi");
		}
	}
}
