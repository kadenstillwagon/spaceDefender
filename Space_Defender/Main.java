package Space_Defender;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		Controls ship= new Controls();
		window.setBounds(10,10,500, 700);
		window.setTitle("Space Defender");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(ship);
		
		window.setVisible(true);

	}

}
