package design;

import java.util.PriorityQueue;

class PriorityQueueTest {

	private static class Time {
		protected int inStation;
		protected int outStation;

		public Time(int in, int out) {
			this.inStation = in;
			this.outStation = out;
		}
	}

	public static void main(String[] args) {
		// 按进站时间排序，每次取进展最小的
		PriorityQueue<Time> pq = new PriorityQueue<>((a,b)->a.inStation-b.inStation);
		Time t1 = new Time(111,0);
		pq.offer(t1);

	}
}