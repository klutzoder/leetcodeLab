package testleetcode;

import java.util.Arrays;

class BestTimeToBuyAndSellStockIII {

	public static int maxProfit(int[] prices) {
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
		for (int i = 0; i < dp.length; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}

		return dp[2][prices.length - 1];
	}

	public static void main(String[] args) {
		int res = maxProfit(new int[] { 3, 3, 5, 0, 0, 3, 1, 4 });
		System.out.println(res);
	}
}