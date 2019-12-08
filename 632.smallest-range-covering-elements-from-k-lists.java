import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=632 lang=java
 *
 * [632] Smallest Range Covering Elements from K Lists
 *
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/description/
 *
 * algorithms
 * Hard (50.00%)
 * Likes:    827
 * Dislikes: 24
 * Total Accepted:    30.2K
 * Total Submissions: 60.3K
 * Testcase Example:  '[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]'
 *
 * You have k lists of sorted integers in ascending order. Find the smallest
 * range that includes at least one number from each of the k lists.
 * 
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c
 * if b-a == d-c.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation: 
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 <= k <= 3500
 * -10^5 <= value of elements <= 10^5.
 * 
 * 
 */

// @lc code=start
class Solution {

	private class Node {
		int val, i, j;

		public Node(int val, int i, int j) {
			this.val = val;
			this.i = i;
			this.j = j;
		}
	}

	public int[] smallestRange(List<List<Integer>> nums) {
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.size(); i++) {
			Node cur = new Node(nums.get(i).get(0), i, 0);
			pq.offer(cur);
			max = Math.max(max, cur.val);
		}

		int range = Integer.MAX_VALUE;
		int start = -1, end = -1;
		while (pq.size() == nums.size()) {
			Node cur = pq.poll();
			if (max - cur.val < range) {
				range = max - cur.val;
				start = cur.val;
				end = max;
			}

			if (cur.j + 1 < nums.get(cur.i).size()) {
				cur.j = cur.j + 1;
				cur.val = nums.get(cur.i).get(cur.j);
				pq.offer(cur);
				if (cur.val > max) {
					max = cur.val;
				}
			}
		}
		return new int[]{start, end};
	}
}
// @lc code=end
