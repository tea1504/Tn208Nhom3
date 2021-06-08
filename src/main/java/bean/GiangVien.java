package bean;

/**
 * Class dùng để lưu dữ liệu giảng viên. Bao gồm:
 * <ul>
 * <li>Mã giảng viên - <strong>String</strong></li>
 * <li>Tên giảng viên - <strong>String</strong></li>
 * <li>Quyền sửa dụng - <strong>int</strong></li>
 * </ul>
 * 
 * @author Trịnh Thanh Thảo
 *
 */
public class GiangVien {
	private String maGiangVien;
	private String tenGiangVien;
	private int quyenSD;

	public GiangVien() {
		super();
	}

	/**
	 * Hàm xây dựng 2 đối số
	 * 
	 * @param maGiangVien  String
	 * @param tenGiangVien String
	 */
	public GiangVien(String maGiangVien, String tenGiangVien) {
		super();
		this.maGiangVien = maGiangVien;
		this.tenGiangVien = tenGiangVien;
	}

	/**
	 * Hàm xây dựng 3 đối số
	 * 
	 * @param maGiangVien  String
	 * @param tenGiangVien String
	 * @param quyenSD      int (0-user, 1-admin)
	 */
	public GiangVien(String maGiangVien, String tenGiangVien, int quyenSD) {
		super();
		this.maGiangVien = maGiangVien;
		this.tenGiangVien = tenGiangVien;
		this.quyenSD = quyenSD;
	}

	public String getMaGiangVien() {
		return maGiangVien;
	}

	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}

	public String getTenGiangVien() {
		return tenGiangVien;
	}

	public void setTenGiangVien(String tenGiangVien) {
		this.tenGiangVien = tenGiangVien;
	}

	public int getQuyenSD() {
		return quyenSD;
	}

	public void setQuyenSD(int quyenSD) {
		this.quyenSD = quyenSD;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMaGiangVien() + "\t" + getTenGiangVien();
	}
}
