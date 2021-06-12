package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Lop;

/**
 * Interface cho chức năng quản lý lớp
 * 
 * @author Trương Trung Trọng
 *
 */
public interface ILopDAO {
	/**
	 * Lấy toàn bộ danh sách lớp
	 * 
	 * @return danh sách lớp {@link ArrayList}&lt;{@link Lop}&gt;
	 */
	ArrayList<Lop> getLop();

	/**
	 * Lấy thông tin lớp theo mã lớp
	 * 
	 * @param searchMa {@link String}
	 * @return thông tin lớp {@link Lop}
	 */
	Lop getLop(String searchMa);

	/**
	 * Thêm thông tin lớp mới
	 * 
	 * @param lop {@link Lop}
	 * @return Kết quả thêm {@link Boolean}
	 * @throws SQLException
	 */
	boolean ThemLop(Lop lop) throws SQLException;

	/**
	 * Sửa thông tin lớp
	 * 
	 * @param lop {@link Lop}
	 * @return kết quả sửa {@link Boolean}
	 * @throws SQLException
	 */
	boolean SuaLop(Lop lop) throws SQLException;

	/**
	 * Xóa thông tin lớp
	 * 
	 * @param lop {@link Lop}
	 * @return Kết quả xóa {@link Boolean}
	 * @throws SQLException
	 */
	boolean XoaLop(Lop lop) throws SQLException;

	/**
	 * Tìm danh sách lớp theo mã lớp, sỉ số lớp, tên lớp hoặc mã giảng viên
	 * 
	 * @param searchMa {@link String}
	 * @return Danh sách lớp {@link ArrayList}&lt;{@link Lop}&gt;
	 */
	ArrayList<Lop> timLop(String searchMa);

	/**
	 * Lấy danh sách lớp theo mã giảng viên
	 * 
	 * @param ma {@link String}
	 * @return Danh sách lớp {@link ArrayList}&lt;{@link Lop}&gt;
	 */
	ArrayList<Lop> getLopTheoMaGiangVien(String ma);

	/**
	 * Kiểm tra mã lớp đã tồn tại chưa
	 * 
	 * @param id {@link String}
	 * @return kết quả {@link Boolean}
	 */
	boolean checkKhoaChinh(String id);
}