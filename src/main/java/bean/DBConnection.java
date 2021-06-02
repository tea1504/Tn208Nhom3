package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private Connection conn;

	static final String USERNAME = "javanc";
	static final String PASSWORD = "tn208";
	static final String URL = "jdbc:mysql://localhost:3308/ql_phongmay";

	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection() {
		if (conn == null) {
			return;
		} else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet excuted(String query) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int update(String query) {
		PreparedStatement ps;
		int r;
		try {
			ps = conn.prepareStatement(query);
			r = ps.executeUpdate();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
