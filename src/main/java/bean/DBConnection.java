package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Lớp dùng để kết nối tới CSDL
 * @author Giáo trình slide 27
 *
 */
public class DBConnection {
	private Connection conn;
	static final String PASSWORD = "tn208";
	static final String USERNAME = "javanc";
	static final String URL = "jdbc:mysql://localhost:3308/ql_phongmay";

	/**
	 * Hàm khởi tạo lớp kết nối CSDL
	 * @author Giáo trình slide 27
	 */
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hàm dùng để lấy kết nối để tạo kết nối đến CSDL
	 * 
	 * @return conn - Một kết nối
	 * @author Giáo trình slide 27
	 */
	public Connection getConnection() {
		try {
			conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Đóng một kết nối
	 * @author Giáo trình slide 27
	 */
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
}
