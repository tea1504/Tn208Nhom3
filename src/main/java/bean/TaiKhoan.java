package bean;
/**
 * Model Tài khoản
 * @author Trịnh Thanh Thảo
 *
 */
public class TaiKhoan {
	private String maGiangVien;
	private String userPassword;
	private int quyenSD;
	
	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String maGiangVien, String userPassword, int quyenSD) {
		super();
		this.maGiangVien=maGiangVien;
		this.userPassword=userPassword;
		this.quyenSD=quyenSD;
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
