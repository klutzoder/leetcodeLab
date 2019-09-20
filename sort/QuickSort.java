package sort;

import java.util.Arrays;

class QuickSort {
	public int[] quickSort(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	private void quickSort(int[] nums, int left, int right) {
		if (left < right) {
			int pivot = partition(nums, left, right);
			quickSort(nums, left, pivot - 1);
			quickSort(nums, pivot + 1, right);
		}
	}

	// 5 2 1 4 3
	private int partition(int[] nums, int low, int high) {
		int pivot = nums[high];
		int i = low; 
		for (int j = low; j < high; j++) { 
			if (nums[j] <= pivot) { 
				swap(nums, i++, j); 
			} 
		} 
		swap(nums,i, high);
		return i;
	}

	private void swap(int[] nums, int i, int j) {
		if (i == j) return;
		nums[i] ^= nums[j];
		nums[j] ^= nums[i];
		nums[i] ^= nums[j];
	}

	public static void main(String[] args) {
		int[] res = new QuickSort().quickSort(new int[] {3,4,5,1,2,0});
		System.out.println(Arrays.toString(res));
	}
}
