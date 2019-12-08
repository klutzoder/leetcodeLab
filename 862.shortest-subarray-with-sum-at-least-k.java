/*
 * @lc app=leetcode id=862 lang=java
 *
 * [862] Shortest Subarray with Sum at Least K
 *
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
 *
 * algorithms
 * Hard (22.76%)
 * Likes:    670
 * Dislikes: 19
 * Total Accepted:    18.4K
 * Total Submissions: 81K
 * Testcase Example:  '[1]\n1'
 *
 * Return the length of the shortest, non-empty, contiguous subarray of A with
 * sum at least K.
 * 
 * If there is no non-empty subarray with sum at least K, return -1.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [1], K = 1
 * Output: 1
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [1,2], K = 4
 * Output: -1
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
	public int shortestSubarray(int[] A, int K) {
		int n = A.length, res = n + 1;
		int[] B = new int[n + 1];
		for (int i = 0; i < n; i++)
			B[i + 1] = A[i] + B[i];

		Deque<Integer> d = new ArrayDeque<>();
		for (int i = 0; i < n + 1; i++) {
			while (d.size() > 0 && B[i] - B[d.getFirst()] >= K)
				res = Math.min(res, i - d.pollFirst());
			while (d.size() > 0 && B[i] <= B[d.getLast()])
				d.pollLast();
			d.addLast(i);
		}
		return res <= n ? res : -1;
	}
}
// @lc code=end
