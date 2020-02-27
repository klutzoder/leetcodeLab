package design;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class RoundRobin {

	private static final int BOUND = 100;

	public static void main(final String[] args) {
		final int m = 10, n = 100;
		final int[] patients = new int[n];
		final Random r = new Random();
		for (int i = 0; i < n; i++) {
			patients[i] = r.nextInt(BOUND);
		}
		System.out.println(Arrays.toString(patients));
		Arrays.sort(patients);

		PriorityQueue<int[]> doctors = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for (int i = 0; i < m; i++) doctors.offer(new int[]{0, i});
		for (int i = 0; i < n; i++) {
			int[] cur = doctors.poll();
			System.out.printf("patient: %d, doctor: %d\n", i, cur[1]);
			cur[0] += patients[i];
			doctors.offer(cur);
		}		
		System.out.println("==================");
		while (!doctors.isEmpty()) {
			int[] cur = doctors.poll();
			System.out.printf("doctor: %d, time: %d\n", cur[1], cur[0]);
		}
		
	}
}