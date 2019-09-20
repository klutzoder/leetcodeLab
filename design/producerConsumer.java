package design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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

		private ReentrantLock putLock = new ReentrantLock();
		private Condition notFull = putLock.newCondition();

		private ReentrantLock takeLock = new ReentrantLock();
		private Condition notEmpty = takeLock.newCondition();

		private AtomicInteger count = new AtomicInteger();

		public ProducerConsumer(int capacity) {
			this.capacity = capacity;
			queue = new LinkedList<>();
		}

		public void put(Integer obj) {
			putLock.lock();
			int c = -1;
			try {
				while (count.get() == this.capacity) {
					System.out.println("当前满了");
					notFull.await();
				}
				queue.offer(obj);
				// 之前为0，现在不为0
				c = count.getAndIncrement();
				if (c + 1 < capacity) {
					notFull.signal();
				}
			} catch (Exception e) {

			} finally {
				putLock.unlock();
			}

			if (c == 0) {
				takeLock.lock();
				try {
					notEmpty.signalAll();
				} finally {
					takeLock.unlock();
				}
			}
		}

		public Integer take() {
			takeLock.lock();
			Integer res = -1;
			int c = -1;
			try {
				while (count.get() == 0) {
					System.out.println("当前为空");
					notEmpty.await();
				}
				res = queue.poll();
				// 之前满，现在不满了
				c = count.getAndDecrement();
				if (c > 1) { // 若 c == 1，则减一后变为0，就是空了，所以拿出前必须大于1
					notEmpty.signal();
				}
			} catch (Exception e) {

			} finally {
				takeLock.unlock();
			}

			if (c == capacity) {
				putLock.lock();
				try {
					notFull.signalAll();
				} finally {
					putLock.unlock();
				}
			}

			return res;
		}

	}
}