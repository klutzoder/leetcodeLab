/*
 * @lc app=leetcode id=639 lang=java
 *
 * [639] Decode Ways II
 *
 * https://leetcode.com/problems/decode-ways-ii/description/
 *
 * algorithms
 * Hard (25.73%)
 * Likes:    312
 * Dislikes: 422
 * Total Accepted:    25.5K
 * Total Submissions: 99.1K
 * Testcase Example:  '"*"'
 *
 * 
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping way:
 * 
 * 
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 
 * 
 * Beyond that, now the encoded string can also contain the character '*',
 * which can be treated as one of the numbers from 1 to 9.
 * 
 * 
 * 
 * 
 * Given the encoded message containing digits and the character '*', return
 * the total number of ways to decode it.
 * 
 * 
 * 
 * Also, since the answer may be very large, you should return the output mod
 * 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B",
 * "C", "D", "E", "F", "G", "H", "I".
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "1*"
 * Output: 9 + 9 = 18
 * 
 * 
 * 
 * Note:
 * 
 * The length of the input string will fit in range [1, 10^5].
 * The input string will only contain the character '*' and digits '0' - '9'.
 * 
 * 
 */

// @lc code=start
class Solution {
	// dp[i] = c(s[i-1]) * dp[i-1] + c(s[i-2], s[i-1]) * dp[i-2]
	// c(A): 9 if *, 0 if 0, 1 other
	// c(A, B): c(**): 15; c(*A): 2 if 0 <= A <= 6, 1 if A > 6; c(A*): 9 if A == 1,
	// g if A == 2, 0
	private final static int MOD = 1_000_000_007;

	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = cal(s.charAt(0));
		for (int i = 2; i <= n; i++) {
			long res = cal(s.charAt(i-1)) * dp[i-1] + cal(s.charAt(i-2), s.charAt(i-1));
			dp[i] = (int)(res % MOD);
		}
		return dp[n];
	}

	private int cal(char c) {
		if (c == '0')
			return 0;
		else if (c == '*')
			return 9;
		else
			return 1;
	}

	private int cal(char a, char b) {
		if (a == '*' && b == '*')
			return 15;
		if (a == '*') {
			if ('0' <= b && b <= '6')
				return 2;
			if (b > '6')
				return 1;
		}
		if (b == '*') {
			if (a == '1')
				return 9;
			if (a == '2')
				return 6;
			return 0;
		}
		return 1;
	}
}
// @lc code=end
