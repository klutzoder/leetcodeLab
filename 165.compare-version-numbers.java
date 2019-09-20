/*
 * @lc app=leetcode id=165 lang=java
 *
 * [165] Compare Version Numbers
 */
class Solution {
	public int compareVersion(String version1, String version2) {
		String[] v1strs = version1.split("\\.");
		String[] v2strs = version2.split("\\.");

		int len = Math.max(v1strs.length, v2strs.length);
		for (int i = 0; i < len; i++) {
			Integer v1 = i < v1strs.length ? Integer.parseInt(v1strs[i]) : 0;
			Integer v2 = i < v2strs.length ? Integer.parseInt(v2strs[i]) : 0;
			Integer compare = v1.compareTo(v2);
			if (compare != 0) return compare;
		}
		return 0;
	}

}
