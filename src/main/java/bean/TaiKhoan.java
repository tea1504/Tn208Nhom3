package bean;

/**
 * Class dùng để lưu dữ liệu tài khoản. Bao gồm:
 * <ul>
 * <li>Mã giảng viên - <strong>String</strong></li>
 * <li>password - <strong>String</strong></li>
 * <li>Quyền sửa dụng - <strong>int</strong></li>
 * </ul>
 * 
 * @author Trịnh Thanh Thảo
 *
 */
public class TaiKhoan {
	private String maGiangVien;
	private String userPassword;
	private int quyenSD;

	/**
	 * Hàm xây dựng không đối số
	 */
	public TaiKhoan() {
		super();
	}

	/**
	 * Hàm xây dựng 3 đối số
	 * 
	 * @param maGiangVien  String
	 * @param userPassword String
	 * @param quyenSD      int
	 */
	public TaiKhoan(String maGiangVien, String userPassword, int quyenSD) {
		super();
		this.maGiangVien = maGiangVien;
		this.userPassword = userPassword;
		this.quyenSD = quyenSD;
	}

	public String getMaGiangVien() {
		return maGiangVien;
	}

	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getQuyenSD() {
		return quyenSD;
	}

	public void setQuyenSD(int quyenSD) {
		this.quyenSD = quyenSD;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return getMaGiangVien() + "\t" + getQuyenSD();
	}

}
