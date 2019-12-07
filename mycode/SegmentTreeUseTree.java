package mycode;

import javax.lang.model.util.ElementScanner6;

public class SegmentTreeUseTree {
	private class Node {
		private Node left, right;
		private int sum;
		private int start;
		private int end;

		public Node(int start, int end, int sum, Node left, Node right) {
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;

	public SegmentTreeUseTree(int[] nums) {
		root = build(0, nums.length - 1, nums);

		System.out.println(0);
	}

	private Node build(int start, int end, int[] nums) {
		if (start == end)
			return new Node(start, end, nums[start], null, null);
		int mid = (end - start) / 2 + start;
		Node left = build(start, mid, nums);
		Node right = build(mid + 1, end, nums);
		return new Node(start, end, left.sum + right.sum, left, right);
	}

	public void update(int i, int val) {
		updateNode(root, i, val);
	}

	private void updateNode(Node node, int i, int val) {
		if (node.start == i && node.end == i) {
			node.sum = val;
			return;
		}
		int mid = (node.end - node.start) / 2 + node.start;
		if (i <= mid)
			updateNode(node.left, i, val);
		else
			updateNode(node.right, i, val);
		node.sum = node.left.sum + node.right.sum;
	}

	public int sumRange(int i, int j) {
		return queryRange(root, i, j);
	}

	private int queryRange(Node root, int i, int j) {
		if (root.start == i && root.end == j)
			return root.sum;

		int mid = (root.end - root.start) / 2 + root.start;
		if (j <= mid)
			return queryRange(root.left, i, j);
		else if (i > mid)
			return queryRange(root.right, i, j);
		else
			return queryRange(root.left, i, mid) + queryRange(root.right, mid + 1, j);
	}

	public static void main(String[] args) {
		SegmentTreeUseTree sg = new SegmentTreeUseTree(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
	}

}