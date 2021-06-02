package DAO;

import java.util.ArrayList;

import bean.DangKy;

public interface IDangKyDAO {
	int CreateDangKy(DangKy dangKy);
	int DeleteDangKy(DangKy dangKy);
	ArrayList<DangKy> ListDangKy();
	ArrayList<DangKy> ListDangKyTheoGV(String maGiangVien);
}
