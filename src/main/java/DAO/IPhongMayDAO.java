package DAO;

import java.sql.Date;
import java.util.ArrayList;

import bean.Phong;

public interface IPhongMayDAO {
	int ThemPhong(Phong p);
	int SuaPhong(Phong p);
	int XoaPhong(Phong p);
	ArrayList<Phong> TimPhong(String searchText);
	ArrayList<Phong> ListPhong();
	ArrayList<Phong> getPhongChuaDangKy(Date ngaydangky, int buoidangky);
}
