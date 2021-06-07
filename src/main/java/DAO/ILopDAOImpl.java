package DAO;

import java.sql.SQLException;
import java.util.ArrayList;


import bean.Lop;

public interface ILopDAOImpl {
	ArrayList<Lop> getLop();
	Lop getLop(String searchMa);
	boolean ThemLop(Lop lop) throws SQLException;
	boolean SuaLop(Lop lop) throws SQLException;
	boolean XoaLop(Lop lop) throws SQLException;
	ArrayList<Lop> timLop(String searchMa);
	ArrayList<Lop> getLopTheoMaGiangVien(String ma);
}