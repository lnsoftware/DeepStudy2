package ansy;

/**
 * Created by hg on 2017/6/28.
 */
public class Main {
    public static void main(String[] args) {

        RpcInvokeHook hook = new RpcInvokeHook() {
            public void beforeInvoke(String method, Object[] args) {
                System.out.println("before invoke " + method);
            }

            public void afterInvoke(String method, Object[] args) {
                System.out.println("after invoke " + method);
            }
        };

        TestInterface testInterface
            = RpcClientProxyBuilder.create(TestInterface.class)
            .timeout(0)
            .hook(hook)
            .connect("127.0.0.1", 3721)
            .build();

        System.out.println("invoke result = " + testInterface.testMethod01("qwerty"));
    }
}
