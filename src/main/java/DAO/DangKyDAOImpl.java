package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.DBConnection;
import bean.DangKy;

public class DangKyDAOImpl implements IDangKyDAO {
	DBConnection conn = new DBConnection();
	CallableStatement cstmt;
	@Override
	public int CreateDangKy(DangKy dangKy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DeleteDangKy(DangKy dangKy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DangKy> ListDangKy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DangKy> ListDangKyTheoGV(String maGiangVien) {
		ArrayList<DangKy> res = new ArrayList<DangKy>();
		ResultSet rs;
		String sql = "{call getDangKyTheoGV (?)}";
		try {
			cstmt = conn.getConnection().prepareCall(sql);
			cstmt.setString(1, maGiangVien);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				DangKy item = new DangKy(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
				res.add(item);
				System.out.println(item);
			}
			cstmt.close();
			conn.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
