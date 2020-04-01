package design;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
	public static void main(String[] args) {
		Random r = new Random();
		int[] arr = new int[100];
		for (int i = 0;i <100; i++) {
			arr[i] = r.nextInt(100);
		}
		new QuickSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort(int[] arr) {
		if (arr == null || arr.length == 0) return;
		quickSort(arr, 0, arr.length-1);
	}

	private void quickSort(int[] arr, int l, int r) {
		if (l >= r) return;
		int par = partation(arr, l, r);
		quickSort(arr, l, par-1);
		quickSort(arr, par+1, r);
	}

	private int partation(int[] arr, int l, int r) {
		int pivot = arr[r];
		int i = l;
		for (int j = l; j < r; j++) {
			if (arr[j] < pivot) swap(arr,j, i++);
		}
		swap(arr, r, i);
		return i;
	}

	private void swap(int[] arr, int i, int j) {
		if (i == j) return;
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];	
	}
}