package app;

import java.util.ArrayList;

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
	}
	
	/* public boolean solve()
	 * 
	 * Purpose: starts search for solution
	 * 
	 * Args: n/a
	 * 
	 * Returns: true when solution is found
	 * 			false when solution isn't found
	 * Throws: nothing
	 */
	
	public boolean solve() {
		boolean result = this.getCombination();
		if (result) return true;
		else return false;
	}
	
	private boolean getCombination() {
		for (int i = 1; i < 128; i++) {
			this.testSequence = this.createSequence(i);
			
			if (this.checkSequence()) {
				this.solution = this.testSequence;
				this.switchMap.clear();
				return true;
			}
			this.backToDefault();
		}
		
		//we've checked 127 combinations, no results have been found...
		this.switchMap.clear();
		 return false;
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
		for (int i = 0; i<data.size(); i++) {
			
			//turn on all default lights in this.lights array
			this.lights[data.get(i) - 1] = true;
		}		
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
	}
	
	private void backToDefault() {
		
		//reset all switches to false and turn off nondefault lights
		this.initializeLightsAndSwitches();
		this.turnDefaultLightsOn(switchMap.get(0));
		this.hanSoloLights();
	}
	
	private boolean checkSequence() {	
		
		for (int i = 1; i <= 7; i++) {
			if (this.testSequence[i-1]) {
				for (int x = 0; x < this.switchMap.get(i).size(); x++) {
					if (!this.checkIfSingle(this.switchMap.get(i).get(x))) {
						this.lights[this.switchMap.get(i).get(x) - 1] = !this.lights[this.switchMap.get(i).get(x) - 1];
					}
				}
			}
		}
		
		for (int i  = 0; i < 20; i++) {
			if (this.lights[i] == false) {
				this.backToDefault();
				return false;
			}
		}
		//if we get this far, that means all fields of array are set to true
		return true;
	}
	private boolean[] createSequence(int iterationNumber) {
		String num = Integer.toBinaryString(iterationNumber);
		boolean[] boolMap = new boolean[7];
		for (int i =0; i < 7; i++) boolMap[i] = false;
		int x = 0;
		for (int i = num.length() - 1; i >= 0; i--) {
			
			boolMap[x] = num.charAt(i) == '1' ? true : false;
			x++;
	}

		return boolMap;
	}
	
	private boolean checkIfSingle(int light) {
		boolean isSingle = false;
	
		if (this.soloLights.contains(light)) isSingle = true;
		
		return isSingle;
	}
}
