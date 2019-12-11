import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=834 lang=java
 *
 * [834] Sum of Distances in Tree
 *
 * https://leetcode.com/problems/sum-of-distances-in-tree/description/
 *
 * algorithms
 * Hard (41.73%)
 * Likes:    573
 * Dislikes: 27
 * Total Accepted:    10.4K
 * Total Submissions: 25K
 * Testcase Example:  '6\n[[0,1],[0,2],[2,3],[2,4],[2,5]]'
 *
 * An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges
 * are given.
 * 
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 * 
 * Return a list ans, where ans[i] is the sum of the distances between node i
 * and all other nodes.
 * 
 * Example 1:
 * 
 * 
 * Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: 
 * Here is a diagram of the given tree:
 * ⁠ 0
 * ⁠/ \
 * 1   2
 * ⁠  /|\
 * ⁠ 3 4 5
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
 * 
 * 
 * Note: 1 <= N <= 10000
 * 
 */

// @lc code=start
class Solution {

	private List<Integer>[] graph;
	private int[] dist;
	private int[] count;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
		graph = new List[N];
		dist = new int[N];
		count = new int[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] e : edges) {
			graph[e[0]].add(e[1]);
			graph[e[1]].add(e[0]);
		}

		postOrder(0, -1);
		preOrder(0, -1);
		return dist;
	}
	
	private void postOrder(int root, int prev) {
		for (int next : graph[root]) {
			if (next == prev) continue;

			postOrder(next, root);
			dist[root] += dist[next] + count[next];
			count[root] += count[next];
		}
		count[root]++;
	}

	private void preOrder(int root, int prev) {
		for (int next : graph[root]) {
			if (next == prev) continue;
			dist[next] = dist[root] - count[next] + graph.length - count[next];
			preOrder(next, root);
		}
	}
}
// @lc code=end

