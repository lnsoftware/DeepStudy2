package java.test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.taofang.service.IHelloService;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;

public class ResourceTest {

    public static void main(String args[]) {
        String url =
                "http://localhost:8080/hessianweb/remoting/helloServiceExporter";

        HessianProxyFactory factory = new HessianProxyFactory();
        IHelloService basic;
        try {
            basic = (IHelloService) factory.create(IHelloService.class, url);
            System.out.println(System.currentTimeMillis());
            System.out.println("hello(): " + basic.say("zhanghailei"));
            System.out.println(System.currentTimeMillis());
            System.out.println(System.currentTimeMillis());
            System.out.println("hello(): zhanghailei");
            System.out.println(System.currentTimeMillis());

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//		 Integer a = 760171376;
//		 System.out.println(a.toHexString(760171376));

//		AtomicInteger a = new AtomicInteger(Integer.MAX_VALUE);
//		a.set(0);
//		System.out.println(a.incrementAndGet());
//		System.out.println("中国".getBytes());
//		System.out.println(getUTF8("中国"));
//
//		test();
    }

    public static void test() {

        ByteArrayOutputStream os = new ByteArrayOutputStream(128);
        //os.write(1);
        os.write(5 >> 24);
        os.write(5 >> 16);
        os.write(5 >> 8);
        os.write(5);
        System.out.println(new String(os.toByteArray()));
    }

    public static byte[] getUTF8(String src) {
        ByteArrayOutputStream os = new ByteArrayOutputStream(128);
        int length = src.length();
        int offset = 0;
        for (int i = 0; i < length; i++) {
            char ch = src.charAt(i + offset);

            if (ch < 0x80)
                os.write(ch);
            else if (ch < 0x800) {
                os.write(0xc0 + ((ch >> 6) & 0x1f));
                os.write(0x80 + (ch & 0x3f));
            } else {
                os.write(0xe0 + ((ch >> 12) & 0xf));
                os.write(0x80 + ((ch >> 6) & 0x3f));
                os.write(0x80 + (ch & 0x3f));
            }
        }

        return os.toByteArray();
    }
}
