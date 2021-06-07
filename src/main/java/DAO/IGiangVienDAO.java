package DAO;

import java.util.ArrayList;

import bean.GiangVien;

public interface IGiangVienDAO {
	ArrayList<GiangVien> getGiangVien();
	GiangVien getGiangVien(String id);
	boolean ThemGiangVien(GiangVien giangvien);
	boolean SuaGiangVien(GiangVien gv);
	boolean XoaGiangVien(GiangVien giangvien);
	ArrayList<GiangVien> TimGiangVien(String searchText);
}
