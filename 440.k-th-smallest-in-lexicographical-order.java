/*
 * @lc app=leetcode id=440 lang=java
 *
 * [440] K-th Smallest in Lexicographical Order
 */
class Solution {
    public int findKthNumber(int n, int k) {
		int res = 1;
		k--;
		while (k > 0) {
			long left = res;
			long right = res+1;
			int count = 0;
			while (left <= n) {
				count += Math.min(right, n+1) - left;
				left *= 10;
				right *= 10;
			}

			if (count > k) {
				k--;
				res *= 10;
			} else {
				k -= count;
				res += 1;
			}
		}
		return res;     
	}
}

