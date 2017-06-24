 
public class ClassInitializationTest2 {
 
    public static void main(String args[]) throws InterruptedException {
 
       //accessing static field of Parent through child, should only initialize Parent
       System.out.println(Child.familyName);
        Child c = new Child();
    }
}
