package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.model.Appointment;

@SuppressWarnings("serial")
public class XemLichGUI extends JFrame {
	Calendar calendar = new Calendar();
//	JLabel title = new JLabel("Xem lịch");
	public XemLichGUI() {
		khoiTaoFrame();
	}
	private void khoiTaoFrame() {
		DateTime d = new DateTime(2021, 6, 1);
		Appointment app = new Appointment();
		app.setStartTime(d);
		app.setEndTime(d);
		app.setHeaderText("Lớp 12345\tGV 12345 Buoi sang");
		calendar.getSchedule().getItems().add(app);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		getContentPane().add(calendar, gbc);
		setBounds(300, 100, 1200, 800);
		ImageIcon icon = new ImageIcon(getClass().getResource("icon/icon.png"));
		setIconImage(icon.getImage());
		setTitle("Xem lịch");
		setVisible(true);
	}
}
