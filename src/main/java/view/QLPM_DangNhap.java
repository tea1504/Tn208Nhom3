package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAOImpl;
import bean.TaiKhoan;
import helpers.DataValidator;
import helpers.SharedData;

public class QLPM_DangNhap extends JFrame{
	private JPanel panel;
	private JTextField txtMSGV;
	private JPasswordField pwfMatKhau;
	private JButton btnDangNhap, btnThoat;

	private JLabel lblTitle, lblMSGV, lblMatKhau, lblImg, lblAuthor;
	private GridBagConstraints gbc;
	
	public QLPM_DangNhap(String title) throws HeadlessException
	{
		//Khởi tạo các thuộc tính
		
		super(title);
		setSize(520,255); 
		setLocationRelativeTo(null); //center
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		lblTitle = new JLabel("QUẢN LÝ PHÒNG MÁY");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		Border margin = new EmptyBorder(10,10,10,10);
		lblTitle.setBorder(margin);
		
		panel = new JPanel();
		lblMSGV = new JLabel("Mã giảng viên: ");
		lblMatKhau = new JLabel("Mật khẩu: ");
		lblAuthor = new JLabel("@TN208 - Nhóm 3");
		txtMSGV = new JTextField("GV020");
		pwfMatKhau = new JPasswordField("12345");

		ImageIcon img = new ImageIcon(getClass().getResource("icon/icon-login.png"));
		btnDangNhap = new JButton("Đăng nhập", new ImageIcon(img.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)));
		img = new ImageIcon(getClass().getResource("icon/icon-exit.png"));
		btnThoat = new JButton("Thoát", new ImageIcon(img.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)));
		img= new ImageIcon(this.getClass().getResource("icon/login.png"));
		lblImg = new JLabel(new ImageIcon(img.getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
	
		
		//Thêm các thành phần chính vào frame
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(lblTitle, BorderLayout.NORTH);
		getContentPane().add(lblAuthor, BorderLayout.SOUTH);
		
		panel.setLayout(new GridBagLayout());
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		panel.setBorder(lineBorder);
	
		//add panel's components 
		GridBagLayout();
		txtMSGV.requestFocus();
		
		// register event handlers
		btnDangNhap.addActionListener(new ButtonHandler()); //inner class for button event handling
		btnThoat.addActionListener(new ActionListener() {
			// anonymous inner class
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	}
	
	
	//method to add panel's components
	private void GridBagLayout()
	{
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		pAddComponent(lblImg, 0, 0, 1, 7);
		
		gbc.anchor=GridBagConstraints.CENTER;
		pAddComponent(lblMSGV, 0, 1, 1, 1);
		pAddComponent(lblMatKhau, 1, 1, 1, 1);
		
		
		pAddComponent(txtMSGV, 0, 2, 3, 1);
		pAddComponent(pwfMatKhau, 1, 2, 3, 1);
		
		gbc.weightx=0.5;
		JLabel temp = new JLabel();
		pAddComponent(temp, 6, 2, 1, 1);
		
		gbc.weightx=0;
		gbc.weighty=0.5;
		gbc.anchor=GridBagConstraints.LAST_LINE_END;
		pAddComponent(btnDangNhap, 6, 3, 1, 1);
		pAddComponent(btnThoat, 6, 4, 1, 1);
	}
	
	// method to set constraints on
		private void pAddComponent(Component component, int row, int column, int width, int height) 
		{
			gbc.gridx = column; 
			gbc.gridy = row; 
			gbc.gridwidth = width; 
			gbc.gridheight = height; 
			panel.add(component, gbc);
		} 
		
		
	//Sự kiện cho btnDangNhap
		private class ButtonHandler implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder str = new StringBuilder();
				
				//Kiểm tra dữ liệu đã nhập đủ chưa
				DataValidator.validateEmptyTextField(pwfMatKhau, str, "Mật khẩu không được trống!");
				DataValidator.validateEmptyTextField(txtMSGV, str, "Tên đăng nhập không được trống!");
				
				//Nếu nhập chưa đủ thì hiện thông báo lỗi và return
				if(str.length()>0)
				{
					JOptionPane.showMessageDialog(null, str, "Lỗi",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//Nếu đã nhập đủ
				
				TaiKhoanDAOImpl tkDAO = new TaiKhoanDAOImpl();
				TaiKhoan tk = new TaiKhoan();
				
				try
				{
					tk = tkDAO.checkLogin(txtMSGV.getText(), new String(pwfMatKhau.getPassword()));
					
					//Không tìm thấy tài khoản
					if(tk == null)
						JOptionPane.showMessageDialog(null, "Sai MSGV hoặc mật khẩu!", "Đăng nhập không thành công",JOptionPane.ERROR_MESSAGE);
					else
					{
						//Đăng nhập thành công
						SharedData.CurentAccount = tk; //Lưu thông tin tài khoản hiện tại
						new AppGUI();
						QLPM_DangNhap.this.dispose();
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi",JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	
}
