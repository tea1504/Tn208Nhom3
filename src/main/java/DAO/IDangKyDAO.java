package DAO;

import java.util.ArrayList;

import bean.DangKy;

/**
 * Interface cho chức năng đăng ký
 * 
 * @author Trần Văn Hòa
 *
 */
public interface IDangKyDAO {
	/**
	 * Đăng ký phòng máy
	 * 
	 * @param dangKy {@link DangKy}
	 * @return số dòng đã tạo {@link Integer}
	 */
	int CreateDangKy(DangKy dangKy);

	/**
	 * Hủy đăng ký phòng máy
	 * 
	 * @param id {@link Integer}
	 * @return số dòng đã xóa {@link Integer}
	 */
	int DeleteDangKy(int id);

	/**
	 * Lấy toàn bộ danh sách đăng ký trong CSDL
	 * 
	 * @return Toàn bộ danh sách đã đăng ký {@link ArrayList}&lt;{@link DangKy}&gt;
	 */
	ArrayList<DangKy> ListDangKy();

	/**
	 * Lấy toàn bộ danh sách đăng ký của một giảng viên nào đó trong CSDL
	 * 
	 * @param maGiangVien {@link String}
	 * @return Toàn bộ danh sách đã đăng ký {@link ArrayList}&lt;{@link DangKy}&gt;
	 */
	ArrayList<DangKy> ListDangKyTheoGV(String maGiangVien);
}
