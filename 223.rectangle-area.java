/*
 * @lc app=leetcode id=223 lang=java
 *
 * [223] Rectangle Area
 */
class Solution {
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int left = Math.max(A, E), right = Math.max(Math.min(C, G), left);
		int bottom = Math.max(B, F), top = Math.max(Math.min(D, H), bottom);
		return (C - A) * (D - B) + (G - E) * (H - F) - (right - left) * (top - bottom);
	}
}
