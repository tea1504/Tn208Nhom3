package view;

import java.awt.EventQueue;

/**
 * Class chính cho chương trình
 * @author Trần Văn Hòa
 */
public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new AppGUI();
			}
		});
	}

}
