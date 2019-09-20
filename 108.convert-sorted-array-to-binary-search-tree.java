/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) return null;
		return helper(nums, 0, nums.length - 1);
	}
	
	private TreeNode helper(int[] nums, int left, int right) {
		if (left > right) return null;
		int mid = (right - left + 1) / 2 + left;
		TreeNode cur = new TreeNode(nums[mid]);
		cur.left = helper(nums, left, mid-1);
		cur.right = helper(nums, mid+1, right);
		return cur;
	}
}

