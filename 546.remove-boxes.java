/*
 * @lc app=leetcode id=546 lang=java
 *
 * [546] Remove Boxes
 *
 * https://leetcode.com/problems/remove-boxes/description/
 *
 * algorithms
 * Hard (39.90%)
 * Likes:    454
 * Dislikes: 41
 * Total Accepted:    11.5K
 * Total Submissions: 28.8K
 * Testcase Example:  '[1,3,2,2,2,3,4,3,1]'
 *
 * Given several boxes with different colors represented by different positive
 * numbers. 
 * You may experience several rounds to remove boxes until there is no box
 * left. Each time you can choose some continuous boxes with the same color
 * (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 * 
 * 
 * Example 1:
 * Input: 
 * 
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * 
 * Output:
 * 
 * 23
 * 
 * Explanation: 
 * 
 * [1, 3, 2, 2, 2, 3, 4, 3, 1] 
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
 * ----> [1, 3, 3, 3, 1] (1*1=1 points) 
 * ----> [1, 1] (3*3=9 points) 
 * ----> [] (2*2=4 points)
 * 
 * 
 * 
 * Note:
 * The number of boxes n would not exceed 100.
 * 
 * 
 */

// @lc code=start
class Solution {

	private int[][][] m;

	public int removeBoxes(int[] boxes) {
		int n = boxes.length;
		m = new int[n][n][n];
		return dfs(boxes, 0, n - 1, 0);
	}

	private int dfs(int[] boxes, int l, int r, int k) {
		if (l > r)
			return 0;
		if (m[l][r][k] > 0)
			return m[l][r][k];
		m[l][r][k] = dfs(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
		for (int i = l; i < r; i++) {
			if (boxes[i] == boxes[r]) {
				m[l][r][k] = Math.max(m[l][r][k], dfs(boxes, l, i, k + 1) + dfs(boxes, i + 1, r - 1, 0));
			}
		}
		return m[l][r][k];
	}

}
// @lc code=end
