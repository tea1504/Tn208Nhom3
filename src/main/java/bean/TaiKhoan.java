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
	
	public String getmaGiangVien() {
		return maGiangVien;
	}
	
	public String getuserPassword() {
		return userPassword;
	}
	
	public int getquyenSD() {
		return quyenSD;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return getmaGiangVien() + "\t" + getquyenSD();
	}
	
}
