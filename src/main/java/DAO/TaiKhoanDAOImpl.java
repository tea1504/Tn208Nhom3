package DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import bean.DBConnection;
import bean.TaiKhoan;
import helpers.PassWordHelper;

public class TaiKhoanDAOImpl  implements ITaiKhoanDAO{

	public DBConnection dbConn = new DBConnection();
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
		
		CallableStatement cstm;
		TaiKhoan tk = null;
		String maHoaPassWord="";
		
		try {
			 maHoaPassWord = PassWordHelper.converMD5(_password);
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

	
	@Override
	public int changePassWord(String _username, String _newpassword) 
	{	
		
		CallableStatement cstm;
		int kq = 0;
		String maHoaPassWord="";
		
		try {
			 maHoaPassWord = PassWordHelper.converMD5(_newpassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			String sql = "{call changePassWord(?, ?)}";
			cstm = dbConn.getConnection().prepareCall(sql);
			
			cstm.setString(1, _username); 
			cstm.setString(2, maHoaPassWord); 
	
			//Thực thi thủ tục, Lưu kết quả trả về trong kq: số dòng dữ liệu bị tác động
			kq = cstm.executeUpdate();
				
			cstm.close();
			dbConn.closeConnection();
			
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}
		
		return kq; // trả về số dòng được update
	}

	@Override
	public Resultset taiKhoanGetTableModel() {
		try
		{
			//Lấy dữ liệu để hiển thị lên bảng
			CallableStatement cstm;
			ResultSet rs;
			String query = "call getAccountModel";
			cstm = dbConn.getConnection().prepareCall(query);
			
			rs = cstm.executeQuery();
			return (Resultset)rs;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	
// Phương thức có tham số để tìm kiếm theo mã giảng viên
	@Override
	public Resultset taiKhoanGetTableModel(String _username) {
		try
		{
			//Lấy dữ liệu để hiển thị lên bảng
			CallableStatement cstm;
			ResultSet rs;
			String query = "call getAccountModelByID(?)";
			cstm = dbConn.getConnection().prepareCall(query);
			
			cstm.setString(1, _username); 
			
			rs = cstm.executeQuery();
			return (Resultset)rs;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public int changeGrant(String _username, int _grant) {
		
		CallableStatement cstm;
		int kq = 0;
	
		try {
			String sql = "{call changeGrant(?, ?)}";
			cstm = dbConn.getConnection().prepareCall(sql);
			
			cstm.setString(1, _username); 
			cstm.setInt(2, _grant); 
	
			//Thực thi thủ tục, Lưu kết quả trả về trong kq: số dòng dữ liệu bị tác động
			kq = cstm.executeUpdate();
				
			cstm.close();
			dbConn.closeConnection();
			
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}
		
		return kq; // trả về số dòng được update
	}

}
