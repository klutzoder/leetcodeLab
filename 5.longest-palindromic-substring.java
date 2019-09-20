/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */
class Solution {
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return "";
		int len = s.length();
		boolean[][] dp = new boolean[len][len];
		dp[0][0] = true;
		String res = "";

		for (int j = 0; j < len; j++) {
			for (int i = 0; i <= j; i++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
				if (dp[i][j] && j - i + 1 > res.length()) {
					res = s.substring(i, j + 1);
				}
			}
		}
		return res;
	}
}
