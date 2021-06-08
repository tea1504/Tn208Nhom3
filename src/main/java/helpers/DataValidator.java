package helpers;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Lớp cung cấp các phương thức hỗ trợ
 * 
 * @author Nguyễn Ngọc Trâm
 *
 */
public class DataValidator {

	/**
	 * Kiểm tra trường nhập vào rỗng cho textfield
	 * 
	 * @param field        {@link JTextField}
	 * @param str          {@link StringBuilder}
	 * @param errorMessage {@link String}
	 */
	public static void validateEmptyTextField(JTextField field, StringBuilder str, String errorMessage) {
		if (field.getText().trim().equals("")) {
			str.append(errorMessage).append("\n");
			field.requestFocus();
		}
	}

	/**
	 * Kiểm tra trường nhập vào rỗng cho passwordfield
	 * 
	 * @param field        {@link JPasswordField}
	 * @param str          {@link StringBuilder}
	 * @param errorMessage {@link String}
	 */
	public static void validateEmptyPassWordField(JPasswordField field, StringBuilder str, String errorMessage) {
		String password = new String(field.getPassword());
		if (password.equals("")) {
			str.append(errorMessage).append("\n");
			field.requestFocus();
		}
	}

	/**
	 * Kiểm tra độ dài chuỗi nhập vào cho textfield
	 * 
	 * @param field        {@link JTextField}
	 * @param length       {@link Integer}
	 * @param str          {@link StringBuilder}
	 * @param errorMessage {@link String}
	 */
	public static void validateTextFieldLength(JTextField field, int length, StringBuilder str, String errorMessage) {
		if (field.getText().length() > length) {
			str.append(errorMessage).append("\n");
			field.requestFocus();
		}
	}

	/**
	 * Kiểm tra độ dài chuỗi nhập vào cho password field
	 * 
	 * @param field        {@link JPasswordField}
	 * @param length       {@link Integer}
	 * @param str          {@link StringBuilder}
	 * @param errorMessage {@link String}
	 */
	public static void validatePassWordFieldLength(JPasswordField field, int length, StringBuilder str,
			String errorMessage) {
		String password = new String(field.getPassword());
		if (password.length() > length) {
			str.append(errorMessage).append("\n");
			field.requestFocus();
		}
	}

}
