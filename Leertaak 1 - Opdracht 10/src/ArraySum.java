
public class ArraySum {
	private int[] array_one = {1, 2, 3, 10};
	private int[] array_two = {4, 5, 6, 24};
	
	public static void main(String[] args) {
		ArraySum as = new ArraySum();
		
		as.sum_arrays();
	}
	
	public void sum_arrays () {
		if (array_one.length != array_two.length) {
			throw new ArraySizeException("Array 1 size: " + array_one.length + " & Array 2 size: " + array_two.length);
		}
		
		int[] result_array = new int[array_one.length];

		for (int i = 0; i < array_one.length; i++) {
			result_array[i] = array_one[i] + array_two[i];
		}
		
		print_array(result_array);
	}
	
	public void print_array (int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
