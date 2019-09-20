import java.util.Map;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */
class Solution {
	public int lengthOfLongestSubstring(String s) {
	   if (s == null || s.length() == 0) return 0;
	   Map<Character, Integer> map = new HashMap<>(); 
	   int res = 0, i = 0, j = 0;
	   while (j < s.length()) {
		   if (!map.containsKey(s.charAt(j))) {
			   map.put(s.charAt(j), ++j);
			   res = Math.max(res, j-i);
		   } else {
			   i = map.remove(s.charAt(i));
		   }
	   }
	   return res;
	}
}
