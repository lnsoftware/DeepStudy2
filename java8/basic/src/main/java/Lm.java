
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by hg on 2017/7/26.
 */
public class Lm {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        Predicate<String> p = (String s) -> list.add(s);

        Predicate<String> p1 = s -> list.add(s);

        boolean ret = p.test("ss");
        System.out.println(ret);
    }
}
