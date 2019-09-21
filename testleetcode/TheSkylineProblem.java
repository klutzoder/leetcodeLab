package testleetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class TheSkylineProblem {
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

	public static void main(String[] args) {
		int[][] buildings = new int[][] { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
		new TheSkylineProblem().getSkyline(buildings);
	}
}