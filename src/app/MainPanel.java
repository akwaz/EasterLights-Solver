package app;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class MainPanel extends JPanel {
	private JTextField textTree;
	private JTextField textPagoda;
	private JTextField textFurnace;
	private JTextField textWaterfall;
	private JTextField textTemple;
	private JTextField textRock;
	private JTextField textPier;
	private DocumentListener textChangeListener;
	private JButton buttonGo, buttonClear;
	private JTextField textDefaultLights;
	private JTextField result;
	ArrayList<ArrayList<Integer>>allLights = new ArrayList<ArrayList<Integer>>(); // used to store all entries - index 0 stores default lights
	
	String[] namesMap = {"A: Tree", "B: Pagoda", "C: Furnace", "D: Waterfall", "E: Rock", "G: Pier", "H: Temple"}; //used to store names of buttons
	boolean[] solution = new boolean[7];//will be filled with solution by Simulation object
	
	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		textChangeListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent documentEvent) {
				validateAfterChange(documentEvent);
			}
			public void insertUpdate(DocumentEvent documentEvent) {
				validateAfterChange(documentEvent);
			}
			public void removeUpdate(DocumentEvent documentEvent) {
				validateAfterChange(documentEvent);
			}
			
		};
		textTree = new JTextField();
		textTree.setToolTipText("");
		textTree.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textTree.setBounds(103, 83, 176, 20);
		add(textTree);
		textTree.setColumns(10);
		textTree.getDocument().addDocumentListener(textChangeListener);
	
		
		textPagoda = new JTextField();
		textPagoda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textPagoda.setBounds(103, 114, 176, 20);
		add(textPagoda);
		textPagoda.setColumns(10);
		textPagoda.getDocument().addDocumentListener(textChangeListener);

		textFurnace = new JTextField();
		textFurnace.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textFurnace.setBounds(103, 145, 176, 20);
		add(textFurnace);
		textFurnace.setColumns(10);
		textFurnace.getDocument().addDocumentListener(textChangeListener);

		textWaterfall = new JTextField();
		textWaterfall.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textWaterfall.setBounds(103, 176, 176, 20);
		add(textWaterfall);
		textWaterfall.setColumns(10);
		textWaterfall.getDocument().addDocumentListener(textChangeListener);

		textTemple = new JTextField();
		textTemple.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textTemple.setBounds(103, 269, 176, 20);
		add(textTemple);
		textTemple.setColumns(10);
		textTemple.getDocument().addDocumentListener(textChangeListener);

		textRock = new JTextField();
		textRock.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textRock.setBounds(103, 207, 176, 20);
		add(textRock);
		textRock.setColumns(10);
		textRock.getDocument().addDocumentListener(textChangeListener);

		textPier = new JTextField();
		textPier.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textPier.setBounds(103, 238, 176, 20);
		add(textPier);
		textPier.setColumns(10);
		textPier.getDocument().addDocumentListener(textChangeListener);

		
		JLabel labelTree = new JLabel("A: Tree");
		labelTree.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelTree.setBounds(10, 86, 83, 14);
		add(labelTree);
		
		JLabel labelPagoda = new JLabel("B: Pagoda");
		labelPagoda.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelPagoda.setBounds(10, 117, 83, 17);
		add(labelPagoda);
		
		JLabel labelFurnace = new JLabel("C: Furnace");
		labelFurnace.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelFurnace.setBounds(10, 148, 83, 14);
		add(labelFurnace);
		
		JLabel labelWaterfall = new JLabel("D: Waterfall");
		labelWaterfall.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelWaterfall.setBounds(10, 179, 83, 14);
		add(labelWaterfall);
		
		JLabel labelRock = new JLabel("E: Rock");
		labelRock.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelRock.setBounds(10, 210, 83, 14);
		add(labelRock);
		
		JLabel labelPier = new JLabel("G: Pier");
		labelPier.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelPier.setBounds(10, 241, 83, 14);
		add(labelPier);
		
		JLabel lblHTemple = new JLabel("H: Temple");
		lblHTemple.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		lblHTemple.setBounds(10, 272, 83, 14);
		add(lblHTemple);
		
		buttonGo = new JButton("Calculate");
		buttonGo.setFont(new Font("Titillium Web", Font.PLAIN, 14));
		buttonGo.setForeground(UIManager.getColor("Button.darkShadow"));
		buttonGo.setBackground(UIManager.getColor("Button.disabledShadow"));
		buttonGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validateAllFields()) {
					collectData();
					Simulation simulation = new Simulation(allLights);
					if (simulation.solve()) {
						solution = simulation.getSolution();
						showSolution();
					}
					else {
						JOptionPane.showMessageDialog(null, "Unfortunately, no solution was found", "No results", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		buttonGo.setBounds(190, 309, 89, 23);
		add(buttonGo);
		
		buttonClear = new JButton("Clear");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textTree.setText("");
				textPagoda.setText("");
				textFurnace.setText("");
				textWaterfall.setText("");
				textTemple.setText("");
				textRock.setText("");
				textPier.setText("");
				textDefaultLights.setText("");
				result.setText("");
				
			}
		});
		buttonClear.setForeground(UIManager.getColor("Button.disabledShadow"));
		buttonClear.setFont(new Font("Titillium Web", Font.PLAIN, 14));
		buttonClear.setBackground(UIManager.getColor("Button.disabledShadow"));
		buttonClear.setBounds(10, 309, 89, 23);
		add(buttonClear);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 300, 21);
		add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mntmExit.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		mntmExit.setBackground(Color.WHITE);
		mnFile.add(mntmExit);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Created with love by Arek. Thank you for using this program!"
						+ "\r\n github.com/akwaz"
						+ "\r\n reddit.com/u/akwaz");
			}
		});
		mntmAbout.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		mntmAbout.setBackground(Color.WHITE);
		mnFile.add(mntmAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Use ; (semicolon) to separate the numbers. Top left light "
						+ "when heading north is the first one, just like in this example "
						+ "\r\n(North) \r\n[1] [2] [3] [4] [5] [6] [7] "
						+ "\r\nIf you're not sure, go to this imgur album: imgur.com/a/JHUQx - credits to Apolloxv7");
			}
		});
		mntmHelp.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		mnFile.add(mntmHelp);
		
		textDefaultLights = new JTextField();
		textDefaultLights.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textDefaultLights.setBounds(103, 32, 176, 20);
		add(textDefaultLights);
		textDefaultLights.setColumns(10);
		textDefaultLights.getDocument().addDocumentListener(textChangeListener);

		
		JLabel labelDefaultLights = new JLabel("Default lights");
		labelDefaultLights.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelDefaultLights.setBounds(10, 35, 83, 17);
		add(labelDefaultLights);
		
		JLabel labelButtonInfo = new JLabel("Button");
		labelButtonInfo.setFont(new Font("Titillium Web", Font.BOLD, 14));
		labelButtonInfo.setBounds(10, 61, 83, 20);
		add(labelButtonInfo);
		
		JLabel labelLightInfo = new JLabel("Lights controlled by this button");
		labelLightInfo.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		labelLightInfo.setBounds(103, 58, 187, 23);
		add(labelLightInfo);
		
		result = new JTextField();
		result.setFont(new Font("Open Sans", Font.PLAIN, 12));
		result.setEditable(false);
		result.setBounds(103, 355, 176, 20);
		add(result);
		result.setColumns(10);
		
		JLabel labelButtons = new JLabel("Buttons");
		labelButtons.setFont(new Font("Titillium Web", Font.PLAIN, 14));
		labelButtons.setBounds(10, 358, 83, 14);
		add(labelButtons);

	}
	
	public void setSolution (boolean[] givenSolution) {
		this.solution = givenSolution;
	}
	private boolean isAnyTextfieldEmpty() {
		if (textDefaultLights.getText().equals("") || textTree.getText().equals("") || textPagoda.getText().equals("") || textFurnace.getText().equals("") || textWaterfall.getText().equals("") || textTemple.getText().equals("") || textRock.getText().equals("") || textPier.getText().equals("")) return true;
		else return false;
		
	}
	private void validateAfterChange(DocumentEvent e) {
		String text = "";
		try {
			Document document = e.getDocument();
			text = document.getText(0, document.getLength());
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "An error occured.");	
		}
		
		String[] parts = text.split(";");
		
		if (validateNumbers(parts) && !isAnyTextfieldEmpty() && validateAllFields()) buttonGo.setEnabled(true);
		else buttonGo.setEnabled(false);
		
	}
	private boolean validateNumbers(String[] array) {
		boolean validationOK = true;
		int[] testParts = new int[30];
		
		for(int i =0; i < array.length; i++) {
			try {
				testParts[i] = Integer.parseInt(array[i]);
			} catch (NumberFormatException noNumber) {
				validationOK = false;
			}
			if (testParts[i] < 0 || testParts[i] > 20) {
				validationOK = false;
				
			}
		}
		return validationOK;
	}
	
	private boolean validateAllFields() {

		String[] partsTree = textTree.getText().split(";");
		String[] partsPagoda = textPagoda.getText().split(";");
		String[] partsFurnace = textFurnace.getText().split(";"); 
		String[] partsWaterfall = textWaterfall.getText().split(";");
		String[] partsRock = textTemple.getText().split(";");
		String[] partsPier = textRock.getText().split(";");
		String[] partsTemple = textPier.getText().split(";"); 
		String[] partsDefault = textDefaultLights.getText().split(";");
		
		if (validateNumbers(partsTree) && validateNumbers(partsPagoda) && validateNumbers(partsFurnace) && validateNumbers(partsWaterfall) && validateNumbers(partsRock) && validateNumbers(partsPier) && validateNumbers(partsTemple) && validateNumbers(partsDefault)) return true;
		else return false;
	}
	
	private void collectData() {
		String[][] parts = new String[8][];
		parts[0] = textDefaultLights.getText().split(";");
		parts[1] = textTree.getText().split(";");
		parts[2] = textPagoda.getText().split(";");
		parts[3] = textFurnace.getText().split(";"); 
		parts[4] = textWaterfall.getText().split(";");
		parts[5] = textRock.getText().split(";");
		parts[6] = textPier.getText().split(";");
		parts[7] = textTemple.getText().split(";"); 
		
		ArrayList<ArrayList<Integer>> holder = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < 8; i++) {
			for (int x = 0; x < parts[i].length; x++) {
				holder.add(new ArrayList<Integer>());
				holder.get(i).add(Integer.parseInt(parts[i][x]));
				System.out.println(parts[i][x]);
			}
			this.allLights.add(holder.get(i));

		}	
	}
	
	private String humanFriendlySolution() {
		String chain = "";
		for (int i = 0; i < 7; i++) {
			if (this.solution[i] && i != 6) chain += this.namesMap[i] + ", ";
			else if (this.solution[i]) chain += this.namesMap[i]; //only for formatting purposes
		}
		
		return chain;
	}
	private void showSolution() {
		String solution = humanFriendlySolution();
		this.result.setText(solution);
	}
}
