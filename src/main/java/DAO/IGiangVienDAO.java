package DAO;
import java.util.ArrayList;
import bean.GiangVien;
public interface IGiangVienDAO {
		int CreateGiangVien(GiangVien giangvien);
		int DeleteGiangVien(GiangVien giangvien);
		ArrayList<GiangVien> ListGiangVien();
}
