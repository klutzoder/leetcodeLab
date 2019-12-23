import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=301 lang=java
 *
 * [301] Remove Invalid Parentheses
 *
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 *
 * algorithms
 * Hard (41.07%)
 * Likes:    1930
 * Dislikes: 82
 * Total Accepted:    163.8K
 * Total Submissions: 398K
 * Testcase Example:  '"()())()"'
 *
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and
 * ).
 * 
 * Example 1:
 * 
 * 
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: ")("
 * Output: [""]
 * 
 */

// @lc code=start
class Solution {

	private void dfs(String s, int start, int l, int r, List<String> res) {
		if (l == 0 && r == 0) {
			if (isValid(s)) res.add(s);
			return;
		}

		for (int i = start; i < s.length(); i++) {
			if (i != start && s.charAt(i) == s.charAt(i-1)) continue;
			if (s.charAt(i) == '(' || s.charAt(i) == ')') {
				String cur = s.substring(0, i) + s.substring(i+1);
				if (r > 0 && s.charAt(i) == ')') 
				  dfs(cur, i, l, r - 1, res);
				else if (l > 0 && s.charAt(i) == '(')
				  dfs(cur, i, l - 1, r, res);
			  }
		}
	}


	private boolean isValid(String s) {
		int count = 0;
    	for (char ch : s.toCharArray()) {
      		if (ch == '(') ++count;
      		if (ch == ')') --count;
      		if (count < 0) return false;
    	}
    	return count == 0;
	}

	public List<String> removeInvalidParentheses(String s) {

		int left = 0, right = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else if (s.charAt(i) == ')') {
				if (left == 0) right++;
				else left--;
			}
		}
		List<String> res = new ArrayList<>();
		dfs(s, 0, left, right, res);
		return res;
	}
}

// @lc code=end
