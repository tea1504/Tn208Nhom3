package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.Phong;

public class PhongMayDAOImpl implements IPhongMayDAO {
	DBConnection conn = new DBConnection();
	CallableStatement cs;

	/**
	 * Thêm thông tin phòng bằng stored procedure <strong>`themPhong`(IN `_maphong`
	 * VARCHAR(5), IN `_tenphong` VARCHAR(50), IN `_somay` INT)</strong>
	 */
	@Override
	public boolean ThemPhong(Phong p) throws SQLException {
		String query = "{call themPhong (?,?,?)}";
		cs = conn.getConnection().prepareCall(query);
		cs.setString(1, p.getMaPhong());
		cs.setString(2, p.getTenPhong());
		cs.setInt(3, p.getSoLuongMay());
		boolean r = cs.execute();
		conn.closeConnection();
		return r;
	}

	/**
	 * Sửa thông tin phòng bằng stored procedure <strong>`suaPhong`(IN `_maphong`
	 * VARCHAR(5), IN `_tenphong` VARCHAR(50), IN `_somay` INT)</strong>
	 */
	@Override
	public boolean SuaPhong(Phong p) throws SQLException {
		String query = "{call suaPhong (?,?,?)}";
		cs = conn.getConnection().prepareCall(query);
		cs.setString(1, p.getMaPhong());
		cs.setString(2, p.getTenPhong());
		cs.setInt(3, p.getSoLuongMay());
		boolean r = cs.execute();
		conn.closeConnection();
		return r;
	}

	/**
	 * Xóa thông tin phòng bằng stored procedure <strong>`xoaPhong`(IN `_maphong`
	 * VARCHAR(5))</strong> trước khi xóa mysql sẽ gọi trigger
	 * <strong>`xoaThongTinPhong</strong> để xóa toàn bộ thông tin đăng ký của phòng
	 */
	@Override
	public boolean XoaPhong(Phong p) throws SQLException {
		String query = "{call xoaPhong (?)}";
		cs = conn.getConnection().prepareCall(query);
		cs.setString(1, p.getMaPhong());
		boolean r = cs.execute();
		conn.closeConnection();
		return r;
	}

	/**
	 * Tìm phòng bằng stored procedure <strong>`timPhong`(IN `searchText`
	 * VARCHAR(50))</strong>
	 */
	@Override
	public ArrayList<Phong> TimPhong(String searchText) {
		ArrayList<Phong> listPhong = new ArrayList<Phong>();

		ResultSet rs;

		try {
			conn.getConnection();
			String query = "{call timPhong (?)}";
			cs = conn.getConnection().prepareCall(query);
			cs.setString(1, searchText);
			rs = cs.executeQuery();
			while (rs.next()) {
				Phong phong = new Phong(rs.getString("maphong"), rs.getString("tenphong"), rs.getInt("somay"));
				listPhong.add(phong);
			}
			cs.close();
			conn.closeConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

//      Trả về ArrayList danh sách phòng tìm được
		return listPhong;
	}

	/**
	 * Lấy phòng theo mã phòng bằng stored procedure <strong>`getPhongTheoMa`(IN
	 * `_maPhong` VARCHAR(5))</strong>
	 */
	@Override
	public Phong getPhong(String maPhong) {
		Phong phong = new Phong();
		ResultSet res;
		String query = "call getPhongTheoMa(?)";
		try {
			cs = conn.getConnection().prepareCall(query);
			cs.setString(1, maPhong);
			res = cs.executeQuery();
			while (res.next()) {
				phong = new Phong(res.getString(1), res.getString(2), res.getInt(3));
			}
			cs.close();
			conn.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phong;
	}

	/**
	 * Lấy toàn bộ danh sách phòng bằng stored procedure
	 * <strong>`listPhong`()</strong>
	 */
	@Override
	public ArrayList<Phong> ListPhong() {
		ArrayList<Phong> list = new ArrayList<Phong>();
		String query = "{call listPhong ()}";
		conn.getConnection();
		ResultSet res = conn.excuted(query);
		try {
			while (res.next()) {
				list.add(new Phong(res.getString(1), res.getString(2), res.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnection();
//      Trả về ArrayList danh sách phòng
		return list;
	}

	/**
	 * Lấy phòng chưa đăng ký bằng stored procedure <strong>`getPhongChuaDangKy`(IN
	 * `_ngaydangky` DATE, IN `_buoidangky` INT, IN `_siso` INT)</strong>
	 */
	@Override
	public ArrayList<Phong> getPhongChuaDangKy(String ngaydangky, int buoidangky, int siso) {
		ArrayList<Phong> res = new ArrayList<Phong>();
		ResultSet rs;
		String query = "{call getPhongChuaDangKy (?,?, ?)}";
		try {
			cs = conn.getConnection().prepareCall(query);
			cs.setString(1, ngaydangky);
			cs.setInt(2, buoidangky);
			cs.setInt(3, siso);
			rs = cs.executeQuery();
			while (rs.next()) {
				Phong item = new Phong(rs.getString(1), rs.getString(2), rs.getInt(3));
				res.add(item);
			}
			cs.close();
			conn.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
//      Trả về ArrayList danh sách phòng chưa đăng ký
		return res;
	}

}
