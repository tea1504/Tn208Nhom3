package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.Lop;



public class LopDAO {
	DBConnection conn = new DBConnection();

	public ArrayList<Lop> getLop() {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();
		conn.getConnection();
		String query = "select * from lop";
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAO gv = new GiangVienDAO();
				temp.setGv(gv.getGiangVien(temp.getMaGV()));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}

	public ArrayList<Lop> getLop(String id) {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();
		conn.getConnection();
		String query = "select * from lop where magiangvien='" + id + "'";
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAO gv = new GiangVienDAO();
				temp.setGv(gv.getGiangVien(temp.getMaGV()));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}

	public int ThemLop(Lop l) {
		String query = "insert into lop (malop, magiangvien, tenlop, sisolop) values ('" + l.getMaLop() + "', '"
				+ l.getMaGV() + "', N'" + l.getTenLop() + "'," + l.getSiSoLop() + ")";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}

	public int SuaLop(Lop l) {
		String query = "update lop set magiangvien='" + l.getMaGV() + "', tenlop=N'" + l.getTenLop() + "', sisolop="
				+ l.getSiSoLop() + " where malop='" + l.getMaLop() + "'";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}

	public int XoaLop(Lop l) {
		String query = "delete from lop where malop='" + l.getMaLop() + "'";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}
}
