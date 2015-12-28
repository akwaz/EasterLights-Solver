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
	private JTextField textRock;
	private JTextField textPier;
	private JTextField textTemple;
	private DocumentListener textChangeListener;
	private JButton buttonGo, buttonClear;
	private JTextField textDefaultLights;
	private JTextField result;
	ArrayList<ArrayList<Integer>>allLights = new ArrayList<ArrayList<Integer>>(); // used to store all entries - index 0 stores default lights
	
	boolean[] solution = new boolean[7];//will be filled with solution by Simulation object
	
	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
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
		textTree.setText("4;8;9;11;12;14;15;17");
		textTree.setToolTipText("");
		textTree.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textTree.setBounds(103, 83, 176, 20);
		add(textTree);
		textTree.setColumns(10);
		textTree.getDocument().addDocumentListener(textChangeListener);
	
		
		textPagoda = new JTextField();
		textPagoda.setText("1;2;3;10;11;16;17;19");
		textPagoda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textPagoda.setBounds(103, 114, 176, 20);
		add(textPagoda);
		textPagoda.setColumns(10);
		textPagoda.getDocument().addDocumentListener(textChangeListener);

		textFurnace = new JTextField();
		textFurnace.setText("1;6;7;9;10;11;12;14;17;20");
		textFurnace.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textFurnace.setBounds(103, 145, 176, 20);
		add(textFurnace);
		textFurnace.setColumns(10);
		textFurnace.getDocument().addDocumentListener(textChangeListener);

		textWaterfall = new JTextField();
		textWaterfall.setText("5;13;18");
		textWaterfall.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textWaterfall.setBounds(103, 176, 176, 20);
		add(textWaterfall);
		textWaterfall.setColumns(10);
		textWaterfall.getDocument().addDocumentListener(textChangeListener);

		textRock = new JTextField();
		textRock.setText("3;4;8;11;15;17;19");
		textRock.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textRock.setBounds(103, 269, 176, 20);
		add(textRock);
		textRock.setColumns(10);
		textRock.getDocument().addDocumentListener(textChangeListener);

		textPier = new JTextField();
		textPier.setText("5;6;7;11;13;17;18;20");
		textPier.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textPier.setBounds(103, 207, 176, 20);
		add(textPier);
		textPier.setColumns(10);
		textPier.getDocument().addDocumentListener(textChangeListener);

		textTemple = new JTextField();
		textTemple.setText("9;12;14");
		textTemple.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textTemple.setBounds(103, 238, 176, 20);
		add(textTemple);
		textTemple.setColumns(10);
		textTemple.getDocument().addDocumentListener(textChangeListener);

		
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
					/*simulation.findASolution();
					if (simulation.isSolutionFound) {
						solution = simulation.getSolution();
					}
					for (int x = 0; x < solution.length; x++) {
						System.out.println("Result: " + solution[x]);
					}*/
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
				textRock.setText("");
				textPier.setText("");
				textTemple.setText("");
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
				JOptionPane.showMessageDialog(null, "Created with love by akwaz. Thanks for using my software, feel free to contact me anytime - I'm on GitHub and reddit.");
			}
		});
		mntmAbout.setFont(new Font("Titillium Web", Font.PLAIN, 13));
		mntmAbout.setBackground(Color.WHITE);
		mnFile.add(mntmAbout);
		
		textDefaultLights = new JTextField();
		textDefaultLights.setText("11;17");
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
		if (textDefaultLights.getText().equals("") || textTree.getText().equals("") || textPagoda.getText().equals("") || textFurnace.getText().equals("") || textWaterfall.getText().equals("") || textRock.getText().equals("") || textPier.getText().equals("") || textTemple.getText().equals("")) return true;
		else return false;
		
	}
	private void validateAfterChange(DocumentEvent e) {
		String text = "";
		boolean validationOK = true;
		try {
			Document document = e.getDocument();
			text = document.getText(0, document.getLength());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
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
		String[] partsRock = textRock.getText().split(";");
		String[] partsPier = textPier.getText().split(";");
		String[] partsTemple = textTemple.getText().split(";"); 
		String[] partsDefault = textDefaultLights.getText().split(";");
		
		if (validateNumbers(partsTree) && validateNumbers(partsPagoda) && validateNumbers(partsFurnace) && validateNumbers(partsWaterfall) && validateNumbers(partsRock) && validateNumbers(partsPier) && validateNumbers(partsTemple) && validateNumbers(partsDefault)) return true;
		else return false;
	}
	
	private void collectData() {
		String[] partsTree = textTree.getText().split(";");
		String[] partsPagoda = textPagoda.getText().split(";");
		String[] partsFurnace = textFurnace.getText().split(";"); 
		String[] partsWaterfall = textWaterfall.getText().split(";");
		String[] partsRock = textRock.getText().split(";");
		String[] partsPier = textPier.getText().split(";");
		String[] partsTemple = textTemple.getText().split(";"); 
		String[] partsDefault = textDefaultLights.getText().split(";");

		//I'm sorry
		ArrayList<Integer> holder = new ArrayList<Integer>(); 
		ArrayList<Integer> holder1 = new ArrayList<Integer>();
		ArrayList<Integer> holder2= new ArrayList<Integer>();
		ArrayList<Integer> holder3 = new ArrayList<Integer>();
		ArrayList<Integer> holder4 = new ArrayList<Integer>();
		ArrayList<Integer> holder5 = new ArrayList<Integer>();
		ArrayList<Integer> holder6 = new ArrayList<Integer>();
		ArrayList<Integer> holder7 = new ArrayList<Integer>();//used to temporary store extracted values - will be added to main list later
		
		//I'm incredibly sorry
		for (int i = 0 ; i<partsDefault.length; i++) holder.add(Integer.parseInt(partsDefault[i]));
		this.allLights.add(holder);

		for (int i : holder) System.out.println("Default-packing: " + i);

		for (int i = 0 ; i<partsTree.length; i++) holder1.add(Integer.parseInt(partsTree[i]));
		this.allLights.add(holder1);
		for (int i : holder1) System.out.println("Tree-packing: " + i);

		
		for (int i = 0 ; i<partsPagoda.length; i++) holder2.add(Integer.parseInt(partsPagoda[i]));
		this.allLights.add(holder2);
		for (int i : holder2) System.out.println("Pagoda-packing: " + i);


		for (int i = 0 ; i<partsFurnace.length; i++) holder3.add(Integer.parseInt(partsFurnace[i]));
		this.allLights.add(holder3);

		for (int i = 0 ; i<partsWaterfall.length; i++) holder4.add(Integer.parseInt(partsWaterfall[i]));
		this.allLights.add(holder4);

		for (int i = 0 ; i<partsRock.length; i++) holder5.add(Integer.parseInt(partsRock[i]));
		this.allLights.add(holder5);

		for (int i = 0 ; i<partsPier.length; i++) holder6.add(Integer.parseInt(partsPier[i]));
		this.allLights.add(holder6);

		for (int i = 0 ; i<partsTemple.length; i++) holder7.add(Integer.parseInt(partsTemple[i]));
		this.allLights.add(holder7);


		for (ArrayList<Integer> i : allLights) {
			for (int x = 0; x< i.size(); x++) System.out.println("Przebieg " + x + ": " + i.get(x));
		}
		
	}
	
}
