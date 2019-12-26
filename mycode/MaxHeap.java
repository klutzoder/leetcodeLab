package mycode;

public class MaxHeap {
	private int[] arr;
	private int size;

	public MaxHeap(int cap) {
		this.arr = new int[cap];
		this.size = 0;
	}

	public void offer(int val) {
		if (this.size >= arr.length) return;
		this.arr[size] = val;
		this.siftUp(this.size);
		this.size++;
	}

	public int getMax() {
		if (this.size == 0) return -1;
		swap(0, this.size-1);
		int max = this.arr[this.size-1];
		this.size--;
		this.siftDown(0);
		return max;
	}

	private void siftUp(int i) {
		int p = parent(i);
		while (p >= 0) {
			if (arr[i] < arr[p]) break;
			swap(i, p);
			i = p;
			p = parent(p);
		}
	}

	private void siftDown(int i) {
		while (i < size) {
			int l = left(i), r = right(i), temp = i;
			if (l < size && arr[temp] < arr[l]) temp = l;
			if (r < size && arr[temp] < arr[r]) temp = r;
			if (i == temp) break;
			swap(i, temp);
			i = temp;
		}
	}

	private void swap(int i, int j) {
		if (i == j) return;
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}

	private int parent(int c) {
		return (c - 1) >> 1;
	}

	private int left(int c) {
		return (c << 1) + 1;
	}

	private int right(int c) {
		return (c << 1) + 2;
	}

	public static void main(String[] args) {
		MaxHeap pq = new MaxHeap(100);
		for (int i = 0; i < 100; i++) pq.offer(i);

		for (int i = 0; i < 100; i++) {
			System.out.println(pq.getMax());
		}
	}
}