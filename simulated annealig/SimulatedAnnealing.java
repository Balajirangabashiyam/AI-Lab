// Java program to implement Simulated Annealing
import java.util.*;

public class SimulatedAnnealing {

	// Initial and final temperature
	public static double T = 1;

	// Simulated Annealing parameters

	// Temperature at which iteration terminates
	static final double Tmin = .0001;

	// Decrease in temperature
	static final double alpha = 0.9;

	// Number of iterations of annealing
	// before decreasing temperature
	static final int numIterations = 100;

	// Locational parameters

	// Target array is discretized as M*N grid
	static final int M = 5, N = 5;

	// Number of objects desired
	static final int k = 5;


	public static void main(String[] args) {

		// Problem: place k objects in an MxN target
		// plane yielding minimal cost according to
		// defined objective function

		// Set of all possible candidate locations
		String[][] sourceArray = new String[M][N];

		// Global minimum
		Solution min = new Solution(Double.MAX_VALUE, null);

		// Generates random initial candidate solution
		// before annealing process
		Solution currentSol = genRandSol();

		// Continues annealing until reaching minimum
		// temperature
		while (T > Tmin) {
			for (int i=0;i<numIterations;i++){

				// Reassigns global minimum accordingly
				if (currentSol.CVRMSE < min.CVRMSE){
					min = currentSol;
				}

				Solution newSol = neighbor(currentSol);
				double ap = Math.pow(Math.E,
					(currentSol.CVRMSE - newSol.CVRMSE)/T);
				if (ap > Math.random())
					currentSol = newSol;
			}

			T *= alpha; // Decreases T, cooling phase
		}

		//Returns minimum value based on optimization
		System.out.println(min.CVRMSE+"\n\n");

		for(String[] row:sourceArray) Arrays.fill(row, "X");

		// Displays
		for (int object:min.config) {
			int[] coord = indexToPoints(object);
			sourceArray[coord[0]][coord[1]] = "-";
		}

		// Displays optimal location
		for (String[] row:sourceArray)
			System.out.println(Arrays.toString(row));

	}

	// Given current configuration, returns "neighboring"
	// configuration (i.e. very similar)
	// integer of k points each in range [0, n)
	/* Different neighbor selection strategies:
		* Move all points 0 or 1 units in a random direction
		* Shift input elements randomly
		* Swap random elements in input sequence
		* Permute input sequence
		* Partition input sequence into a random number
		of segments and permute segments */
	public static Solution neighbor(Solution currentSol){

		// Slight perturbation to the current solution
		// to avoid getting stuck in local minimas

		// Returning for the sake of compilation
		return currentSol;

	}

	// Generates random solution via modified Fisher-Yates
	// shuffle for first k elements
	// Pseudorandomly selects k integers from the interval
	// [0, n-1]
	public static Solution genRandSol(){

		// Instantiating for the sake of compilation
		int[] a = {1, 2, 3, 4, 5};

		// Returning for the sake of compilation
		return new Solution(-1, a);
	}


	// Complexity is O(M*N*k), asymptotically tight
	public static double cost(int[] inputConfiguration){

		// Given specific configuration, return object
		// solution with assigned cost
		return -1; //Returning for the sake of compilation
	}

	// Mapping from [0, M*N] --> [0,M]x[0,N]
	public static int[] indexToPoints(int index){
		int[] points = {index%M, index/M};
		return points;
	}

	// Class solution, bundling configuration with error
	static class Solution {

		// function value of instance of solution;
		// using coefficient of variance root mean
		// squared error
		public double CVRMSE;

		public int[] config; // Configuration array
		public Solution(double CVRMSE, int[] configuration) {
			this.CVRMSE = CVRMSE;
			config = configuration;
		}
	}
}
