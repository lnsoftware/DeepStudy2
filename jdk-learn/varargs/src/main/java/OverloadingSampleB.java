public class OverloadingSampleB {  
    public static void main(String[] args) {
        // 编译出错
//        testOverloading(1, 2, 3);
    }  
  
    private static void testOverloading(Object... args) {}  
  
    private static void testOverloading(Object o, Object... args) {}  
}  