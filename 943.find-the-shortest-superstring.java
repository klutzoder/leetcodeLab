/*
 * @lc app=leetcode id=943 lang=java
 *
 * 
 */

// @lc code=start
class Solution {
    public String shortestSuperstring(String[] A) {
		if (A == null || A.length == 0) return "";
		int[][] graph = buildGraph(A);
		int len = A.length, sLen = 1 << len;
		int[][] dp = new int[sLen][len];
		int[][] parent = new int[sLen][len];
		for (int i = 0; i < sLen; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/2);
			Arrays.fill(parent[i], -1);
		}
		
		for (int i = 0; i < len; i++) dp[1 << i][i] = A[i].length();
		// System.out.println("start calcuate");
		for (int s = 1; s < sLen; s++) {
			// System.out.printf("%d calcuate\n", s);
			for (int i = 0; i < len; i++) { // end point
				if ((s & (1<<i)) == 0) continue; 
				int prev = s - (1<<i);
				for (int j = 0; j < len; j++) {
					if (dp[prev][j] + graph[j][i] < dp[s][i]) {
						dp[s][i] = dp[prev][j] + graph[j][i];
						parent[s][i] = j;
					}
				}
			}
		}
		int cur = -1, minLen = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			if (dp[sLen-1][i] < minLen) {
				minLen = dp[sLen-1][i];
				cur = i;
			}
		}

		int s = sLen - 1;
		String ans = "";
		while (s != 0) {
			// System.out.printf("s: %d, cur: %d\n", s, cur);
			int prev = parent[s][cur];
			if (prev < 0) ans = A[cur] + ans;
			else ans = A[cur].substring(A[cur].length() - graph[prev][cur]) + ans;
			s &= ~(1 << cur);
			cur = prev;
		}

		return ans;
	}
	
	private int[][] buildGraph(String[] A) {
		int len = A.length;
		int[][] g = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				g[i][j] = calCost(A[i], A[j]);
			}
		}
		return g;
	}

	private int calCost(String a, String b) {
		int len = Math.min(a.length(), b.length());
		for (int i = 1; i < len; i++) {
			if (a.substring(a.length()-i) == b.substring(0, i)) {
				return a.length() - i;
			}
		}
		return b.length();
	}
}
// @lc code=end

