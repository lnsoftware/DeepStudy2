/**
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 **/
class SuperClass1 {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

class SubClass1 extends SuperClass1 {

    static {
        System.out.println("SubClass init!");
    }
}

/**
 * 非主动使用类字段演示
 **/
public class NotInitialization {

    public static void main(String[] args) {
//         通过子类引用父类的静态字段，不会导致子类初始化
//        System.out.println(SubClass.value);
//SuperClass init!
//123

/**
 * 被动使用类字段演示二：
 * 通过数组定义来引用类，不会触发此类的初始化
 **/
        SuperClass[] sca = new SuperClass[10];

    }
}