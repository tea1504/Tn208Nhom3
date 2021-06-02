package DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.TaiKhoan;
import helpers.MaHoaMatKhau;

public class TaiKhoanDAOImpl  implements ITaiKhoanDAO{

	@Override
	public int CreateTaiKhoan(TaiKhoan taikhoan) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DeleteTaiKhoan(TaiKhoan taikhoan) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TaiKhoan> ListTaiKhoan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaiKhoan checkLogin(String _username, String _password) {
		
		DBConnection dbConn = new DBConnection();
		CallableStatement cstm;
		TaiKhoan tk = null;
		String maHoaPassWord="";
		
		try {
			 maHoaPassWord = MaHoaMatKhau.converMD5(_password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			String sql = "{call getLoginAccount(?, ?)}";
			cstm = dbConn.getConnection().prepareCall(sql);
			
			cstm.setString(1, _username); 
			cstm.setString(2, maHoaPassWord); 
	
			//Lưu kết quả trả về trong rs
			ResultSet rs =  cstm.executeQuery();
			
			//Nếu tìm thấy tài khoản thì gán giá giá trị và trả về tk
			if(rs.next())
			{
				tk = new TaiKhoan();
				tk.setMaGiangVien(_username);
				tk.setQuyenSD(rs.getInt("quyensd"));
			}
			
			cstm.close();
			dbConn.closeConnection();
			
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}
		
		//Nếu không tìm thấy tài khoản thì trả về tk=null
		return tk;
		
	}

}
