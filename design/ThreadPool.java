package design;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class ThreadPool {
	private int poolSize;
	private List<Worker> workers;
	private BlockingQueue<Runnable> queue;

	public ThreadPool(int poolSize) {
		this.poolSize = poolSize;
		this.workers = new ArrayList(this.poolSize);
		this.queue = new LinkedBlockingQueue<>();

		init();
	}

	private void init() {
		for (int i = 0; i < this.poolSize; i++) {
			Worker worker = new Worker();
			worker.thread.start();
			workers.add(worker);
		}
	}

	private void runTask(Worker worker) {
		Runnable task = null;
		try {
			while (task != null || (task = queue.take()) != null) {
				task.run();
				task = null;
			}
		} catch (InterruptedException e) {
		}
	}

	public void shutdown() {
		while (this.queue.size() != 0) {
		}
		for (Worker worker : workers) {
			worker.thread.interrupt();
		}
	}

	public void execute(Runnable task) {
		try {
			queue.put(task);
		} catch (InterruptedException e) {
		}
	}

	private final class Worker implements Runnable {

		private Thread thread;

		public Worker() {
			this.thread = new Thread(this);
		}

		@Override
		public void run() {
			runTask(this);
		}

	}

	public static void main(String[] args) {
		// ThreadPool pool = new ThreadPool(2);
		ExecutorService pool = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 100; i++) {
			int x = i;
			pool.execute(() -> {
				System.out.println(x);
			});
		}
		pool.shutdown();
	}
}