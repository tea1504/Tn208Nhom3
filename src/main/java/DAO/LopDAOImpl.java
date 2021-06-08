package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.CallableStatement;

import bean.DBConnection;
import bean.Lop;

public class LopDAOImpl implements ILopDAO {
	DBConnection conn = new DBConnection();
	CallableStatement callableStatement;

	/**
	 * Lấy toàn bộ danh sách lớp bằng stored procedure <strong>`listlop`()</strong>
	 */
	@Override
	public ArrayList<Lop> getLop() {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();
		conn.getConnection();
		String query = "{call listlop()}";
		;
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAOImpl gv = new GiangVienDAOImpl();
				temp.setGiangVien(gv.getGiangVien(temp.getMaGiangVien()));
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
	 * Lấy danh sách lớp theo mã giảng viên bằng stored procedure
	 * <strong>`getLopTheoMaGiangVien`(IN `magiangvien1` varchar(10))</strong> dùng
	 * cho chức năng đăng ký phòng máy
	 */
	public ArrayList<Lop> getLopTheoMaGiangVien(String ma) {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();

		try {
			conn.getConnection();
			String query = "{call getLopTheoMaGiangVien(?)}";
			callableStatement = conn.getConnection().prepareCall(query);
			callableStatement.setString(1, ma);
			rs = callableStatement.executeQuery();
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAOImpl gv = new GiangVienDAOImpl();
				temp.setGiangVien(gv.getGiangVien(temp.getMaGiangVien()));
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
	 * Thêm mới thông tin lớp bằng stored procedure <strong>`themlopmoi`(IN
	 * `malop_new` VARCHAR(10), IN `tenlop_new` VARCHAR(50), IN `magiangvien_new`
	 * VARCHAR(10), IN `siso_new` INT)</strong>
	 */
	public boolean ThemLop(Lop l) throws SQLException {
		String query = "{call themlopmoi(?,?,?,?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		callableStatement.setString(2, l.getTenLop());
		callableStatement.setString(3, l.getMaGiangVien());
		callableStatement.setInt(4, l.getSiSoLop());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}

	/**
	 * Sửa thông tin của một lớp bằng stored procedure <strong>`sualop`(IN
	 * `malop_new` VARCHAR(10), IN `tenlop_new` VARCHAR(50), IN `magiangvien_new`
	 * VARCHAR(10), IN `siso_new` INT)</strong>
	 */
	public boolean SuaLop(Lop l) throws SQLException {
		String query = "{call sualop(?,?,?,?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		callableStatement.setString(2, l.getTenLop());
		callableStatement.setString(3, l.getMaGiangVien());
		callableStatement.setInt(4, l.getSiSoLop());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}

	/**
	 * Xóa thông tin lớp bằng stored procedure <strong>`xoalop`(IN `malop_de`
	 * VARCHAR(10))</strong> trước khi xóa mysql sẽ gọi trigger
	 * <strong>`trigger_xoa`</strong> để xóa toàn bộ thông tin đăng ký của lớp
	 */
	public boolean XoaLop(Lop l) throws SQLException {
		String query = "{call xoalop(?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}

	public Lop getLop(String searchMa) {
		Lop lop = new Lop();
		ResultSet rs;
		String query = "call getLopTheoMa(?)";
		try {
			callableStatement = conn.getConnection().prepareCall(query);
			callableStatement.setString(1, searchMa);
			rs = callableStatement.executeQuery();
			while (rs.next()) {
				lop = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
			callableStatement.close();
			conn.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lop;
	}

	/**
	 * Tìm danh sách lớp bằng stored procedure <strong>`timlop`(IN `searchText`
	 * VARCHAR(50))</strong>
	 */
	public ArrayList<Lop> timLop(String search) {
		ArrayList<Lop> listLop = new ArrayList<Lop>();

		ResultSet rs;

		try {
			conn.getConnection();
			String query = "{call timlop(?)}";
			callableStatement = conn.getConnection().prepareCall(query);
			callableStatement.setString(1, search);
			rs = callableStatement.executeQuery();
			while (rs.next()) {
				Lop lop = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				listLop.add(lop);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listLop;
	}
}