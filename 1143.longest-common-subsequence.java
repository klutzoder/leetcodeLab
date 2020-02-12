/*
 * @lc app=leetcode id=1143 lang=java
 *
 * [1143] Longest Common Subsequence
 */

// @lc code=start
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;
		int len1 = text1.length(), len2 = text2.length();
		int[][] dp = new int[len1+1][len2+1];
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (text1.charAt(i) == text2.charAt(j)) {
					dp[i+1][j+1] = dp[i][j] + 1;
				} else {
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}
		return dp[len1][len2];
    }
}
// @lc code=end

