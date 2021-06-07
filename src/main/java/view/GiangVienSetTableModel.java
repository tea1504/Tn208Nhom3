package view;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import bean.DBConnection;

@SuppressWarnings("serial")
public class GiangVienSetTableModel extends AbstractTableModel {
	private DBConnection conn = new DBConnection();
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private final String title[] = {"Mã giảng viên", "Tên giảng viên", "Quyền sử dụng"};
	
	public GiangVienSetTableModel() {
		try {
			conn.getConnection();
			String query = "call getModelGiangVien()";
			rs = conn.excuted(query);
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		conn.closeConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			if (rs.absolute(rowIndex + 1))
				return rs.getObject(columnIndex + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return title[column];
	}

}
