public class OverrideTest{
    
    public static void main(String[] args){
        Animal dog = new Dog();
        dog.say();
        Animal cat = new Cat();
        cat.say();
    }
}
class Animal {
    public void say(){
        System.out.println("is animal");
    }
}
class Dog extends Animal{
    public void say(){
        System.out.println("is Dog");
    }
}
class Cat extends Animal{
    public void say(){
        System.out.println("is Cat");
    }
}