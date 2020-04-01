import java.util.Arrays;

/*
 * @lc app=leetcode id=1278 lang=java
 *
 * [1278] Palindrome Partitioning III
 */

// @lc code=start
class Solution {
	// leetcode 813
	public int palindromePartition(String s, int k) {
		int len = s.length();
        int[][] cost = new int[len][len];
        for (int l = 2; l <= len; l++) {
            for (int i = 0, j = l-1; j < len; i++, j++) {
                cost[i][j] = (s.charAt(i) == s.charAt(j) ? 0 : 1) + cost[i+1][j-1];
            }
        }
		int[][] dp = new int[len][k];
		for (int i = 0; i < len; i++) Arrays.fill(dp[i], len);
		for (int i = 0; i < len; i++) {
			dp[i][0] = cost[0][i];
			for (int l = 1; l < k; l++) {
				for (int j = 0; j < i; j++) {
					dp[i][l] = Math.min(dp[i][l], dp[j][l-1] + cost[j+1][i]);
				}
			}
		}
		return dp[len-1][k-1];     
	}
}
// @lc code=end

