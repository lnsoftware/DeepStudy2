


public class AccessStaticParentFieldThroughChild {

    public static void main(String args[]) throws InterruptedException {
        //accessing static field of Parent through child, should only initialize Parent
        System.out.println("get parent field:" + Child.familyName);

        System.out.println("start new child.");
        Child c = new Child();
    }
}
