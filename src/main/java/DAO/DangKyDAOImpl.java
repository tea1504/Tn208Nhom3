package DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import bean.DBConnection;
import bean.DangKy;

/**
 * Lớp dùng để điều khiển đăng ký phòng máy
 * 
 * @author Trần Văn Hòa
 *
 */
public class DangKyDAOImpl implements IDangKyDAO {
	DBConnection conn = new DBConnection();
	CallableStatement cstmt;

	/**
	 * Đăng ký phòng máy bằng stored procedure <strong>`dangKyPhongMay`(IN
	 * `_maPhong` VARCHAR(5), IN `_maLop` VARCHAR(7), IN `_ngayDangKy` DATE, IN
	 * `_buoiDangKy` INT)</strong>
	 */
	@Override
	public int CreateDangKy(DangKy dangKy) {
		String sql = "{call dangKyPhongMay(?, ?, ?, ?)}";
		try {
			cstmt = conn.getConnection().prepareCall(sql);
			cstmt.setString(1, dangKy.getMaPhong());
			cstmt.setString(2, dangKy.getMaLop());
			String now = dangKy.getNgayDangKy().get(Calendar.YEAR) + "-"
					+ (dangKy.getNgayDangKy().get(Calendar.MONTH) + 1) + "-"
					+ dangKy.getNgayDangKy().get(Calendar.DATE);
			cstmt.setString(3, now);
			cstmt.setInt(4, dangKy.getBuoiDangKy());
			int res = cstmt.executeUpdate();
			cstmt.close();
			conn.closeConnection();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Hủy đăng ký phòng máy bằng stored procedure <strong>`huyDangKy`(IN
	 * `_madangky` INT)</strong>
	 */
	@Override
	public int DeleteDangKy(int id) {
		String sql = "{call huyDangKy(?)}";
		try {
			cstmt = conn.getConnection().prepareCall(sql);
			cstmt.setInt(1, id);
			int res = cstmt.executeUpdate();
			cstmt.close();
			conn.closeConnection();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Lấy toàn bộ danh sách đăng ký trong cơ sở dữ liệu bằng stored procedure
	 * <strong>`listDangKy`()</strong>
	 */
	@Override
	public ArrayList<DangKy> ListDangKy() {
		ArrayList<DangKy> res = new ArrayList<DangKy>();
		ResultSet rs;
		String sql = "call listDangKy()";
		try {
			cstmt = conn.getConnection().prepareCall(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate(4);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				DangKy item = new DangKy(rs.getInt(1), rs.getString(2), rs.getString(3), calendar, rs.getInt(5));
				res.add(item);
			}
			cstmt.close();
			conn.closeConnection();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Lấy toàn bộ danh sách đăng ký theo mã giảng viên bằng stored procedure
	 * <strong>`getDangKyTheoGV`(IN `_magiangvien` VARCHAR(5))</strong>
	 */
	@Override
	public ArrayList<DangKy> ListDangKyTheoGV(String maGiangVien) {
		ArrayList<DangKy> res = new ArrayList<DangKy>();
		ResultSet rs;
		String sql = "{call getDangKyTheoGV (?)}";
		try {
			cstmt = conn.getConnection().prepareCall(sql);
			cstmt.setString(1, maGiangVien);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate(4);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				DangKy item = new DangKy(rs.getInt(1), rs.getString(2), rs.getString(3), calendar, rs.getInt(5));
				LopDAOImpl lopDAO = new LopDAOImpl();
				PhongMayDAOImpl phongDAO = new PhongMayDAOImpl();
				item.setLop(lopDAO.getLop(item.getMaLop()));
				item.setPhong(phongDAO.getPhong(item.getMaPhong()));
				res.add(item);
			}
			cstmt.close();
			conn.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}

}
