/**
 * Created by hg on 2017/6/15.
 */
public class HelloImpl implements IHello {
    @Override public String hello(String hello) {
        return "hello "+ hello;
    }
}
