package bean;

/**
 * Class dùng để lưu dữ liệu phòng. Bao gồm:
 * <ul>
 * <li>Mã phòng - <strong>String</strong></li>
 * <li>Tên phòng - <strong>String</strong></li>
 * <li>Số lượng máy - <strong>int</strong></li>
 * </ul>
 * 
 * @author Lê Ngọc Huỳnh
 *
 */
public class Phong {
	private String MaPhong;
	private String TenPhong;
	private int SoLuongMay;

	/**
	 * Hàm xây dựng 3 đối số
	 * 
	 * @param maPhong  String
	 * @param tenPhong String
	 * @param soMay    int
	 */
	public Phong(String maPhong, String tenPhong, int soMay) {
		super();
		MaPhong = maPhong;
		TenPhong = tenPhong;
		SoLuongMay = soMay;
	}

	/**
	 * Hàm xây dựng 0 đối số
	 */
	public Phong() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Phương thức lấy mã phòng
	 * 
	 * @return Mã phòng <strong>String</strong>
	 */
	public String getMaPhong() {
		return MaPhong;
	}

	/**
	 * Phương thức gán mã phòng
	 * 
	 * @param maPhong String
	 */
	public void setMaPhong(String maPhong) {
		MaPhong = maPhong;
	}

	/**
	 * Phương thức lấy tên phòng
	 * 
	 * @return tên phòng <strong>String</strong>
	 */
	public String getTenPhong() {
		return TenPhong;
	}

	/**
	 * Phương thức gán tên phòng
	 * 
	 * @param tenPhong String
	 */
	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}

	/**
	 * Phương thức lấy số lượng máy
	 * 
	 * @return số lượng máy <strong>int</strong>
	 */
	public int getSoLuongMay() {
		return SoLuongMay;
	}

	/**
	 * Phương thức gán số lượng máy
	 * 
	 * @param soMay int
	 */
	public void setSoMay(int soMay) {
		SoLuongMay = soMay;
	}

	public String toString() {
		return getTenPhong();
	}
}
