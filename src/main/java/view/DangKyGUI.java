package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.mindfusion.scheduling.Calendar;

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
		gbc.insets = new Insets(0, 100, 0, 100);
		gbc.gridy++;
		getContentPane().add(pNhapLieu, gbc);
		gbc.gridy++;
		getContentPane().add(pButton, gbc);
		setTitle("Đăng ký phòng máy");
		setVisible(true);
	}

	private void setupNhapLieu() {
		// JDatePicker
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hôm nay");
		p.put("text.month", "Tháng");
		p.put("text.year", "Năm");
		datePane = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePane, new AbstractFormatter() {
			private String datePattern = "yyyy-MM-dd";
		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
			@Override
			public String valueToString(Object value) throws ParseException {
				if(value == null)
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
		JFormattedTextField textField = datePicker.getJFormattedTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 20));
		//cboBuoi
		cboBuoi = new JComboBox<String>();
		cboBuoi.addItem("Sáng");
		cboBuoi.addItem("Chiều");
		cboBuoi.addItem("Tối");
		//pNhapLieu
		pNhapLieu = new JPanel(new GridBagLayout());
		TitledBorder titleBorder=BorderFactory.createTitledBorder("Nhập liệu");
		titleBorder.setTitleJustification(TitledBorder.CENTER);
		titleBorder.setTitleFont(new Font("Arial", Font.BOLD, 20));
		pNhapLieu.setBorder(titleBorder);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(5, 50, 5, 50);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pNhapLieu.add(datePicker, gbc);
		gbc.gridy++;
		pNhapLieu.add(cboBuoi, gbc);
	}

	private void setupButton() {
		pButton = new JPanel(new GridBagLayout());
		btnDangKy = new JButton("Đăng ký");
		btnNhapLai = new JButton("Nhập lại");
		GridBagConstraints gbc = new GridBagConstraints();
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
		pTitle.add(title, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
