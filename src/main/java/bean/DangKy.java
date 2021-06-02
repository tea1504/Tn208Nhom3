package bean;

import java.sql.Date;

public class DangKy {
	private int maDangKy;
	private String maPhong;
	private String maLop;
	private Date ngayDangKy;
	private int buoiDangKy;
	
	public DangKy() {
		super();
	}
	public DangKy(int maDangKy, String maPhong, String maLop, Date ngayDangKy, int buoiDangKy) {
		super();
		this.maDangKy = maDangKy;
		this.maPhong = maPhong;
		this.maLop = maLop;
		this.ngayDangKy = ngayDangKy;
		this.buoiDangKy = buoiDangKy;
	}
	public int getMaDangKy() {
		return maDangKy;
	}
	public void setMaDangKy(int maDangKy) {
		this.maDangKy = maDangKy;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public Date getNgayDangKy() {
		return ngayDangKy;
	}
	public void setNgayDangKy(Date ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}
	public int getBuoiDangKy() {
		return buoiDangKy;
	}
	public void setBuoiDangKy(int buoiDangKy) {
		this.buoiDangKy = buoiDangKy;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMaDangKy() + "\t" + getMaPhong() + "\t" + getMaLop() + "\t" + getNgayDangKy() + "\t" + getBuoiDangKy();
	}
}
