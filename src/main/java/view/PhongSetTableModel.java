package view;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import bean.DBConnection;

/**
 * Class tạo model cho table phòng<br/>
 * Model gồm 3 cột
 * <ul>
 * 	<li>Mã phòng</li>
 * 	<li>Tên phòng</li>
 * 	<li>Số lượng máy</li>
 * </ul>
 * 
 * @author Lê Ngọc Huỳnh
 *
 */
@SuppressWarnings("serial")
public class PhongSetTableModel extends AbstractTableModel {
	private DBConnection conn = new DBConnection();
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private final String title[] = { "Mã phòng", "Tên phòng", "Số lượng máy" };

/**
 * Phương thức xây dựng kết nối với csdl<br/>
 * Dữ liệu được lấy bằng stored procedure <strong>`listPhong`()</strong>
 */
	public PhongSetTableModel() {
		try {
			conn.getConnection();
			String query = "call listPhong()";
			rs = conn.excuted(query);
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	Phương thức ngắt kết nối
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
