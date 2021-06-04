package bean;

public class Lop {
	private String MaLop;
	private String TenLop;
	private int SiSoLop;
	private String MaGV;
	
	public String getMaGV() {
		return MaGV;
	}
	public void setMaGV(String maGV) {
		MaGV = maGV;
	}
	private GiangVien gv;
	public int getSiSoLop() {
		return SiSoLop;
	}
	public void setSiSoLop(int siSoLop) {
		SiSoLop = siSoLop;
	}
	public GiangVien getGv() {
		return gv;
	}
	public void setGv(GiangVien gv) {
		this.gv = gv;
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
	public Lop(String maLop, String maGV, String tenLop, int siSoLop) {
		super();
		MaLop = maLop;
		TenLop = tenLop;
		SiSoLop = siSoLop;
		MaGV = maGV;
	}
	public Lop() {
		// TODO Auto-generated constructor stub
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMaLop() + " | " + getTenLop();
	}
}
