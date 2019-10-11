/*
 * @lc app=leetcode id=130 lang=java
 *
 * [130] Surrounded Regions
 */

// @lc code=start
class Solution {
    public void solve(char[][] board) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			dfs(board, i, 0);
			dfs(board, i, n-1);
		}   

		for (int j = 0; j < n; j++) {
			dfs(board, 0, j);
			dfs(board, m-1, j);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') board[i][j] = 'X';
				else if (board[i][j] == 'G') board[i][j] = 'O';
			}
		}
	}

	private void dfs(char[][] board, int i, int j) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') return;
		board[i][j] = 'G';
		dfs(board, i-1, j);
		dfs(board, i+1, j);
		dfs(board, i, j-1);
		dfs(board, i, j+1);
	}
}
// @lc code=end

