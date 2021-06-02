package helpers;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

// Lớp cung cấp các phương thức hỗ trợ
public class DataValidator {
	
	//Kiểm tra trường nhập vào rỗng cho textfield
	public static void validateEmptyTextField(JTextField field, StringBuilder str, String errorMessage)
	{
		if(field.getText().trim().equals(""))
		{
			str.append(errorMessage).append("\n");
			field.requestFocus();
		}
	}
	
	//Kiểm tra trường nhập vào rỗng cho passwordfiel
		public static void validateEmptyTextField(JPasswordField field, StringBuilder str, String errorMessage)
		{
			String password = new String(field.getPassword());
			if(password.equals(""))
			{
				str.append(errorMessage).append("\n");
				field.requestFocus();
			}
		}
		
		
}
