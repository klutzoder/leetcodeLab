package testleetcode;

class LongestIncreasingSubsequence {

	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int[] tail = new int[nums.length];
		tail[0] = Integer.MAX_VALUE;
		int size = 0;
		for (int num : nums) {
			int i = 0, j = size;
			while (i + 1 < j) {
				int mid = (j - i) / 2 + i;
				if (tail[mid] <= num)
					i = mid;
				else
					j = mid;
			}
			if (tail[i] > num)
				j = i;
			tail[j] = num;
			if (j == size)
				size++;
		}
		return size;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
		new LongestIncreasingSubsequence().lengthOfLIS(nums);
	}
}