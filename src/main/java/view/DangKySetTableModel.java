package view;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import bean.DBConnection;

public class DangKySetTableModel extends AbstractTableModel {
	private DBConnection conn = new DBConnection();
	private ResultSet rs;
	CallableStatement cstmt;
	private ResultSetMetaData rsmd;
	private final String title[] = { "Mã lớp", "Giảng viên", "Tên lớp", "Sỉ số lớp" };

	public DangKySetTableModel() {
		try {
			String query = "{call getModelDangKy (?)}";
			cstmt = conn.getConnection().prepareCall(query);
			cstmt.setString(1, "GV020");
			rs = cstmt.executeQuery();
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			cstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
