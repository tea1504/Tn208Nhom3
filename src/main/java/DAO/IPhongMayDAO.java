package DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Phong;

public interface IPhongMayDAO {
	boolean ThemPhong(Phong p) throws SQLException;
	boolean SuaPhong(Phong p) throws SQLException;
	boolean XoaPhong(Phong p) throws SQLException;
	ArrayList<Phong> TimPhong(String searchText);
	ArrayList<Phong> ListPhong();
	ArrayList<Phong> getPhongChuaDangKy(String ngaydangky, int buoidangky, int siso);
}
