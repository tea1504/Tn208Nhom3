package DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.Phong;

public class PhongDAO implements IPhongMayDAO{
	DBConnection conn = new DBConnection();
	CallableStatement cs;
	public int ThemPhong(Phong p) {
		String query = "insert into phongmay (maphong, tenphong, somay) values ('" + p.getMaPhong() + "', '"
				+ p.getTenPhong() + "'," + p.getSoLuongMay() + ")";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}

	public int SuaPhong(Phong p) {
		String query = "update phongmay set tenphong='" + p.getTenPhong() + "', somay="
				+ p.getSoLuongMay() + " where maphong='" + p.getMaPhong() + "'";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}

	public int XoaPhong(Phong p) {
		String query = "delete from phongmay where maphong='" + p.getMaPhong() + "'";
		conn.getConnection();
		int r = conn.update(query);
		conn.closeConnection();
		return r;
	}
	
	public ArrayList<Phong> TimPhong(String searchText)
    {
        ArrayList<Phong> phongList = new ArrayList<Phong>();
      
        ResultSet rs;
        
        try{
            conn.getConnection();
            String query = "{call timKiem (?)}";
//            String query = "SELECT * FROM `phongmay` WHERE CONCAT(`maphong`, `tenphong`, `somay`) LIKE '%" + searchText + "%'";
            cs = conn.getConnection().prepareCall(query);
			cs.setString(1, searchText);
			rs = cs.executeQuery();
           
            Phong phong;
          
            while(rs.next())
            {
            	phong = new Phong(
                                 rs.getString("maphong"),
                                 rs.getString("tenphong"),
                                 rs.getInt("somay")
                                );
            	phongList.add(phong);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return phongList;
    }

	public ArrayList<Phong> ListPhong() {
		ArrayList<Phong> list = new ArrayList<Phong>();
		String query = "{call listPhong ()}";
		conn.getConnection();
		ResultSet res = conn.excuted(query);
		try {
			while(res.next()) {
				list.add(new Phong(res.getString(1), res.getString(2), res.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnection();
		return list;
	}

	public ArrayList<Phong> getPhongChuaDangKy(Date ngaydangky, int buoidangky) {
		ArrayList<Phong> res = new ArrayList<Phong>();
		ResultSet rs;
		String query = "{call getPhongChuaDangKy (?,?)}";
		try {
			cs = conn.getConnection().prepareCall(query);
			cs.setDate(1, ngaydangky);
			cs.setInt(2, buoidangky);
			rs = cs.executeQuery();
			while(rs.next()) {
				Phong item = new Phong(rs.getString(1), rs.getString(2), rs.getInt(3));
				res.add(item);
			}
			cs.close();
			conn.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
}
