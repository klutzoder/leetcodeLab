package mycode;

public class SegmentTreeUseArray {

	private int[] tree;
	private int n;

	public SegmentTreeUseArray(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		n = nums.length;
		tree = buildTree(nums);
	}

	private int[] buildTree(int[] nums) {
		int[] t = new int[n * 2];
		for (int i = n, j = 0; i < n * 2; i++, j++) {
			t[i] = nums[j];
		}
		for (int i = n - 1; i >= 0; i--) {
			t[i] = t[i * 2] + t[i * 2 + 1];
		}
		return t;
	}

	void update(int pos, int val) {
		pos += n;
		tree[pos] = val;
		while (pos > 0) {
			int left = pos;
			int right = pos;
			if (pos % 2 == 0) {
				right = pos + 1;
			} else {
				left = pos - 1;
			}
			// parent is updated after child is updated
			tree[pos / 2] = tree[left] + tree[right];
			pos /= 2;
		}
	}

	public int sumRange(int l, int r) {
		// get leaf with value 'l'
		l += n;
		// get leaf with value 'r'
		r += n;
		int sum = 0;
		while (l <= r) {
			if ((l % 2) == 1) {
				sum += tree[l];
				l++;
			}
			if ((r % 2) == 0) {
				sum += tree[r];
				r--;
			}
			l /= 2;
			r /= 2;
		}
		return sum;
	}

	public static void main(String[] args) {
		SegmentTreeUseArray sg = new SegmentTreeUseArray(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		System.out.println(0);
	}

}