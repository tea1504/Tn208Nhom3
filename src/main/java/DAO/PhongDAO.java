package DAO;

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;

import bean.DBConnection;
import bean.Phong;

public class PhongDAO {
	DBConnection conn = new DBConnection();
//	public ArrayList<Phong> getPhong(){
//		ArrayList<Phong> list = new ArrayList<Phong>();
//		String query = "select * from phongmay";
//		conn.getConnection();
//		ResultSet res = conn.excuted(query);
//		try {
//			while(res.next()) {
//				list.add(new Phong(res.getString(1), res.getString(2), res.getInt(3)));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		conn.closeConnection();
//		return list;
//	}
	
	public int ThemPhong(Phong p) {
		String query = "insert into phongmay (maphong, tenphong, somay) values ('" + p.getMaPhong() + "', '"
				+ p.getTenPhong() + "'," + p.getSoLuongMay() + ")";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}

	public int SuaPhong(Phong p) {
		String query = "update phongmay set tenphong='" + p.getTenPhong() + "', somay="
				+ p.getSoLuongMay() + " where maphong='" + p.getMaPhong() + "'";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}

	public int XoaPhong(Phong p) {
		String query = "delete from phongmay where maphong='" + p.getMaPhong() + "'";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}
	
	public int TimPhong(Phong p) {
		String query = "select * from phongmay where maphong=?";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}
	
}
