package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Phong;

/**
 * Interface cho chức năng quản lý phòng máy
 * 
 * @author Lê Ngọc Huỳnh
 *
 */
public interface IPhongMayDAO {
	/**
	 * Thêm thông tin phòng
	 * 
	 * @param p {@link Phong}
	 * @return Kết quả thêm {@link Boolean}
	 * @throws SQLException
	 */
	boolean ThemPhong(Phong p) throws SQLException;

	/**
	 * Sửa thông tin phòng
	 * 
	 * @param p {@link Phong}
	 * @return Kết quả sửa {@link Boolean}
	 * @throws SQLException
	 */
	boolean SuaPhong(Phong p) throws SQLException;

	/**
	 * Xóa thông tin phòng
	 * 
	 * @param p {@link Phong}
	 * @return Kết quả xóa {@link Boolean}
	 * @throws SQLException
	 */
	boolean XoaPhong(Phong p) throws SQLException;

	/**
	 * Tìm phòng theo mã phòng, tên phòng hay số lượng máy
	 * 
	 * @param searchText {@link String}
	 * @return Danh sách phòng {@link ArrayList}&lt;{@link Phong}&gt;
	 */
	ArrayList<Phong> TimPhong(String searchText);

	/**
	 * Lấy toàn bộ danh sách phòng
	 * 
	 * @return Danh sách phòng {@link ArrayList}&lt;{@link Phong}&gt;
	 */
	ArrayList<Phong> ListPhong();

	/**
	 * Lấy danh sách phòng chưa đăng ký theo ngày buổi và số phòng máy >= sỉ số lớp
	 * 
	 * @param ngaydangky {@link String}
	 * @param buoidangky {@link Integer}
	 * @param siso       {@link Integer}
	 * @return Danh sách phòng {@link ArrayList}&lt;{@link Phong}&gt;
	 */
	ArrayList<Phong> getPhongChuaDangKy(String ngaydangky, int buoidangky, int siso);

	/**
	 * Lấy phòng theo mã phòng
	 * 
	 * @param maPhong {@link String}
	 * @return Phòng {@link Phong}
	 */
	Phong getPhong(String maPhong);
}
