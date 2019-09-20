/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 */
class Solution {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;

		int[][] dp = new int[3][prices.length];
		for (int k = 1; k <= 2; k++) {
			int tmpMax = dp[k - 1][0] - prices[0];
			for (int i = 1; i < prices.length; i++) {
				dp[k][i] = Math.max(dp[k][i - 1], prices[i] + tmpMax);
				tmpMax = Math.max(tmpMax, dp[k - 1][i] - prices[i]);
			}
		}
		return dp[2][prices.length - 1];
	}
}
