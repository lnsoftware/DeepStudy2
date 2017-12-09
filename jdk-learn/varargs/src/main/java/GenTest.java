/**
 * Created by wangqinghui on 2017/12/17.
 */
public class GenTest {

    public static void main(String[] args) {

        Gen<String> gen = new Gen<String>();

        String[] array = new String[]{"a", "b"};
//        gen.testVarargs(array);

        gen.testVarargs("a", "b");
    }

}


class Gen<T> {

    public void testVarargs(T... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }

//    public void testVarargs(T[] args) {
//        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i]);
//        }
//    }
}