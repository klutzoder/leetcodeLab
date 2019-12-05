/*
 * @lc app=leetcode id=1012 lang=java
 *
 * [1012] Numbers With Repeated Digits
 */

// @lc code=start
class Solution {
	public int numDupDigitsAtMostN(int N) {
		// Transform N + 1 to arrayList
		ArrayList<Integer> L = new ArrayList<Integer>();
		for (int x = N + 1; x > 0; x /= 10)
			L.add(0, x % 10);

		// Count the number with digits < N
		int res = 0, n = L.size();
		for (int i = 1; i < n; ++i)
			res += 9 * A(9, i - 1);

		// Count the number with same prefix
		HashSet<Integer> seen = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j)
				if (!seen.contains(j))
					res += A(9 - i, n - i - 1);
			if (seen.contains(L.get(i)))
				break;
			seen.add(L.get(i));
		}
		return N - res;
	}

	public int A(int m, int n) {
		int res = 1;
		for (int i = m-n+1; i <= m; i++) res *= i;
		return res;
	}
}
// @lc code=end
