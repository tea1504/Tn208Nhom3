package view;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

import bean.DBConnection;
import bean.TaiKhoan;
import helpers.SharedData;

@SuppressWarnings("serial")
public class DangKySetTableModel extends AbstractTableModel {
	private DBConnection conn = new DBConnection();
	private ResultSet rs;
	CallableStatement cstmt;
	private ResultSetMetaData rsmd;
	private final String title[] = { "Mã đăng ký", "Tên lớp", "Tên phòng", "Ngày đăng ký", "Buổi đăng ký" };

	public DangKySetTableModel() {
		try {
			String query = "{call getModelDangKy (?, ?)}";
			cstmt = conn.getConnection().prepareCall(query);
			TaiKhoan user = SharedData.CurentAccount;
			cstmt.setString(1, user.getMaGiangVien());
			GregorianCalendar date = new GregorianCalendar();
			cstmt.setString(2, date.get(GregorianCalendar.YEAR) + "-" + (date.get(GregorianCalendar.MONTH) + 1) + "-"
					+ date.get(GregorianCalendar.DATE));
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
			if (rs.absolute(rowIndex + 1)) {
				return rs.getObject(columnIndex + 1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
//		try {
//			return rsmd.getColumnName(column + 1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		return title[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> dataType = super.getColumnClass(columnIndex);
		if (columnIndex == 3) {
			dataType = Date.class;
		}
		return dataType;
	}

}
