/*
 * @lc app=leetcode id=1262 lang=java
 *
 * [1262] Greatest Sum Divisible by Three
 *
 * https://leetcode.com/problems/greatest-sum-divisible-by-three/description/
 *
 * algorithms
 * Medium (40.71%)
 * Likes:    168
 * Dislikes: 6
 * Total Accepted:    5.5K
 * Total Submissions: 13.6K
 * Testcase Example:  '[3,6,5,1,8]'
 *
 * Given an array nums of integers, we need to find the maximum possible sum of
 * elements of the array such that it is divisible by three.
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum
 * divisible by 3).
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum
 * divisible by 3).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution{
    public int maxSumDivThree(int[] nums) {
		int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : nums) {
            int[] dp2 = new int[3];
            for (int i = 0; i < 3; ++i)
                dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            dp = dp2;
        }
        return dp[0]; 
	}
	
}
// @lc code=end

