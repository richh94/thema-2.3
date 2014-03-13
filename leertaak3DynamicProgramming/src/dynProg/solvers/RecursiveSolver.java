package dynProg.solvers;

import dynProg.Solver;

public class RecursiveSolver implements Solver
{
	public RecursiveSolver() {}

	@Override
	public boolean solve(int[] numbers, int sum) {
		// Er is tenminste één base case
		if (numbers.length == 0)	return false;

		try {
			for (int i = 0; i < numbers.length; i++) {
				int currentNumber = numbers[i];
				int sumB = currentNumber;

				// j = i because we already had all numbers below equal i
				for (int j = i + 1; j < numbers.length; j++) {
					sumB += numbers[j];

					if (sumB == sum) 					return true;
					if (sumB - numbers[j - 1] == sum)	return true;
				}
			}
		}
		catch (Exception e)	{
			System.err.println(e.getMessage());
		}
		
		return false;
	}
}
