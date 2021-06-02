package bean;

public class Lop {
	private String MaLop;
	private String TenLop;
	private int SiSoLop;
	private String MaGiangVien;
	
	
	public Lop()
	{
		
	}
	
	public Lop(String maLop, String tenLop, int siSoLop, String maGiangVien) {
		super();
		MaLop = maLop;
		TenLop = tenLop;
		SiSoLop = siSoLop;
		MaGiangVien = maGiangVien;
	}
	
	
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
	public String getTenLop() {
		return TenLop;
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	public int getSiSoLop() {
		return SiSoLop;
	}
	public void setSiSoLop(int siSoLop) {
		SiSoLop = siSoLop;
	}
	public String getMaGiangVien() {
		return MaGiangVien;
	}
	public void setMaGiangVien(String maGiangVien) {
		MaGiangVien = maGiangVien;
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMaLop() + " | " + getTenLop();
	}
	
	
	
	
}
