package dynProg.solvers;

import dynProg.Solver;

public class TopDownSolver implements Solver
{
	private int[][] matrix;

	public TopDownSolver() {}

	@Override
	public boolean solve(int[] numbers, int sum) {
		try {
			int tmpSum = 0;
			for (int i = 0; i < numbers.length; i++) {
				if (numbers[i] >= 0) {
					tmpSum += numbers[i];
				}
			}
			matrix = new int[numbers.length][tmpSum];

			for (int i = 0; i < numbers.length; i++){
				int sumB = 0;
				for (int j = 0; j <= i; j++) {
					// Place current
					matrix[i][numbers[j] - 1] = 1;

					// do SumB
					sumB += numbers[j];
					matrix[i][sumB - 1] = 1;

					// do SumB - previous
					if (j > 0) {
						tmpSum = sumB - numbers[j - 1];
						matrix[i][tmpSum - 1] = 1;
					}
				}
			}
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
		return isSolved(sum);
	}

	private boolean isSolved(int sum) {
		if (sum <= matrix[0].length) {
			for (int i = matrix.length - 1; i >= 0; i--){
				if (matrix[i][sum - 1] != 0) return true;
			}
		}
		return false;
	}
}
