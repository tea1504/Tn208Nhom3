package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.GiangVien;

/**
 * Class dùng để quản lý giảng viên
 * 
 * @author Trịnh Thanh Thảo
 *
 */
public class GiangVienDAOImpl implements IGiangVienDAO {
	DBConnection conn = new DBConnection();
	CallableStatement cstmt;

	/**
	 * Lấy toàn bộ danh sách giảng viên bằng stored procedure
	 * <strong>`listGiangVien`()</strong>
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

	/**
	 * Lấy thông tin giảng viên theo mã giảng viên bằng store procedure
	 * <strong>`getGiangVienTheoMa`(IN `_magiangvien` VARCHAR(5))</strong>
	 */
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
			e1.printStackTrace();
		}
		conn.closeConnection();
		return gv;
	}

	/**
	 * Thêm thông tin giảng viên bằng stored procedure <strong>`themGiangVien`(IN
	 * `_magiangvien` VARCHAR(5), IN `_tengiangvien` VARCHAR(50))</strong> sau khi
	 * thêm giảng viên mysql sẽ gọi trigger <strong>`addTaiKhoanTuDong`</strong> để
	 * tự động thêm tài khoản cho giảng viên vừa tạo
	 */
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
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * Sửa thông tin giảng viên bằng store procedure <strong>`suaGiangVien`(IN
	 * `_magiangvien` VARCHAR(5), IN `_tengiangvien` VARCHAR(50))</strong>
	 */
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
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * Xóa thông tin giảng viên bằng stored procedure <strong>`xoaGiangVien`(IN
	 * `_magiangvien` VARCHAR(5))</strong> và trước khi xóa giảng viên mysql sẽ gọi
	 * trigger <strong>`xoaGiangVien`</strong> để xóa tất cacr các lớp do giảng viên
	 * bị xóa dạy
	 */
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
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * Lấy danh sách giảng viên theo mã giảng viên hoặc theo tên giảng viên bằng
	 * stored procedure <strong>`timGiangVien`(IN `searchText` VARCHAR(10))</strong>
	 */
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
				gv = new GiangVien(rs.getString("magiangvien"), rs.getString("tengiangvien"), rs.getInt("quyensd"));
				gvList.add(gv);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return gvList;
	}

	@Override
	public boolean checkKhoaChinh(String id) {
		ResultSet rs;
		boolean flag = false;
		String query = "call getGiangVienTheoMa(?)";
		try {
			cstmt = conn.getConnection().prepareCall(query);
			cstmt.setString(1, id);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		conn.closeConnection();
		return flag;
	}
}
