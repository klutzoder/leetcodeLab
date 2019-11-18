/*
 * @lc app=leetcode id=1139 lang=java
 *
 * [1139] Largest 1-Bordered Square
 */

// @lc code=start
class Solution {
	public int largest1BorderedSquare(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
			return 0;
		int m = grid.length, n = grid[0].length;
		int[][] top = new int[m][n], left = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					top[i][j] = j > 0 ? top[i][j - 1] + 1 : 1;
					left[i][j] = i > 0 ? left[i - 1][j] + 1 : 1;
				}
			}
		}

		for (int a = Math.min(m, n); a > 0; a--) {
			for (int i = m - 1; i - a + 1 >= 0; i--) {
				for (int j = n - 1; j - a + 1 >= 0; j--) {
					if (top[i][j] >= a && top[i-a+1][j] >= a && left[i][j] >= a && left[i][j-a+1] >= a)
						return a * a;
				}
			}
		}

		return 0;
	}
}
// @lc code=end
