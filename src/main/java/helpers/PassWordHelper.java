package helpers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JPasswordField;

public class PassWordHelper {

	// Phương thức mã hóa mật khẩu

	// Mã hóa 1 chiều với MD5 có độ bảo mật cao
	public static String converMD5(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public static String getPassWordField(JPasswordField pwf)
	{
		if(pwf != null)
			return new String(pwf.getPassword());
		
		return "";
	}

}
