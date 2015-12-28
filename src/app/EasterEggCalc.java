package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		mainWindow = new JFrame("EasterLights Calculator");
		mainWindow.setSize(300,420 );
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new MainPanel();
		mainWindow.add(mainPanel);
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}
}
