/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 */
class Solution {
	public String shortestPalindrome(String s) {
		String temp = s + "#" + new StringBuilder(s).reverse().toString();
		int[] prefix = prefixTable(temp);
		return new StringBuilder(s.substring(prefix[prefix.length - 1])).reverse().toString() + s;
	}

	private int[] prefixTable(String s) {
		int[] prefix = new int[s.length()];
		int i = 1, len = 0;
		while (i < s.length()) {
			if (s.charAt(i) == s.charAt(len)) {
				prefix[i++] = ++len;
			} else {
				if (len > 0)
					len = prefix[len - 1];
				else
					prefix[i++] = len;
			}
		}
		return prefix;
	}
}
