import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 */
class Solution {
	public List<List<Integer>> getSkyline(int[][] buildings) {
		List<List<Integer>> res = new ArrayList<>();
		List<int[]> heights = new ArrayList<>();
		for (int[] build : buildings) {
			heights.add(new int[] { build[0], -build[2] }); // left
			heights.add(new int[] { build[1], build[2] }); // right
		}
		Collections.sort(heights, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
		queue.offer(0);
		int prev = 0;
		for (int[] height : heights) {
			if (height[1] < 0) {
				queue.offer(-height[1]);
			} else {
				queue.remove(height[1]);
			}
			int cur = queue.peek();
			if (prev != cur) {
				res.add(Arrays.asList(height[0], cur));
				prev = cur;
			}
		}
		return res;
	}
}
