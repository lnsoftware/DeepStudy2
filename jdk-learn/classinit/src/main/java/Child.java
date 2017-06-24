/**
 * Sub class of Parent, demonstrate when exactly sub class loading and initialization occurs.
 */
class Child extends Parent {
    static { System.out.println("static block of Sub class is initialized in Java "); }
    {System.out.println("non static blocks in sub class is initialized");}
}