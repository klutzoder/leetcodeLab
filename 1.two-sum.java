/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */
class Solution {
	public int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return new int[] { -1, -1 };
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int remain = target - nums[i];
			if (map.containsKey(remain)) {
				return new int[] { map.get(remain), i };
			}
			map.put(nums[i], i);
		}
		return new int[] { -1, -1 };
	}
}
