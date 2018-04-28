public class Test {

public static void main(String argv[]) throws Exception {
	for (int i = 0; i < 100; i++) {
		foo(3, 4);
		foo(-3, -4);
	}

	Thread.sleep(1000000);
}

public static void foo(int x, int y) {
	if (x < 0 && y < 0) {
		foo(y, add(x, 1));
	}	
}

public static int add(int a, int b) {
	return a + b;
}

}
