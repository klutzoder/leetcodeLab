package design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class ProducerConsumerTest {
	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer(5);

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					pc.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Producer");

		new Thread(() -> {
			while (true) {
				try {
					pc.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Consumer");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class ProducerConsumer {

		private int capacity;
		private AtomicInteger count;
		private Object putLock;
		private Object takeLock;
		private Queue<Integer> queue;

		public ProducerConsumer(int capacity) {
			this.capacity = capacity;
			this.count = new AtomicInteger();
			this.queue = new LinkedList<>();
			this.putLock = new Object();
			this.takeLock = new Object();
		}

		public void put(int val) throws InterruptedException {
			final Object putLock = this.putLock;
			int c = -1;
			synchronized (putLock) {
				// full
				while (count.get() == this.capacity) {
					System.out.println("满了");
					putLock.wait();
				}
				queue.offer(val);
				c = count.getAndIncrement();
				if (c + 1 < this.capacity) {
					putLock.notify();
				}
			}

			if (c == 0)
				notifyTake();
		}

		private void notifyTake() {
			final Object takeLock = this.takeLock;
			synchronized (takeLock) {
				takeLock.notify();
			}
		}

		private void notifyPut() {
			final Object putLock = this.putLock;
			synchronized (putLock) {
				putLock.notify();
			}
		}

		public int take() throws InterruptedException {
			final Object takeLock = this.takeLock;
			int c = -1;
			int res = -1;
			synchronized (takeLock) {
				while (count.get() == 0) {
					System.out.println("空了");
					takeLock.wait();
				}
				res = queue.poll();
				c = count.getAndDecrement();
				if (c > 2) {
					takeLock.notify();
				}
			}
			if (c == this.capacity)
				notifyPut();
			return res;
		}
	}
}