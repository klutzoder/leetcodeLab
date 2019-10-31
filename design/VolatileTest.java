package design;

public class VolatileTest {

	private static volatile int a = 10;

	public static void main(String[] args) {
		a++;
		System.out.println("Hello world!");
	}
}