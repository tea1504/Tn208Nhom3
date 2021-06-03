package DAO;

import java.util.ArrayList;


import bean.Lop;

public interface ILopDAO {
	int CreateLop(Lop lop);
	int DeleteGiangVien(Lop lop);
	ArrayList<Lop> ListLop();
}
