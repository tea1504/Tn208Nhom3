package bean;

public class Lop {
	private String maLop;
	private String tenLop;
	private int siSoLop;
	private String maGiangVien;
	private GiangVien giangVien;
	public Lop()
	{
		
	}
	public Lop(String maLop, String tenLop,  String maGiangVien, int siSoLop) {
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
