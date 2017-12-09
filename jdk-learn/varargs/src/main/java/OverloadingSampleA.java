public class OverloadingSampleA {  
    public static void main(String[] args) {  
        testOverloading(1);// 打印出A  
        testOverloading(1, 2);// 打印出B  
        testOverloading(1, 2, 3);// 打印出C  
    }  
  
    private static void testOverloading(int i) {  
        System.out.println("A");  
    }  
  
    private static void testOverloading(int i, int j) {  
        System.out.println("B");  
    }  
  
        private static void testOverloading(int i, int... more) {  
        System.out.println("C");  
    }  
}  