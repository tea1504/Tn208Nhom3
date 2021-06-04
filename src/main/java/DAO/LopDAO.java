package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.CallableStatement;

import bean.DBConnection;
import bean.GiangVien;
import bean.Lop;
import bean.Phong;




public class LopDAO implements ILopDAO {
	DBConnection conn = new DBConnection();
	CallableStatement callableStatement;

	public ArrayList<Lop> getLop() {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();
		conn.getConnection();
		String query = "{call listlop ()}";;
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAO gv = new GiangVienDAO();
				temp.setGv(gv.getGiangVien(temp.getMaGV()));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}

	public ArrayList<Lop> getLopTheoMaGiangVien(String id) {
		ResultSet rs;
		ArrayList<Lop> list = new ArrayList<Lop>();
		conn.getConnection();
		String query = "select * from lop where magiangvien='" + id + "'";
		rs = conn.excuted(query);
		try {
			while (rs.next()) {
				Lop temp = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				GiangVienDAO gv = new GiangVienDAO();
				temp.setGv(gv.getGiangVien(temp.getMaGV()));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}

	public boolean ThemLop(Lop l) throws SQLException {
		String query = "{call themlopmoi (?,?,?,?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		callableStatement.setString(2, l.getTenLop());
		callableStatement.setInt(3, l.getSiSoLop());
		callableStatement.setString(4, l.getMaGV());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}

	public boolean SuaLop(Lop l) throws SQLException {
		String query = "{call sualop3(?,?,?,?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		callableStatement.setString(2, l.getTenLop());
		callableStatement.setInt(3, l.getSiSoLop());
		callableStatement.setString(4, l.getMaGV());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}

	public boolean XoaLop(Lop l) throws SQLException {
		String query = "{call xoalop4(?)}";
		callableStatement = conn.getConnection().prepareCall(query);
		callableStatement.setString(1, l.getMaLop());
		boolean r = callableStatement.execute();
		conn.closeConnection();
		return r;
	}
	
	public ArrayList<Lop> timloptheoma(String searchMa)
    {
        ArrayList<Lop> list = new ArrayList<Lop>();
      
        ResultSet rs;
        
        try{
            conn.getConnection();
            String query = "{call timkiem4 (?)}";
            callableStatement = conn.getConnection().prepareCall(query);
			callableStatement.setString(1, searchMa);
			rs = callableStatement.executeQuery();
            while(rs.next())
            {
            	Lop lop = new Lop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            	list.add(lop);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
