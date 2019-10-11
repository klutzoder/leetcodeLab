package testleetcode;

import java.util.Arrays;

class FindNumberOfLIS {

	public int findNumberOfLIS(int[] nums) {
		int N = nums.length;
		if (N <= 1)
			return N;
		int[] lengths = new int[N]; // lengths[i] = length of longest ending in nums[i]
		int[] counts = new int[N]; // count[i] = number of longest ending in nums[i]
		Arrays.fill(counts, 1);

		for (int j = 0; j < N; ++j) {
			for (int i = 0; i < j; ++i) {
				if (nums[i] < nums[j]) {
					if (lengths[i] >= lengths[j]) {
						lengths[j] = lengths[i] + 1;
						counts[j] = counts[i];
					} else if (lengths[i] + 1 == lengths[j]) {
						counts[j] += counts[i];
					}
				}
			}
		}

		int longest = 0, ans = 0;
		for (int length : lengths) {
			longest = Math.max(longest, length);
		}
		for (int i = 0; i < N; ++i) {
			if (lengths[i] == longest) {
				ans += counts[i];
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int res = new FindNumberOfLIS().findNumberOfLIS(new int[] { 1, 3, 5, 4, 7 });
		System.out.println(res);
	}
}