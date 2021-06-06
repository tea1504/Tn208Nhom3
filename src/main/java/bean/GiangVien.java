package bean;

/**
 * Model Giảng viên
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
	
	public GiangVien(String maGiangVien, String tenGiangVien) {
		super();
		this.maGiangVien=maGiangVien;
		this.tenGiangVien=tenGiangVien;
	}
	public GiangVien(String maGiangVien, String tenGiangVien, int quyenSD) {
		super();
		this.maGiangVien=maGiangVien;
		this.tenGiangVien=tenGiangVien;
		this.quyenSD=quyenSD;
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
