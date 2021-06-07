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
	
//	Phương thức thêm phòng
	public boolean ThemPhong(Phong p) throws SQLException {
		String query = "{call themPhong (?,?,?)}";
		cs = conn.getConnection().prepareCall(query);
		cs.setString(1, p.getMaPhong());
		cs.setString(2, p.getTenPhong());
		cs.setInt(3, p.getSoLuongMay());
		boolean r = cs.execute();
		conn.closeConnection();
		return r;
	}

//	Phương thức sửa phòng
	public boolean SuaPhong(Phong p) throws SQLException {
		String query = "{call suaPhong (?,?,?)}";
		cs = conn.getConnection().prepareCall(query);
		cs.setString(1, p.getMaPhong());
		cs.setString(2, p.getTenPhong());
		cs.setInt(3, p.getSoLuongMay());
		boolean r = cs.execute();
		conn.closeConnection();
		return r;
	}

//	Phương thức xóa phòng
	public boolean XoaPhong(Phong p) throws SQLException {
		String query = "{call xoaPhong (?)}";
		cs = conn.getConnection().prepareCall(query);
		cs.setString(1, p.getMaPhong());
		boolean r = cs.execute();
		conn.closeConnection();
		return r;
	}
	
//	Phương thức tìm phòng
	public ArrayList<Phong> TimPhong(String searchText)
    {
        ArrayList<Phong> listPhong = new ArrayList<Phong>();
      
        ResultSet rs;
        
        try{
            conn.getConnection();
            String query = "{call timPhong (?)}";
            cs = conn.getConnection().prepareCall(query);
			cs.setString(1, searchText);
			rs = cs.executeQuery();
           
          
            while(rs.next())
            {
            	Phong phong = new Phong(
                                 rs.getString("maphong"),
                                 rs.getString("tenphong"),
                                 rs.getInt("somay")
                                );
            	listPhong.add(phong);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
//      Trả về ArrayList danh sách phòng tìm được
        return listPhong;
    }

//	Phương thức lấy tất cả các phòng
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
//      Trả về ArrayList danh sách phòng
		return list;
	}

//	Phương thức lấy phòng chưa đăng ký
	public ArrayList<Phong> getPhongChuaDangKy(String ngaydangky, int buoidangky, int siso) {
		ArrayList<Phong> res = new ArrayList<Phong>();
		ResultSet rs;
		String query = "{call getPhongChuaDangKy (?,?, ?)}";
		try {
			cs = conn.getConnection().prepareCall(query);
			cs.setString(1, ngaydangky);
			cs.setInt(2, buoidangky);
			cs.setInt(3, siso);
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
//      Trả về ArrayList danh sách phòng chưa đăng ký
		return res;
	}
	
}
