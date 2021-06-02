package DAO;

import java.util.ArrayList;

import bean.DangKy;

public interface IDangKyDAO {
	/**
	 * Đăng ký phòng máy
	 * @param dangKy - Dữ liệu đăng ký
	 * @return số dòng đã tạo
	 * @author Trần Văn Hòa
	 */
	int CreateDangKy(DangKy dangKy);
	/**
	 * Hủy đăng ký phòng máy
	 * @param dangKy - Dữ liệu đăng ký
	 * @return số dòng đã xóa
	 * @author Trần Văn Hòa
	 */
	int DeleteDangKy(DangKy dangKy);
	/**
	 * Lấy toàn bộ danh sách đăng ký trong CSDL
	 * @return Toàn bộ danh sách đã đăng ký
	 * @author Trần Văn Hòa
	 */
	ArrayList<DangKy> ListDangKy();
	/**
	 * Lấy toàn bộ danh sách đăng ký của một giảng viên nào đó trong CSDL
	 * @param maGiangVien - Mã của giảng viên
	 * @return Toàn bộ danh sách đã đăng ký
	 * @author Trần Văn Hòa
	 */
	ArrayList<DangKy> ListDangKyTheoGV(String maGiangVien);
}
