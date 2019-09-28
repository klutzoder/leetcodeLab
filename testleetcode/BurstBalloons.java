package testleetcode;

class BurstBalloons {
	public int maxCoins(int[] nums) {
		int[] balls = new int[nums.length + 2];
		int n = 1;
		for (int x : nums)
			if (x > 0)
				balls[n++] = x;
		balls[0] = balls[n++] = 1;
		int[][] dp = new int[n][n];
		for (int k = 2; k < n; ++k)
			for (int left = 0; left < n - k; ++left) {
				int right = left + k;
				for (int i = left + 1; i < right; ++i)
					dp[left][right] = Math.max(dp[left][right],
							balls[left] * balls[i] * balls[right] + dp[left][i] + dp[i][right]);
			}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 1, 5, 8 };
		int res = new BurstBalloons().maxCoins(nums);
		System.out.println(res);
	}
}