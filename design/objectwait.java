package design;

class ObjectWait {
	
	public static void main(String[] args) throws InterruptedException {
		
		Solution solution = new Solution();

		new Thread(()->{
			solution.run();
		}).start();

		new Thread(()->{
			solution.run();
		}).start();

		Thread.sleep(10000);
	}

	private static class Solution {

		private int i = 0;

		public void run() {
			while (i <= 100) {
				System.out.println(i++);
				synchronized(this) {
					this.notifyAll();
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}