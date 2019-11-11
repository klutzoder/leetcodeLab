import java.util.Collections;
import java.util.Map;

/*
 * @lc app=leetcode id=697 lang=java
 *
 * [697] Degree of an Array
 */

// @lc code=start
class Solution {
    public int findShortestSubArray(int[] nums) {
		Map<Integer, Integer> left = new HashMap<>(), right = new HashMap<>(), count = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int x = nums[i];
			left.putIfAbsent(x, i);
			right.put(x, i);
			count.put(x, count.getOrDefault(x, 0)+1);
		} 
		int degree = Collections.max(count.values());
		int res = nums.length;
		for (int x : left.keySet()) {
			if (count.get(x) != degree) continue;
			res = Math.max(res, right.get(x)-left.get(x)+1);
		}
		return res;
    }
}
// @lc code=end

