/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 */
class Solution {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null)
			return 0.0;
		if (nums1.length > nums2.length)
			return findMedianSortedArrays(nums2, nums1);

		int allLen = nums1.length + nums2.length;
		int halfLen = allLen / 2;
		int cut1 = 0, cut2 = 0;
		int start = 0, end = nums1.length - 1;
		
		while (cut1 <= nums1.length) {
			cut1 = (end - start) / 2 + start;
			cut2 = halfLen - cut1;
			double l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
			double r1 = cut1 == nums1.length ? Integer.MAX_VALUE : nums1[cut1];
			double l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
			double r2 = cut2 == nums2.length ? Integer.MAX_VALUE : nums2[cut2];

			if (l1 > r2)
				end = cut1 - 1;
			else if (l2 > r1)
				start = cut1 + 1;
			else {
				if (allLen % 2 == 0) {
					return ((l1 > l2 ? l1 : l2) + (r1 < r2 ? r1 : r2)) / 2.0;
				} else {
					return r1 < r2 ? r1 : r2;
				}
			}
		}

		return 0.0;
	}
}
