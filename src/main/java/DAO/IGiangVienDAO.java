package DAO;

import java.util.ArrayList;

import bean.GiangVien;

/**
 * Interface cho chức năng quản lý giảng viên
 * 
 * @author Trịnh Thanh Thảo
 *
 */
public interface IGiangVienDAO {
	/**
	 * Lấy toàn bộ danh sách giảng viên
	 * 
	 * @return danh sách giảng viên
	 *         <strong>{@link ArrayList}&lt;{@link GiangVien}&gt;</strong>
	 */
	ArrayList<GiangVien> getGiangVien();

	/**
	 * Lấy thông tin giảng viên theo mã giảng viên
	 * 
	 * @param id {@link String}
	 * @return thông tin giảng viên {@link GiangVien}
	 */
	GiangVien getGiangVien(String id);

	/**
	 * Thêm thông tin giảng viên
	 * 
	 * @param giangvien {@link GiangVien}
	 * @return kết quả thêm {@link Boolean}
	 */
	boolean ThemGiangVien(GiangVien giangvien);

	/**
	 * Sửa thông tin giảng viên
	 * 
	 * @param gv {@link GiangVien}
	 * @return kết quả sửa {@link Boolean}
	 */
	boolean SuaGiangVien(GiangVien gv);

	/**
	 * Xóa thông tin giảng viên
	 * @param giangvien {@link GiangVien}
	 * @return kết quả xóa {@link Boolean}
	 */
	boolean XoaGiangVien(GiangVien giangvien);

	/**
	 * Lấy danh sách giảng viên theo một tiêu chí nào đó(mã giảng viên hoặc tên
	 * giảng viên)
	 * 
	 * @param searchText {@link String}
	 * @return danh sách giảng viên {@link ArrayList}&lt;{@link GiangVien}&gt;
	 */
	ArrayList<GiangVien> TimGiangVien(String searchText);

	/**
	 * Kiểm tra mã giảng viên đã tồn tại chưa
	 * 
	 * @param id {@link String}
	 * @return kết quả {@link Boolean}
	 */
	boolean checkKhoaChinh(String id);
}
