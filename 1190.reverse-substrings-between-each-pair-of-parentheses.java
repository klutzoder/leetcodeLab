import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=1190 lang=java
 *
 * [1190] Reverse Substrings Between Each Pair of Parentheses
 */

// @lc code=start
class Solution {
    public String reverseParentheses(String s) {
		if (s == null || s.length() == 0) return s;
		char[] chs = s.toCharArray();
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == '(') stack.push(i);
			else if (chs[i] == ')') {
				int start = stack.pop();
				reverse(chs, start+1, i-1);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (char c : chs) {
			if (c == '(' || c == ')') continue;
			sb.append(c);
		}
		return sb.toString();
	}

	private void reverse(char[] chs, int i, int j) {
		while (i < j) {
			chs[i] ^= chs[j];
			chs[j] ^= chs[i];
			chs[i] ^= chs[j];
			i++; j--;
		}
	}
}
// @lc code=end

