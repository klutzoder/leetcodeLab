/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 */
class Solution {
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
			return 0;
		int n = matrix.length;
		int m = matrix[0].length;
		int[][] dp = new int[n + 1][m + 1];
		int max = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
					if (dp[i][j] > max)
						max = dp[i][j];
				}
			}
		}
		return max * max;
	}
}
