package DAO;

import javax.swing.JTable;

import com.mysql.cj.protocol.Resultset;

import bean.TaiKhoan;

public interface ITaiKhoanDAO {

	/**
	 * Kiểm tra đăng nhập
	 * 
	 * @param _username {@link String}
	 * @param _password {@link String}
	 * @return Tài khoản đang đăng nhập {@link TaiKhoan}
	 */
	TaiKhoan checkLogin(String _username, String _password);

	/**
	 * Thay đổi mật khẩu
	 * 
	 * @param _username    {@link String}
	 * @param _newpassword {@link String}
	 * @return Số dòng dữ liệu được thay đổi {@link Integer}
	 */
	int changePassWord(String _username, String _newpassword);

	/**
	 * Lấy model tài khoản cho {@link JTable}
	 * 
	 * @return tập kết quả {@link Resultset}
	 */
	Resultset taiKhoanGetTableModel();

	/**
	 * Lấy model tài khoản theo mã giảng viên
	 * 
	 * @param maGiangVien {@link String}
	 * @return Tập kết quả {@link Resultset}
	 */
	Resultset taiKhoanGetTableModel(String maGiangVien);

	/**
	 * Thay đổi quyền sử dụng
	 * 
	 * @param _username {@link String}
	 * @param _grant    {@link Integer}
	 * @return Số dòng dữ liệu bị thay đổi {@link Integer}
	 */
	int changeGrant(String _username, int _grant);

}
