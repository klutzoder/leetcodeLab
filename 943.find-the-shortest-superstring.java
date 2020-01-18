import java.util.Arrays;

/*
 * @lc app=leetcode id=943 lang=java
 *
 * [943] Find the Shortest Superstring
 *
 * https://leetcode.com/problems/find-the-shortest-superstring/description/
 *
 * algorithms
 * Hard (40.89%)
 * Likes:    245
 * Dislikes: 61
 * Total Accepted:    7.7K
 * Total Submissions: 18.8K
 * Testcase Example:  '["alex","loves","leetcode"]'
 *
 * Given an array A of strings, find any smallest string that contains each
 * string in A as a substring.
 * 
 * We may assume that no string in A is substring of another string in A.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be
 * accepted.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 12
 * 1 <= A[i].length <= 20
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public String shortestSuperstring(String[] A) {
		if (A == null || A.length == 0) return "";
		int[][] graph = buildGraph(A);
	}
	
	private int[][] buildGraph(String[] A) {
		int len = A.length;
		int[][] g = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				g[i][j] = calCost(A[i], A[j]);
			}
		}
		return g;
	}

	private int calCost(String a, String b) {
		int i = 0, j = 0;
		while (i < a.length() && a.charAt(i) != b.charAt(j)) i++;
		while (i < a.length() && j < b.length() && a.charAt(i) == b.charAt(j)) {
			i++; j++;
		}
		return b.length() - j;
	}

	public static void main(String[] args) {
		int[][] g = new Solution().buildGraph(new String[]{"bad", "ads", "dsg", "gg"});
		for (int i = 0; i < g.length; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
	}
}
// @lc code=end

