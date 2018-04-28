package invokedynamic.demo;

public class LamdaTest {
    
    public static void main(String[] args) {
        Runnable x = () -> {
            //System.out.println("Hello, World!"); 
        };
        x.run();
    }
    
}
