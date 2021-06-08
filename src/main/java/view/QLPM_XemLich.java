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
import com.mindfusion.drawing.GradientBrush;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.model.Appointment;

import DAO.DangKyDAOImpl;
import bean.DangKy;
import bean.TaiKhoan;
import helpers.SharedData;
/**
 * Class tạo giao diện chức năng xem lịch <br/>
 * Chức năng này dùng để xem lịch đăng ký phòng của giảng viên đang đăng nhập vào hệ thông
 * @author Trần Văn Hòa
 *
 */
@SuppressWarnings("serial")
public class QLPM_XemLich extends JFrame {
	Calendar calendar = new Calendar();
	JLabel title = new JLabel("Xem lịch phòng máy");

	public QLPM_XemLich() {
		khoiTaoFrame();
	}

	/**
	 * Phương thức khởi tạo Frame <br/>
	 * Frame sử dụng LayoutManager là <strong>GridBagLayout</strong> để quản lý layout <br/>
	 * Gọi phương thức <strong>setupCaculater</strong>
	 * @author Trần Văn Hòa
	 */
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
		setSize(1200, 800);
		ImageIcon icon = new ImageIcon(getClass().getResource("icon/icon.png"));
		setIconImage(icon.getImage());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setTitle("Xem lịch phòng máy");
		setVisible(true);
	}

	/**
	 * Hàm này dùng để hiển thị thông tin lịch dạy của một giảng viên <br>
	 * Cách lấy danh sách lịch dạy của một giảng viên
	 * <ol>
	 * <li>Lấy mã giảng viên đang đăng nhập - trong class <b>ShareData</b></li>
	 * <li>Lấy danh sách đăng ký theo giảng viên - trong class
	 * <b>DangKyDAOImpl</b></li>
	 * </ol>
	 * 
	 * @author Trần Văn Hòa
	 */
	private void setupCaculater() {
		DangKyDAOImpl dangKyDAO = new DangKyDAOImpl();
		// Bước 1
		TaiKhoan user = SharedData.CurentAccount;
		// Bước 2
		ArrayList<DangKy> listDangKy = dangKyDAO.ListDangKyTheoGV(user.getMaGiangVien());
		for (DangKy dangKy : listDangKy) {
			int year = dangKy.getNgayDangKy().get(java.util.Calendar.YEAR);
			int month = dangKy.getNgayDangKy().get(java.util.Calendar.MONTH) + 1; // Tháng đầu tiên bắt đầu từ 0
			int date = dangKy.getNgayDangKy().get(java.util.Calendar.DATE);
			String buoi = new String();
			if(dangKy.getBuoiDangKy() == 0)
				buoi = "Buổi sáng";
			else if (dangKy.getBuoiDangKy() == 1)
				buoi="Buổi chiều";
			else
				buoi="Buổi tối";
			DateTime d = new DateTime(year, month, date);
			Appointment app = new Appointment();
			app.setStartTime(d);
			app.setEndTime(d);
			app.setHeaderText("Lớp : " + dangKy.getLop().getTenLop() + " | " + dangKy.getPhong().getTenPhong() + " | " + buoi);
			app.setAllowMove(false);
			app.setAllowChangeEnd(false);
			app.setAllowChangeStart(false);
			calendar.getSchedule().getItems().add(app);
		}
		calendar.getItemSettings().setSize(30);
		calendar.getItemSettings().getStyle()
				.setBrush(new GradientBrush(new Color(161, 255, 254), new Color(250, 255, 209), 1));

	}
}
