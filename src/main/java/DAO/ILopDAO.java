package DAO;

import java.sql.SQLException;
import java.util.ArrayList;


import bean.Lop;

public interface ILopDAO {
	boolean ThemLop(Lop lop) throws SQLException;
	boolean SuaLop(Lop lop) throws SQLException;
	boolean XoaLop(Lop lop) throws SQLException;
	ArrayList<Lop> getLop();
	ArrayList<Lop> timloptheoma(String searchMa);
}