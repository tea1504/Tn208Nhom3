package bean;

/**
 * Class dùng để lưu dữ liệu lớp. Bao gồm:
 * <ul>
 * <li>Mã lớp - <strong>String</strong></li>
 * <li>Tên lớp - <strong>String</strong></li>
 * <li>Sỉ số lớp - <strong>int</strong></li>
 * <li>Mã giảng viên - <strong>String</strong></li>
 * <li>Giảng viên- <strong>GiangVien</strong></li>
 * </ul>
 * 
 * @author Trương Trung Trọng
 *
 */
public class Lop {
	private String maLop;
	private String tenLop;
	private int siSoLop;
	private String maGiangVien;
	private GiangVien giangVien;

	/**
	 * Hàm xây dựng không đối số
	 */
	public Lop() {

	}

	/**
	 * Hàm xây dựng 4 đối số
	 * 
	 * @param maLop       String
	 * @param maGiangVien String
	 * @param tenLop      String
	 * @param siSoLop     int
	 */
	public Lop(String maLop, String maGiangVien, String tenLop, int siSoLop) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.siSoLop = siSoLop;
		this.maGiangVien = maGiangVien;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public int getSiSoLop() {
		return siSoLop;
	}

	public void setSiSoLop(int siSoLop) {
		this.siSoLop = siSoLop;
	}

	public String getMaGiangVien() {
		return maGiangVien;
	}

	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}

	public GiangVien getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMaLop() + " | " + getTenLop();
	}
}
