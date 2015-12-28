package app;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Simulation {
	public boolean isSolutionFound;
	private boolean[] lights = new boolean[20];
	private boolean[] switches = new boolean[7];
	private boolean[] solution = new boolean[7];
	private boolean[] testSequence = new boolean[7];
	private ArrayList<Integer> soloLights = new ArrayList<Integer>();
	// stores switches and lights, that each switch affects
	private ArrayList<ArrayList<Integer>> switchMap = new ArrayList<ArrayList<Integer>>(); 
	
	
	/* public constructor Simulation(ArrayList<ArrayList<Integer>>)
	 * 
	 * Purpose: instantiate a new simulation object, prepare data for calculations
	 * 
	 * Args:
	 * 		* ArrayList<ArrayList<Integer>> - obligatory, two-dimensional ArrayList. 
	 * 		  Stores, which lights are affected by each switch. Created in MainPanel.collectData()
	 * 
	 * Returns: n/a
	 * Throws: nothing
	 */
	
	public Simulation (ArrayList<ArrayList<Integer>> data) {
		this.switchMap = data;
		initializeLightsAndSwitches();
		turnDefaultLightsOn(data.get(0));
		hanSoloLights();
		System.out.println("Length: " + data.get(7).size()); //TODO TEST
		System.out.println(data.get(7));
		System.out.println(data.get(1));
		System.out.println(data.get(0));

	}
	
	
	public boolean[] getSolution() {
		return this.solution;
	}
	private void initializeLightsAndSwitches() {
		
		//fills both this.lights and this.switches arraylists with false
		for (int i = 0 ; i< 20; i++) lights[i] = false;
		
		for (int i =0; i<7;i++) switches[i] = false;
	}
	
	private void turnDefaultLightsOn(ArrayList<Integer> data) {
		for (int i : data) { //TODO test
			System.out.println("Default: " + data);
		}
		for (int i = 0; i<data.size(); i++) {
			
			//turn on all default lights in this.lights array
			this.lights[data.get(i) - 1] = true;
		}
		
		int i = 1;
		for(boolean a : lights) {System.out.println(i + ": " + a); i++;} //TODO test
		
	}
	
	private void hanSoloLights () { //ha ha
		int[] tempArray = new int[20];
		for (int i = 1; i <= 7; i++) {
			for (int x = 0; x < this.switchMap.get(i).size(); x++) {
				tempArray[this.switchMap.get(i).get(x) - 1] += 1;
			}
		}
		
		//write solo lights down
		for (int i = 1; i < 20; i++) {
			if (tempArray[i-1] == 1) this.soloLights.add(i);
		}
		
		//light them
		for (int i = 1; i <= 20; i++) {
			if (this.soloLights.contains(i)) this.lights[i-1] = true;
		}
		
		
		//TODO test
		int x=1;
		System.out.println("Solo lights: [" );
		for (int i : tempArray) { 
			System.out.println(" Light no. " + x + ": " + i + ", ");
			x++;
		}
		System.out.print("]");
		
		x = 1;
		System.out.println("Lights on: [" );
		for (boolean z : this.lights) {
			System.out.println(" Light no. " + x + ": " + z + ", ");
			x++;
		}
	}
	
	private void backToDefault() {
		
		//reset all switches to false and turn off nondefault lights
		this.initializeLightsAndSwitches();
		this.turnDefaultLightsOn(switchMap.get(0));
	}
	
	
	
	
	private boolean checkIfDefaultOrSingle(int light) {
		boolean isDefaultOrSingle = false;
		for (int i = 0; i< this.switchMap.get(0).size(); i++) {
			if (light == this.switchMap.get(0).get(i)) isDefaultOrSingle = true;
		}
		
		if (this.soloLights.contains(light)) isDefaultOrSingle = true;
		
		return isDefaultOrSingle;
	}
}