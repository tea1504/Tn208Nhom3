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

public class GiangVienDAOImpl implements IGiangVienDAO {
	DBConnection conn = new DBConnection();
	CallableStatement cstmt;

	/**
	 * 
	 * @return
	 */
	@Override
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

	@Override
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

	@Override
	public boolean ThemGiangVien(GiangVien gv) {
		String query = "{call themGiangVien(?,?)}";
		boolean r = false;
		try {
			cstmt = conn.getConnection().prepareCall(query);
			cstmt.setString(1, gv.getMaGiangVien());
			cstmt.setString(2, gv.getTenGiangVien());
			r = cstmt.execute();
			cstmt.close();
			conn.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean SuaGiangVien(GiangVien gv) {
		String query = "{call suaGiangVien(?,?)}";
		boolean r = false;
		try {
			cstmt = conn.getConnection().prepareCall(query);
			cstmt.setString(1, gv.getMaGiangVien());
			cstmt.setString(2, gv.getTenGiangVien());
			r = cstmt.execute();
			cstmt.close();
			conn.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean XoaGiangVien(GiangVien gv) {
		String query = "{call xoaGiangVien(?)}";
		boolean r = false;
		try {
			cstmt = conn.getConnection().prepareCall(query);
			cstmt.setString(1, gv.getMaGiangVien());
			r = cstmt.execute();
			cstmt.close();
			conn.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
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
}
