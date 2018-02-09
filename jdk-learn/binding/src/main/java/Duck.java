class Animal {  
 private int i=1;  
 public void walk(){System.out.println(i);}  
}  
public class  Duck extends Animal{  
 private int i=2;
 public void walk(){System.out.println(i);}

 public static void main(String...a) {  
  Animal animal = new Duck();  
  animal.walk();  
 }  
}  
