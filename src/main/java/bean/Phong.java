package bean;

public class Phong {
	private String MaPhong;
	private String TenPhong;
	public Phong(String maPhong, String tenPhong, int soMay) {
		super();
		MaPhong = maPhong;
		TenPhong = tenPhong;
		SoLuongMay = soMay;
	}
	private int SoLuongMay;
	public String getMaPhong() {
		return MaPhong;
	}
	public void setMaPhong(String maPhong) {
		MaPhong = maPhong;
	}
	public String getTenPhong() {
		return TenPhong;
	}
	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}
	public int getSoLuongMay() {
		return SoLuongMay;
	}
	public void setSoMay(int soMay) {
		SoLuongMay = soMay;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMaPhong() + "\t" + getTenPhong() + "\t" + getSoLuongMay();
	}
}
