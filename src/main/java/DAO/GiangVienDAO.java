package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.DangKy;
import bean.GiangVien;
import bean.Lop;
import bean.Phong;
import DAO.IGiangVienDAO;

public class GiangVienDAO implements IGiangVienDAO {
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
		String query = "call listGiangVien()";
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
		String query = "call getGiangVienTheoMa(?)";
		try {
			cstmt = conn.getConnection().prepareCall(query);
			cstmt.setString(1, id);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				gv.setMaGiangVien(rs.getString(1));
				gv.setTenGiangVien(rs.getString(2));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conn.closeConnection();
		return gv;
	}

	public boolean ThemGiangVien(GiangVien gv) throws SQLException {
		String query = "{call themGiangVien(?,?)}";
		cstmt = conn.getConnection().prepareCall(query);
		cstmt.setString(1, gv.getMaGiangVien());
		cstmt.setString(2, gv.getTenGiangVien());
		boolean r = cstmt.execute();
		conn.closeConnection();
		return r;
	}

	public boolean SuaGiangVien(GiangVien gv) throws SQLException {
		String query = "{call suaGiangVien(?,?)}";
		cstmt = conn.getConnection().prepareCall(query);
		cstmt.setString(1, gv.getMaGiangVien());
		cstmt.setString(2, gv.getTenGiangVien());
		boolean r = cstmt.execute();
		conn.closeConnection();
		return r;
	}

	public boolean XoaGiangVien(GiangVien gv) throws SQLException {
		String query = "{call xoaGiangVien(?)}";
		cstmt = conn.getConnection().prepareCall(query);
		cstmt.setString(1, gv.getMaGiangVien());
		boolean r = cstmt.execute();
		conn.closeConnection();
		return r;
	}

	public ArrayList<GiangVien> TimGiangVien(String searchText) {
		ArrayList<GiangVien> gvList = new ArrayList<GiangVien>();
		ResultSet rs;

		try {
			conn.getConnection();
			String query = "{call timGiangVien(?)}";
			cstmt = conn.getConnection().prepareCall(query);
			cstmt.setString(1, searchText);
			rs = cstmt.executeQuery();

			GiangVien gv;

			while (rs.next()) {
				gv = new GiangVien(rs.getString("magiangvien"), rs.getString("tengiangvien"));
				gvList.add(gv);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return gvList;
	}

	@Override
	public int CreateGiangVien(GiangVien gv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DeleteGiangVien(GiangVien gv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<GiangVien> ListGiangVien() {
		// TODO Auto-generated method stub
		return null;
	}
}
