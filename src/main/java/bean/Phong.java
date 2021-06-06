package bean;

public class Phong {
	private String MaPhong;
	private String TenPhong;

//	Hàm xây dựng 
	public Phong(String maPhong, String tenPhong, int soMay) {
		super();
		MaPhong = maPhong;
		TenPhong = tenPhong;
		SoLuongMay = soMay;
	}

	private int SoLuongMay;

//	Phương thức lấy mã phòng
	public String getMaPhong() {
		return MaPhong;
	}

//	Phương thức gán mã phòng
	public void setMaPhong(String maPhong) {
		MaPhong = maPhong;
	}

//	Phương thức lấy tên phòng
	public String getTenPhong() {
		return TenPhong;
	}

//	Phương thức gán tên phòng
	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}
	
//	Phương thức lấy số lượng máy
	public int getSoLuongMay() {
		return SoLuongMay;
	}

//	Phương thức gán số lượng máy
	public void setSoMay(int soMay) {
		SoLuongMay = soMay;
	}
	
	public String toString() {
		return getTenPhong();
	}
}
