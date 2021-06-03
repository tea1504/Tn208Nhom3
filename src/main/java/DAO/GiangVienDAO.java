package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.GiangVien;



public class GiangVienDAO {
	DBConnection conn = new DBConnection();

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
}
