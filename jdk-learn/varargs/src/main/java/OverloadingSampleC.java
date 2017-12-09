

public class OverloadingSampleC {
    public static void main(String[] args) {
		/* 编译出错 */
//        testOverloading(1, 2);
		/* 还是编译出错 */
//        testOverloading(new Integer(1), new Integer(2));
    }

    private static void testOverloading(int... args) {}

    private static void testOverloading(Integer... args) {}
}