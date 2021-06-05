package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.DangKy;
import bean.GiangVien;
import bean.Lop;
import DAO.IGiangVienDAO;


public class GiangVienDAO implements IDangKyDAO{
	DBConnection conn = new DBConnection();
	CallableStatement cstmt;
	/**
	 * 
	 * @return
	 */
	public ArrayList<GiangVien> getGiangVien() {
		ResultSet rs;
		ArrayList<GiangVien> list = new ArrayList<GiangVien>();
		conn.getConnection();
		String query = "select * from giangvien";
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				GiangVien temp = new GiangVien(rs.getString(1), rs.getString(2));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}

	public GiangVien getGiangVien(String id) {
		ResultSet rs;
		GiangVien gv = new GiangVien();
		conn.getConnection();
		String query = "select * from giangvien where magiangvien='" + id + "'";
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				gv.setMaGiangVien(rs.getString(1));
				gv.setTenGiangVien(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return gv;
	}
	
	public int ThemGiangVien(GiangVien gv){
		String query= "{call themGiangVien(?,?)}";
		try {
			cstmt =conn.getConnection().prepareCall(query);
			cstmt.setString(1, gv.getMaGiangVien());
			cstmt.setString(2, gv.getTenGiangVien());
			int r = cstmt.executeUpdate();
			conn.closeConnection();
			return r;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int SuaGiangVien(GiangVien gv){
		String query= "{call suaGiangVien(?,?)}";
		try {
			cstmt =conn.getConnection().prepareCall(query);
			cstmt.setString(1, gv.getMaGiangVien());
			cstmt.setString(2, gv.getTenGiangVien());
			int r = cstmt.executeUpdate();
			conn.closeConnection();
			return r;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
		
	}

	public int XoaGiangVien(GiangVien gv){
		String query= "{call xoaGiangVien(?)}";
		try {
			cstmt =conn.getConnection().prepareCall(query);
			cstmt.setString(1, gv.getMaGiangVien());
			int r = cstmt.executeUpdate();
			conn.closeConnection();
			return r;
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return 0;
	}

//	public int ThemGiangVien(GiangVien gv) {
//		String query = "insert into giangvien (magiangvien, tengiangvien) values ('" + gv.getMaGiangVien() + "', '"
//				+ gv.getTenGiangVien() + "')";
//		conn.getConnection();
//		int r = conn.update(query);
//		conn.closeConnection();
//		return r;
//	}
//
//	public int SuaGiangVien(GiangVien gv) {
//		String query = "update giangvien set tengiangvien='" + gv.getTenGiangVien()+"' where magiangvien='" + gv.getMaGiangVien() + "'";
//		conn.getConnection();
//		int r = conn.update(query);
//		conn.closeConnection();
//		return r;
//	}
//
//	public int XoaGiangVien(GiangVien gv) {
//		String query = "delete from giangvien where magiangvien='" + gv.getMaGiangVien() + "'";
//		conn.getConnection();
//		int r = conn.update(query);
//		query = "delete from taikhoan where magiangvien='" + gv.getMaGiangVien() + "'";
//		conn.getConnection();
//		r = conn.update(query);		
//		conn.closeConnection();
//		return r;
//	}
	
	
	@Override
	public int CreateDangKy(DangKy dangKy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DeleteDangKy(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DangKy> ListDangKy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DangKy> ListDangKyTheoGV(String maGiangVien) {
		// TODO Auto-generated method stub
		return null;
	}
}
