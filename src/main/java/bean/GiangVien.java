package bean;

import java.util.ArrayList;





public class GiangVien {
	private String maGiangVien;
	private String tenGiangVien;
	private ArrayList<Lop> dsLop;
	public ArrayList<Lop> getDsLop() {
		return dsLop;
	}
	
	public GiangVien() {
		super();
	}
	public void setDsLop(ArrayList<Lop> dsLop) {
		this.dsLop = dsLop;
	}
	
	public GiangVien(String maGiangVien, String tenGiangVien) {
		super();
		this.maGiangVien=maGiangVien;
		this.tenGiangVien=tenGiangVien;
	}
	
	public String getmaGiangVien() {
		return maGiangVien;
	}
	public void setmaGiangVien(String ma) {
		maGiangVien = ma;
	}
	public String gettenGiangVien() {
		return tenGiangVien;
	}
	
	public void setTenGiangVien(String ten) {
		tenGiangVien = ten;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getmaGiangVien() + "\t" + gettenGiangVien();
	}
}
