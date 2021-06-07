package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.CallableStatement;

import bean.DBConnection;
import bean.GiangVien;
import bean.Lop;
import bean.Phong;




public class LopDAO implements ILopDAOImpl {
	DBConnection conn = new DBConnection();
	CallableStatement callableStatement;
// lay danh sach lop su dung procedure
	public ArrayList<Lop> getLop() {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();
		conn.getConnection();
		String query = "{call listlop()}";;
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAOImpl gv = new GiangVienDAOImpl();
				temp.setGiangVien(gv.getGiangVien(temp.getMaGiangVien()));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}
// lấy danh sách lớp theo mã giang vien su dung procedure
	public ArrayList<Lop> getLopTheoMaGiangVien(String ma) {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();
		
		try {
		conn.getConnection();
		String query = "{call getLopTheoMaGiangVien(?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, ma);
		rs = callableStatement.executeQuery();
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAOImpl gv = new GiangVienDAOImpl();
				temp.setGiangVien(gv.getGiangVien(temp.getMaGiangVien()));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}
// thêm lớp mới sử dụng procedure
	public boolean ThemLop(Lop l) throws SQLException {
		String query = "{call themlopmoi(?,?,?,?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		callableStatement.setString(2, l.getTenLop());
		callableStatement.setString(3, l.getMaGiangVien());
		callableStatement.setInt(4, l.getSiSoLop());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}
// sửa lớp sử dụng procedure
	public boolean SuaLop(Lop l) throws SQLException {
		String query = "{call sualop(?,?,?,?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		callableStatement.setString(2, l.getTenLop());
		callableStatement.setString(3, l.getMaGiangVien());
		callableStatement.setInt(4, l.getSiSoLop());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}
// xóa lớp sử dụng procedure
	public boolean XoaLop(Lop l) throws SQLException{
		String query = "{call xoalop(?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}
	// tim kiem lớp sử dụng procedure
	public Lop getLop(String searchMa)
    {
        Lop lop = new Lop();
        ResultSet rs;
        String query = "call getLopTheoMa()";
        try {
			callableStatement = conn.getConnection().prepareCall(query);
			rs = callableStatement.executeQuery();
			while(rs.next())
            {
            	lop = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
			callableStatement.close();
			conn.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return lop;
    }
	public ArrayList<Lop> timLop(String search){
		ArrayList<Lop> listLop = new ArrayList<Lop>();
      
        ResultSet rs;
        
        try{
            conn.getConnection();
            String query = "{call timlop(?)}";
            callableStatement = conn.getConnection().prepareCall(query);
			callableStatement.setString(1, search);
			rs = callableStatement.executeQuery();
            while(rs.next())
            {
            	Lop lop = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            	listLop.add(lop);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return listLop;
	}
}