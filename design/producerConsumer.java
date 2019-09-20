package design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

		public ProducerConsumer(int capacity) {
			this.capacity = capacity;
			queue = new LinkedList<>();
		}

		public synchronized void put(Integer obj) {
			while (queue.size() >= this.capacity) {
				System.out.println("当前满了");

				try {
					this.wait();
				} catch (Exception e) {
				}
			}
			queue.offer(obj);
			this.notifyAll();
		}

		public synchronized Integer take() {
			while (queue.size() <= 0) {
				System.out.println("当前为空");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Integer res = queue.poll();
			this.notifyAll();
			return res;
		}

	}
}