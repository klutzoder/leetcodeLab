import java.util.Arrays;

/*
 * @lc app=leetcode id=1278 lang=java
 *
 * [1278] Palindrome Partitioning III
 */

// @lc code=start
class Solution1 {
	// leetcode 813
    public int palindromePartition(String s, int k) {
		int len = s.length();
		int[][] dp = new int[len][k];
		for (int i = 0; i < len; i++) Arrays.fill(dp[i], len);
		for (int i = 0; i < len; i++) {
			dp[i][0] = minCost(s, 0, i);
			for (int l = 1; l < k; l++) {
				for (int j = 0; j < i; j++) {
					dp[i][l] = Math.min(dp[i][l], dp[j][l-1] + minCost(s, j+1, i));
				}
			}
		}
		return dp[len-1][k-1];     
	}
	
	private int minCost(String s, int i, int j) {
		int res = 0;
		while (i < j) {
			if (s.charAt(i++) != s.charAt(j--)) res++;
		}
		return res;
	}

	public static void main(String[] args) {
		new Solution1().palindromePartition("aabbc", 3);
	}
}
// @lc code=end

