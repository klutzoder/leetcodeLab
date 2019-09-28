/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 */
class Solution {
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int[] tail = new int[nums.length];
		int size = 0;
		for (int num : nums) {
			int i = 0, j = size;
			while (i + 1 < j) {
				int mid = (j - i) / 2 + i;
				if (tail[mid] < num)
					i = mid;
				else
					j = mid;
			}
			tail[j] = num;
			if (i == size)
				size++;
		}
		return size;
	}
}
