package view;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import DAO.TaiKhoanDAOImpl;
import bean.DBConnection;


@SuppressWarnings("serial")
public class TaiKhoanSetTableModel  extends AbstractTableModel {
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private final String title[] = {"Mã giảng viên", "Tên giảng viên", "Quyền sử dụng"};
	
	public TaiKhoanSetTableModel() {
		TaiKhoanDAOImpl tkDAO = new TaiKhoanDAOImpl();
		try
		{
			//Lấy dữ liệu để hiển thị lên bảng
			rs = (ResultSet) tkDAO.taiKhoanGetTableModel();
			rsmd = rs.getMetaData();	
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public TaiKhoanSetTableModel(String maGiangVien) {
		TaiKhoanDAOImpl tkDAO = new TaiKhoanDAOImpl();
		try
		{
			//Lấy dữ liệu để hiển thị lên bảng
			rs = (ResultSet) tkDAO.taiKhoanGetTableModel(maGiangVien);
			rsmd = rs.getMetaData();	
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public int getRowCount() {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			if (rs.absolute(rowIndex + 1))
				return rs.getObject(columnIndex + 1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return title[column];
	}
	
	public void disconnect() throws SQLException {
		if(rs != null)
			rs.close();
	}

}
