package DAO;

import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import bean.DangKy;
import bean.TaiKhoan;

public interface ITaiKhoanDAO {
	int CreateTaiKhoan(TaiKhoan taikhoan);
	int DeleteTaiKhoan(TaiKhoan taikhoan);
	ArrayList<TaiKhoan> ListTaiKhoan();
	
	TaiKhoan checkLogin(String _username, String _password);
	int changePassWord(String _username, String _newpassword);
	Resultset taiKhoanGetTableModel();
	Resultset taiKhoanGetTableModel(String maGiangVien);
	int changeGrant(String _username, int _grant);
	
	
}

