package design;

class RedisLock {
	// 守护线程
	public void Lock() {
		Thread daemonThread = new Thread(new Runnable() {
			public void run() {
				// 续命操作 ……
			}
		});

		if (redisClient.set("key", "value", "NX", "EX", 30) == 1) {// 1、设值和设置超时时间
			try {
				// 开启守护线程
				daemonThread.start();
				daemonThread.setDaemon(true);
				// 业务逻辑 ……
			} finally {
				del("key");// 2、释放锁
			}
			daemonThread.interrupt();// 通知守护线程终止
			// 其他操作 ……
		}
	}
}
