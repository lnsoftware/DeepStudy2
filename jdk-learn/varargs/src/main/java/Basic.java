/**
 * Created by wangqinghui on 2017/12/17.
 */
public class Basic {


    private static void testOverloading(String a, int[] i) {
        System.out.println("A");
    }

//    public static void main(String[] args) {
//        testOverloading("1", 2, 3);// 编译出错
//    }
}

// 编译器会在背地里把这最后一个形参转化为一个数组形参，并在编译出的class文件里作上一个记号，表明这是个实参个数可变的方法。
class VarargAndArray {

    static int sumUp(int... values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }
// 不能与可变参数同时存在
//    static int sumUp(int[] values) {
//        int sum = 0;
//        for (int value : values) {
//            sum += value;
//        }
//        return sum;
//    }

}