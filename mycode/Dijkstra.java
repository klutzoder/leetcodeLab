package mycode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

	public void solution(int[][] uvw, int N) {
		Map<Integer, List<int[]>> map = new HashMap<>();
		for (int[] c : uvw) {
			map.putIfAbsent(c[0], new ArrayList<>());
			map.putIfAbsent(c[1], new ArrayList<>());
			map.get(c[0]).add(new int[] { c[2], c[1] });
			map.get(c[1]).add(new int[] { c[2], c[0] });
		}

		int[] parents = new int[N];
		parents[0] = -1;
		boolean[] used = new boolean[N];
		// 0: distance, 1: node
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		pq.offer(new int[] { 0, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (used[cur[1]] || !map.containsKey(cur[1])) continue;
			used[cur[1]] = true;
			for (int[] next : map.get(cur[1])) {
				if (used[next[1]]) continue;
				parents[next[1]] = cur[1];
				pq.offer(new int[]{cur[0]+next[0], next[1]});
			}
		}
		System.out.println(Arrays.toString(parents));
	}

	public static void main(String[] args) {
		new Dijkstra().solution(new int[][] { { 0, 1, 5 }, { 0, 2, 1 }, { 1, 2, 2 }, { 1, 3, 1 }, { 2, 3, 4 },
				{ 2, 4, 8 }, { 3, 4, 3 }, { 3, 5, 6 } }, 6);
	}
}