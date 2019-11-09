package mycode;

import java.util.Arrays;

class UnionFind {
	private int[] parents;

	public UnionFind(int size) {
		this.parents = new int[size];
		Arrays.fill(this.parents, -1);
	}

	public int findRoot(int x) {
		int xRoot = x;
		while (this.parents[xRoot] != -1) {
			xRoot = parents[xRoot];
		}
		return xRoot;
	}

	public boolean union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
		if (xRoot == yRoot)
			return false;
		parents[xRoot] = yRoot;
		return true;
	}
}