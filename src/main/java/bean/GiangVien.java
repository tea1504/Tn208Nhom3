package bean;

import java.util.ArrayList;

public class GiangVien {
	public GiangVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String Ma;
	private String Ten;
	private ArrayList<Lop> dsLop;
	public ArrayList<Lop> getDsLop() {
		return dsLop;
	}
	public void setDsLop(ArrayList<Lop> dsLop) {
		this.dsLop = dsLop;
	}
	public String getMa() {
		return Ma;
	}
	public void setMa(String ma) {
		Ma = ma;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public GiangVien(String ma, String ten) {
		super();
		Ma = ma;
		Ten = ten;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMa() + " | " + getTen();
	}
}
