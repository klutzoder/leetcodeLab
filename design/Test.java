package design;

public class Test {
	transient int sum = 0;

	public static void main(String[] args) {
		Test t = new Test();
		t.sum++;

		int x = t.sum;
		x++;
		System.out.println("Hello world");
	}

}