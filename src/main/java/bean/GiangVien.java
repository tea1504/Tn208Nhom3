package bean;

/**
 * Model Giảng viên
 * @author Trịnh Thanh Thảo
 *
 */

public class GiangVien {
	private String maGiangVien;
	private String tenGiangVien;
	
	public GiangVien() {
		super();
	}
	
	public GiangVien(String maGiangVien, String tenGiangVien) {
		super();
		this.maGiangVien=maGiangVien;
		this.tenGiangVien=tenGiangVien;
	}
	
	public String getmaGiangVien() {
		return maGiangVien;
	}
	
	public String gettenGiangVien() {
		return tenGiangVien;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getmaGiangVien() + "\t" + gettenGiangVien();
	}
}
