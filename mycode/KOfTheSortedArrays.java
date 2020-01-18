package mycode;

import java.util.Arrays;

class KOfTheSortedArrays {

	// merge sort
	// space: O(1)
	// time: O(m+n)
	public int solution11(int[] a, int[] b, int k) {
		if (a == null || b == null || a.length == 0 || b.length == 0)
			return -1;
		int len1 = a.length, len2 = b.length;
		if (len1 + len2 < k)
			return -1;
		int p = len1 - 1, q = len2 - 1;
		while (p >= 0 && q >= 0) {
			if (a[p] > b[q]) {
				k--;
				if (k == 0)
					return a[p];
				p--;
			} else {
				k--;
				if (k == 0)
					return b[q];
				q--;
			}
		}
		while (p >= 0) {
			if (--k == 0)
				return a[p];
			p--;
		}
		while (q >= 0) {
			if (--k == 0)
				return b[q];
			q--;
		}
		return -1;
	}

	public int solution22(int[] a, int[] b, int k) {
		if (a == null || a.length == 0 || b == null || b.length == 0)
			return -1;
		int len1 = a.length, len2 = b.length;
		if (len1 + len2 < k)
			return -1;
		if (k == 1)
			return Math.max(a[len1 - 1], b[len2 - 1]);
		if (len1 > len2)
			return solution2(b, a, k);
		int start = 0, end = len1, cut1 = 0, cut2 = 0;
		while (start <= end) {
			cut1 = (end - start) / 2 + start;
			cut2 = len2 - (k - (len1 - cut1));
			// System.out.printf("%d, %d\n", cut1, cut2);
			if (cut2 > len2) {
				start = cut1 + 1;
				continue;
			}
			if (cut2 < 0) {
				end = cut1 - 1;
				continue;
			}
			int l1 = cut1 == 0 ? Integer.MIN_VALUE : a[cut1 - 1];
			int r1 = cut1 == len1 ? Integer.MAX_VALUE : a[cut1];
			int l2 = cut2 == 0 ? Integer.MIN_VALUE : b[cut2 - 1];
			int r2 = cut2 == len2 ? Integer.MAX_VALUE : b[cut2];

			if (l1 > r2)
				end = cut1 - 1;
			else if (l2 > r1)
				start = cut1 + 1;
			else {
				return r1 < r2 ? r1 : r2;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// for (int i = 1; i <= 10; i++) {
		// System.out.println(
		// new KOfTheSortedArrays().solution2(new int[] { 2, 3, 6, 7, 9 }, new int[] {
		// 1, 4, 8, 10 }, i));
		// }

		// System.out.println(
		// new KOfTheSortedArrays().solution2(new int[] { 2, 3, 6, 7, 9 }, new int[] {
		// 1, 4, 8, 10 }, 9));

		for (int i = 1; i <= 15; i++) {
			System.out.println(new KOfTheSortedArrays().solution2(new int[] { 100, 112, 256, 349, 770 },
					new int[] { 72, 86, 113, 119, 265, 445, 892 }, i));
		}

		// System.out.println(new KOfTheSortedArrays().solution2(new int[] { 100, 112,
		// 256, 349, 770 },
		// new int[] { 72, 86, 113, 119, 265, 445, 892 }, 7));
	}
}