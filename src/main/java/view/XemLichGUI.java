package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.Cursor;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.scheduling.model.Style;

import DAO.DangKyDAOImpl;
import bean.DangKy;

@SuppressWarnings("serial")
public class XemLichGUI extends JFrame {
	Calendar calendar = new Calendar();
	JLabel title = new JLabel("Xem lịch phòng máy");

	public XemLichGUI() {
		khoiTaoFrame();
	}

	private void khoiTaoFrame() {
		setupCaculater();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		title.setFont(new Font("Arial", Font.BOLD, 60));
		title.setHorizontalAlignment(JLabel.HORIZONTAL);
		title.setForeground(new Color(30, 55, 153));
		getContentPane().add(title, gbc);
		gbc.gridy++;
		gbc.weighty = 0.9;
		getContentPane().add(calendar, gbc);
		setBounds(300, 100, 1200, 800);
		ImageIcon icon = new ImageIcon(getClass().getResource("icon/icon.png"));
		setIconImage(icon.getImage());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Xem lịch phòng máy");
		setVisible(true);
	}

	private void setupCaculater() {
		DangKyDAOImpl dangKyDAO = new DangKyDAOImpl();
		ArrayList<DangKy> listDangKy = dangKyDAO.ListDangKyTheoGV("GV020");
		for (DangKy dangKy : listDangKy) {
			int year = dangKy.getNgayDangKy().getYear() + 1900;
			int month = dangKy.getNgayDangKy().getMonth() + 1;
			int date = dangKy.getNgayDangKy().getDate();
			DateTime d = new DateTime(year, month, date);
			Appointment app = new Appointment();
			app.setStartTime(d);
			app.setEndTime(d);
			app.setHeaderText("Lop lap trinh huong doi tuong tc19201 phong 12 Buoi sang");
			app.setAllowMove(false);
			app.setAllowChangeEnd(false);
			app.setAllowChangeStart(false);
			Style style = new Style();
			style.setHeaderBorderBottomWidth(20);
			app.setStyle(style);
			calendar.getSchedule().getItems().add(app);
		}

	}
}
