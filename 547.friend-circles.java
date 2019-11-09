/*
 * @lc app=leetcode id=547 lang=java
 *
 * [547] Friend Circles
 */

// @lc code=start
class Solution {
	// dfs
    public int findCircleNum(int[][] M) {
		if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) return 0;
		int N = M.length, res = 0;
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			helper(M, i, visited);
			res++;
		}
		return res;
	}
	
	private void helper(int[][] M, int i, boolean[] visited) {
		if (visited[i]) return;
		visited[i] = true;
		for (int j = 0; j < M.length; j++) {
			if (M[i][j] == 1 && !visited[j]) helper(M, j, visited);
		}
	}


	// union find
	public int findCircleNum1(int[][] M) {
		return -1;
	}
}
// @lc code=end

