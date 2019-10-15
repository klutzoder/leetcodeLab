/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 */

// @lc code=start
class Solution {
    public int strStr(String s, String p) {
		if (s == null || p == null) return -1;
		if (p.length() == 0) return 0;
		int[] prefix = prefixTable(p);
		int i = 0, j = 0;
		while (i < s.length()) {
			if (j == p.length()-1 && s.charAt(i) == p.charAt(j)) return i-j;
			if (s.charAt(i) == p.charAt(j)) {
				i++; j++;
			} else {
				j = prefix[j];
				if (j == -1) {
					i++;j++;
				}
			}
		}
		return -1;
	}
	
	private int[] prefixTable(String p) {
		int[] prefix = new int[p.length()];
		int i = 1, len = 0;
		while (i < p.length()) {
			if (p.charAt(i) == p.charAt(len)) {
				prefix[i++] = ++len;
			} else {
				if (len > 0) len = prefix[len-1];
				else prefix[i++] = len;
			}
		}
		for (i = p.length()-1; i > 0; i--) {
			prefix[i] = prefix[i-1];
		}
		prefix[0] = -1;
		return prefix;
	} 
}
// @lc code=end

