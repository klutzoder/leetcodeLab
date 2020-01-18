/*
 * @lc app=leetcode id=679 lang=java
 *
 * [679] 24 Game
 */

// @lc code=start
class AndroidUnlockPatterns {
    
    public int numberOfPatterns(int m, int n) {
        int[][] graph = new int[10][10];
        graph[1][3] = graph[3][1] = 2;
        graph[4][6] = graph[6][4] = 5;
        graph[7][9] = graph[9][7] = 8;
        graph[1][7] = graph[7][1] = 4;
        graph[2][8] = graph[8][2] = 5;
        graph[3][9] = graph[9][3] = 6;
        graph[1][9] = graph[9][1] = graph[3][7] = graph[7][3] = 5;
        boolean[] visited = new boolean[10];
        int res = 0;
        res += helper(1, 1, 0, m, n, graph, visited) * 4;
        res += helper(2, 1, 0, m, n, graph, visited) * 4;
        res += helper(5, 1, 0, m, n, graph, visited);
        return res;
    }
    
    private int helper(int num, int len, int res, int m, int n, int[][] graph, boolean[] visited) {
        if (len >= m) ++res;
        ++len;
        if (len > n) return res;
        visited[num] = true;
        for (int next = 1; next <= 9; ++next) {
            int g = graph[num][next];
            if (!visited[next] && (g == 0 || visited[g])) {
                res = helper(next, len, res, m, n, graph, visited);
            }
        }
        visited[num] = false;
        return res;
	}
	
	public static void main(String[] args) {
		new AndroidUnlockPatterns().numberOfPatterns(2, 2); 
	}
}
// @lc code=end
