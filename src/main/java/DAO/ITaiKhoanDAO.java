package DAO;

import java.util.ArrayList;

import bean.DangKy;
import bean.TaiKhoan;

public interface ITaiKhoanDAO {
	int CreateTaiKhoan(TaiKhoan taikhoan);
	int DeleteTaiKhoan(TaiKhoan taikhoan);
	ArrayList<TaiKhoan> ListTaiKhoan();
}

