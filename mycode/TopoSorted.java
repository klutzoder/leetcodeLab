package mycode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class TopoSorted {

	private int v; // 顶点的个数
	private LinkedList<Integer> adj[]; // 邻接表

	public TopoSorted(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int s, int t) { // s先于t，边s->t
		adj[s].add(t);
	}

	public List<Integer> topoSortByDFS() {
		Deque<Integer> stack = new ArrayDeque<>(v);
		boolean[] visited = new boolean[v];
		Arrays.fill(visited, false);
		for (int i = 0; i < v; i++) {
			if (!visited[i])
				dfs(i, visited, stack);
		}
		List<Integer> res = new ArrayList<>();
		while (!stack.isEmpty())
			res.add(stack.pop());
		return res;
	}

	private void dfs(int i, boolean[] visited, Deque<Integer> stack) {
		visited[i] = true;
		for (int w : adj[i]) {
			if (!visited[w])
				dfs(w, visited, stack);
		}
		stack.push(i);
	}

	public List<Integer> topoSortByKahn() {
		int[] inDegree = calInDegree();
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < v; ++i) {
			if (inDegree[i] == 0)
				queue.add(i);
		}
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			int i = queue.remove();
			res.add(i);
			for (int j = 0; j < adj[i].size(); ++j) {
				int k = adj[i].get(j);
				inDegree[k]--;
				if (inDegree[k] == 0)
					queue.add(k);
			}
		}
		return res;
	}

	public List<List<Integer>> findAllTopoSort() {
		List<List<Integer>> res = new ArrayList<>();
		int[] inDegree = calInDegree();
		boolean[] visited = new boolean[v];
		findAllTopoLogicalOrders(res, new ArrayList<>(), inDegree, visited);

		return res;
	}

	private void findAllTopoLogicalOrders(List<List<Integer>> res, List<Integer> temp, int[] inDegree,
			boolean[] visited) {
		for (int u = 0; u < v; u++) {
			if (inDegree[u] != 0 || visited[u])
				continue;
			for (int w : adj[u])
				inDegree[w]--;
			temp.add(u);
			visited[u] = true;
			findAllTopoLogicalOrders(res, temp, inDegree, visited);
			for (int w : adj[u])
				inDegree[w]++;
			temp.remove(temp.size() - 1);
			visited[u] = false;
		}
		if (temp.size() == v) {
			res.add(new ArrayList<>(temp));
		}

	}

	private int[] calInDegree() {
		int[] inDegree = new int[v]; // 统计每个顶点的入度
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < adj[i].size(); ++j) {
				int w = adj[i].get(j); // i->w
				inDegree[w]++;
			}
		}
		return inDegree;
	}

	public static void main(String[] args) {
		TopoSorted g = new TopoSorted(6);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(5, 2);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		List<List<Integer>> res = g.findAllTopoSort();
		for (List<Integer> cur : res) {
			System.out.println(Arrays.toString(cur.toArray()));
		}

	}

}