package mycode;

import java.util.Arrays;

class UnionFind {
	private int[] parents;

	public UnionFind(int size) {
		this.parents = new int[size];
		for (int i = 0; i < size; i++) this.parents[i] = i;
	}

	public int findRoot(int x) {
		int xRoot = x;
		while (this.parents[xRoot] != -1) {
			parents[xRoot] = parents[parents[xRoot]];
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