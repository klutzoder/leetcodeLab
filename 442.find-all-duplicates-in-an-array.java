/*
 * @lc app=leetcode id=442 lang=java
 *
 * [442] Find All Duplicates in an Array
 */
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int idx = Math.abs(nums[i]) - 1;
			if (nums[idx] < 0) res.add(Math.abs(idx+1));
			nums[idx] = -nums[idx];
		}
		return res;
    }
}

