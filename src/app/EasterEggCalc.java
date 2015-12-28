package app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class EasterEggCalc extends JFrame {

	JFrame mainWindow;
	MainPanel mainPanel;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EasterEggCalc();
			}
		});			
	}
	
	EasterEggCalc() {
		mainWindow = new JFrame("EasterLights Solver");
		mainWindow.setSize(300,420 );
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new MainPanel();
		mainWindow.add(mainPanel);
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}
}
