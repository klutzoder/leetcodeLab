package design;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyPriorityQueue {
	
	private int size;
	private List<Integer> queue;

	public MyPriorityQueue() {
		this.queue = new ArrayList<>();
		this.size = 0;
	}

	public void offer(int e) {
		int i = size;
		queue.add(e);
		while (i > 0) {
			int parent = (i-1) >> 1;
			if (queue.get(parent) <= e) break;
			swap(i, parent);
			i = parent;
		}
		size++;
		queue.set(i, e);
	}

	public int poll() {
		swap(0, --size);
		int i = 0;
		int half = size >> 1;
		while (i < half) {
			int left = (i << 1) + 1, right = (i << 1) + 2;
			if (queue.get(i) < queue.get(left)) left = i;
			if (right < size && queue.get(left) > queue.get(right)) left = right;
			if (i == left) break;
			swap(i, left);
			i = left;
		}
		return queue.get(size);
	}


	private void swap(int i, int j) {
		int temp = queue.get(i);
		queue.set(i, queue.get(j));
		queue.set(j, temp);
	}

	public static void main(String[] args) {
		MyPriorityQueue p = new MyPriorityQueue();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			p.offer(r.nextInt(100));
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(p.poll());
		}		
	}
}