package view.taikhoan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAOImpl;
import bean.TaiKhoan;
import helpers.DataValidator;
import helpers.PassWordHelper;
import helpers.SharedData;

public class CapLaiMatKhauGUI extends JFrame {
	private JPanel panel;
	private JPasswordField pwfMatKhau_new, pwfMatKhau_nhaplai;
	private JButton btnLuu, btnThoat;

	private JLabel lblTitle, lblMK_new, lblMK_nhaplai, lblImg, lblAuthor;
	private GridBagConstraints gbc;

	private String maGiangVien;

	public CapLaiMatKhauGUI(String maGV) {
		// Khởi tạo các thuộc tính

		super();
		setSize(550, 255);
		setLocationRelativeTo(null); // center
		setVisible(true);
		setTitle("Cấp lại mật khẩu cho giảng viên "+ maGV);

		lblTitle = new JLabel("Cấp lại mật khẩu mật khẩu");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		Border margin = new EmptyBorder(10, 10, 10, 10);
		lblTitle.setBorder(margin);

		maGiangVien = maGV;// truyền giá trị

		panel = new JPanel();

		lblMK_new = new JLabel("Mật khẩu mới: ");
		lblMK_nhaplai = new JLabel("Xác nhận lại: ");
		lblAuthor = new JLabel("@TN208 - Nhóm 3");

		pwfMatKhau_new = new JPasswordField();
		pwfMatKhau_nhaplai = new JPasswordField();

		ImageIcon img = new ImageIcon(getClass().getResource("../icon/save.png"));
		btnLuu = new JButton("Lưu thay đổi",
				new ImageIcon(img.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)));
		img = new ImageIcon(getClass().getResource("../icon/icon-exit.png"));
		btnThoat = new JButton("Thoát",
				new ImageIcon(img.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)));
		img = new ImageIcon(this.getClass().getResource("../icon/password-window.png"));
		lblImg = new JLabel(new ImageIcon(img.getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));

		// Thêm các thành phần chính vào frame
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(lblTitle, BorderLayout.NORTH);
		getContentPane().add(lblAuthor, BorderLayout.SOUTH);

		panel.setLayout(new GridBagLayout());
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		panel.setBorder(lineBorder);

		// add panel's components
		GridBagLayout();
		pwfMatKhau_new.requestFocus();

		// register event handlers
		btnLuu.addActionListener(new ButtonHandler()); // inner class for button event handling

		btnThoat.addActionListener(new ActionListener() {
			// anonymous inner class
			@Override
			public void actionPerformed(ActionEvent e) {
				CapLaiMatKhauGUI.this.dispose();

			}
		});
	}

	// method to add panel's components
	private void GridBagLayout() {
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		pAddComponent(lblImg, 0, 0, 1, 7);

		gbc.anchor = GridBagConstraints.CENTER;

		pAddComponent(lblMK_new, 0, 1, 1, 1);
		pAddComponent(lblMK_nhaplai, 1, 1, 1, 1);

		pAddComponent(pwfMatKhau_new, 0, 2, 3, 1);
		pAddComponent(pwfMatKhau_nhaplai, 1, 2, 3, 1);

		gbc.weightx = 0.5;
		JLabel temp = new JLabel();
		pAddComponent(temp, 6, 2, 1, 1);

		gbc.weightx = 0;
		gbc.weighty = 0.5;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		pAddComponent(btnLuu, 6, 3, 1, 1);
		pAddComponent(btnThoat, 6, 4, 1, 1);
	}

	// method to set constraints on
	private void pAddComponent(Component component, int row, int column, int width, int height) {
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		panel.add(component, gbc);
	}

	// Sự kiện cho btnLuu
	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuilder str = new StringBuilder();

			// Kiểm tra tính hợp lệ của dữ liệu đã nhập
			// Kiểm tra rỗng
			DataValidator.validateEmptyPassWordField(pwfMatKhau_nhaplai, str, "Vui lòng xác nhận lại mật khẩu mới!");
			DataValidator.validateEmptyPassWordField(pwfMatKhau_new, str, "Vui lòng nhập mật khẩu mới!");
			// Kiểm tra độ dài
			DataValidator.validatePassWordFieldLength(pwfMatKhau_new, 50, str, "Độ dài mật khẩu phải dưới 50 ký tự!");

			// Nếu nhập chưa đủ thì hiện thông báo lỗi và return
			if (str.length() > 0) {
				JOptionPane.showMessageDialog(null, str, "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Nếu đã nhập đủ
			String mk_moi = PassWordHelper.getPassWordField(pwfMatKhau_new);
			String mk_nhaplai = PassWordHelper.getPassWordField(pwfMatKhau_nhaplai);

			// Kiểm tra nhập lại mật khẩu mới
			if (!mk_moi.equals(mk_nhaplai)) {
				JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không chính xác!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			TaiKhoanDAOImpl tkDAO = new TaiKhoanDAOImpl();
			TaiKhoan tk = new TaiKhoan();
			try {

				// Nếu đổi mật khẩu thành công
				if (tkDAO.changePassWord(maGiangVien, mk_moi) > 0) {
					JOptionPane.showMessageDialog(null, "Cấp lại mật khẩu thành công!", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);
					CapLaiMatKhauGUI.this.dispose();
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Cấp lại mật khẩu không thành công!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

}
