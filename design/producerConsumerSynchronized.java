package design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class ProducerConsumerTest {
	public static void main(String[] args) throws InterruptedException {
		ProducerConsumer pc = new ProducerConsumer(5);

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				pc.put(i);
			}
		}, "Producer").start();

		new Thread(() -> {
			while (true) {
				System.out.println(pc.take());
			}
		}, "Consumer").start();

		Thread.sleep(10000);
	}

	private static class ProducerConsumer {
		private int capacity = 0;

		private Queue<Integer> queue;

		private Object putLock = new Object();
		private Object takeLock = new Object();
		private AtomicInteger count = new AtomicInteger();

		public ProducerConsumer(int capacity) {
			this.capacity = capacity;
			queue = new LinkedList<>();
		}

		public void put(Integer obj) {
			int c = -1;
			synchronized (putLock) {
				while (count.get() == this.capacity) {
					System.out.println("当前满了!");
					try {
						putLock.wait();
					} catch (InterruptedException e) {
					}
				}
				queue.offer(obj);
				// 获取的是之前的值
				c = count.getAndIncrement();
				if (c + 1 < capacity) // 若当前插入后没满
					putLock.notify();
			}
			if (c == 0) {
				synchronized (takeLock) {
					takeLock.notify();
				}
			}
		}

		public Integer take() {
			Integer res = -1;
			int c = -1;

			synchronized (takeLock) {
				while (count.get() == 0) {
					System.out.println("当前空了!");
					try {
						takeLock.wait();
					} catch (InterruptedException e) {
					}
				}
				res = queue.poll();
				c = count.getAndDecrement();
				if (c > 1) // 若当前拿出后仍有
					takeLock.notify();
			}

			if (c == capacity) {
				synchronized (putLock) {
					putLock.notify();
				}
			}
			return res;
		}

	}
}