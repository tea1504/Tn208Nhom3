package view;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import bean.DBConnection;

/**
 * Lớp tạo model cho table Lớp
 * 
 * @author Trương Trung Trọng
 *
 */

@SuppressWarnings("serial")
public class LopSetTableModel extends AbstractTableModel {
	private DBConnection conn = new DBConnection();
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private final String title[] = { "Mã lớp", "Giảng viên", "Tên lớp", "Sỉ số lớp" };

	/**
	 * Hàm xây dựng kết nối với CSDL<br/>
	 * Dữ liệu hiển thị bao gồm:
	 * <ul>
	 * 	<li>Mã lớp</li>
	 * 	<li>Giảng viên</li>
	 * 	<li>Tên lớp</li>
	 * 	<li>Sỉ số lớp</li>
	 * </ul>
	 * Dữ liệu được lấy bằng stored procedure <strong>`listLop()`</strong>
	 */
	public LopSetTableModel() {
		// TODO Auto-generated constructor stub
		try {
			conn.getConnection();
			String query = "call listLop()";
			rs = conn.excuted(query);
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Đóng kết nối tới CSDL
	 */
	public void disconnect() {
		conn.closeConnection();
	}

	@Override
	public int getRowCount() {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
//		try {
//			return rsmd.getColumnName(column + 1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		return title[column];
	}

}
