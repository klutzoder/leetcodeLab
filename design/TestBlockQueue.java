package design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestBlockQueue {

	private Queue<Integer> queue;
	private int capacity;
	private Object putLock, takeLock;
	private AtomicInteger count;

	public TestBlockQueue(int capacity) {
		this.queue = new LinkedList();
		this.capacity = capacity;
		this.putLock = new Object();
		this.takeLock = new Object();
		this.count = new AtomicInteger(0);
	}

	public void put(int val) throws InterruptedException {
		int c = -1;

		synchronized (putLock) {
			while (count.get() == capacity) {
				System.out.println("满了");
				wait();
			}
			queue.offer(val);
			c = count.getAndIncrement();
			if (c + 1 < this.capacity) {
				putLock.notifyAll();
			}
		}

		if (c == 0) {
			synchronized (takeLock) {
				takeLock.notifyAll();
			}
		}

	}

	public int take() throws InterruptedException {
		int res = -1;
		int c = 0;
		synchronized (takeLock) {
			while (count.get() == 0) {
				System.out.println("空了");
				wait();
			}
			res = queue.poll();
			c = count.getAndDecrement();
			if (c > 1) {
				takeLock.notifyAll();
			}
		}

		if (c == capacity) {
			synchronized (putLock) {
				putLock.notifyAll();
			}
		}

		return res;
	}

	public static void main(String[] args) throws InterruptedException {
		TestBlockQueue tbq = new TestBlockQueue(1000);

		ExecutorService product = Executors.newFixedThreadPool(10);
		for (int k = 0; k < 10; k++) {
			product.submit(() -> {
				for (int i = 0; i < 1000; i++) {
					try {
						tbq.put(i);
					} catch (InterruptedException e) {
						
					}
				}
			});
		}


		ExecutorService consumer = Executors.newFixedThreadPool(4);

		consumer.submit(() -> {
			while(true){
				System.out.println(tbq.take());
			}
		});

		Thread.sleep(10000);	
	}
}