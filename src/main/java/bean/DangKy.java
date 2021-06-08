package bean;

import java.util.Calendar;

/**
 * Class dùng để lưu dữ liệu đăng ký. Bao gồm:
 * <ul>
 * <li>Mã đăng ký - <strong>int</strong></li>
 * <li>Mã phòng - <strong>String</strong></li>
 * <li>Mã lớp - <strong>String</strong></li>
 * <li>Ngày đăng ký - <strong>Calendar</strong></li>
 * <li>Buổi đăng ký - <strong>int</strong></li>
 * <li>Lớp - <strong>Lop</strong></li>
 * <li>Phòng- <strong>Phong</strong></li>
 * </ul>
 * 
 * @author Trần Văn Hòa
 *
 */
public class DangKy {
	private int maDangKy;
	private String maPhong;
	private String maLop;
	private Calendar ngayDangKy;
	private int buoiDangKy;
	private Lop lop;
	private Phong phong;

	/**
	 * Hàm khởi tạo rỗng
	 */
	public DangKy() {
		super();
	}

	/**
	 * Hàm khởi tạo 5 đối số
	 * 
	 * @param maDangKy   int
	 * @param maPhong    String
	 * @param maLop      String
	 * @param ngayDangKy Calendar
	 * @param buoiDangKy int (0-Buổi sáng, 1-Buổi chiều, 2-Buổi tối);
	 */
	public DangKy(int maDangKy, String maPhong, String maLop, Calendar ngayDangKy, int buoiDangKy) {
		super();
		this.maDangKy = maDangKy;
		this.maPhong = maPhong;
		this.maLop = maLop;
		this.ngayDangKy = ngayDangKy;
		this.buoiDangKy = buoiDangKy;
	}

	public int getMaDangKy() {
		return maDangKy;
	}

	public void setMaDangKy(int maDangKy) {
		this.maDangKy = maDangKy;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public Calendar getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(Calendar ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public int getBuoiDangKy() {
		return buoiDangKy;
	}

	public void setBuoiDangKy(int buoiDangKy) {
		this.buoiDangKy = buoiDangKy;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMaDangKy() + "\t" + getMaPhong() + "\t" + getMaLop() + "\t" + getNgayDangKy() + "\t"
				+ getBuoiDangKy();
	}
}
