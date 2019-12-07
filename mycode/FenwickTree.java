package mycode;

import java.util.Arrays;

public class FenwickTree {

	private int[] sums;

	private int lowBit(int x) {
		return x & (-x);
	}

	public FenwickTree(int n) {
		sums = new int[n + 1];
	}

	public void update(int i, int val) {
		while (i < sums.length) {
			sums[i] += val;
			i += lowBit(i);
		}
	}

	public int query(int i) {
		int sum = 0;
		while (i > 0) {
			sum += sums[i];
			i -= lowBit(i);
		}
		return sum;
	}

	public static void main(String[] args) {
		FenwickTree ft = new FenwickTree(8);
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		for (int i = 0; i < nums.length;i++) {
			ft.update(i+1, nums[i]);
		}
	}
}
