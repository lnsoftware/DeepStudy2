import base.JavaDynamicProxy;
import br.com.fabriciodeb.sample.Address;
import br.com.fabriciodeb.sample.Foo;
import br.com.fabriciodeb.sample.IHello;
import br.com.fabriciodeb.sample.Person;
import br.com.fabriciodeb.sample.impl.FooImpl;
import br.com.fabriciodeb.sample.impl.HelloImpl;
import br.com.fabriciodeb.sample.proxy.DynamicProxyEntityInterceptor;
import br.com.fabriciodeb.sample.proxy.EntityInterceptor;
import br.com.fabriciodeb.sample.proxy.LoggerInterceptor;
import br.com.fabriciodeb.sample.proxy.ProxyFoo;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestProxy {

    @Before
    public void be(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./target/cglib_proxy");

    }

    @Test
    public void testProxy() {
        Foo foo = new FooImpl();

        Foo proxy = (Foo) ProxyFoo.createProxy(foo);

//        proxy = (Foo)ProxyFoo.createProxy(proxy);

        proxy.foo();

    }

    @Test
    public void genFile() throws IOException {
        //被动态代理的IHello实例对象A
        IHello helloA = new HelloImpl();
        //生成对象A的动态代理
        IHello helloAProxy = (IHello) JavaDynamicProxy.generateProxy(helloA);
        helloAProxy.hello();

        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", HelloImpl.class.getInterfaces());
        FileUtils.writeByteArrayToFile(new File("./target/DumpProxy.class"), classFile);
    }

    @Test
    public void proxyPerson() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before: " + method);

                proxy.invokeSuper(obj, args);

                System.out.println("after: " + method);

                return null;
            }
        });

        Person proxy = (Person) enhancer.create();

        proxy.getId();

        proxy.getName();

        proxy.setName("Fabricio");
    }

    @Test
    public void testLoggerProxy() {
        Person person = LoggerInterceptor.createProxy(Person.class);

        person.setName("Fabricio");

        System.out.println(person.getName());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void dynamicProxy() {
        Person person = new Person();

        Person proxy = DynamicProxyEntityInterceptor.createProxy(person);

//        DynamicProxyEntityInterceptor.createProxy(proxy);

        proxy.setName("Fabricio");

        assertNull("name is not null", person.getName());
        assertEquals("Fabricio", proxy.getName());

        EntityInterceptor<Person> interceptor = (EntityInterceptor<Person>) proxy;

        Person applied = interceptor.applyChanges();

        assertNotNull("Not apply changes", applied);
        assertEquals("Fabricio", applied.getName());

        proxy.getAddresses().add(new Address());

        assertEquals(0, person.getAddresses().size());
        assertEquals(1, proxy.getAddresses().size());

        proxy.getAddresses().get(0).setAddress("Avenue Brasil");

        assertEquals("Avenue Brasil", proxy.getAddresses().get(0).getAddress());

        person = interceptor.applyChanges();

        assertEquals(1, person.getAddresses().size());
        assertEquals("Avenue Brasil", person.getAddresses().get(0).getAddress());

        proxy.getAddresses().get(0).setAddress("Changed");
        person = interceptor.applyChanges();
        assertEquals(1, person.getAddresses().size());
        assertEquals("Changed", person.getAddresses().get(0).getAddress());

        proxy.getAddresses().remove(0);
        person = interceptor.applyChanges();
        assertEquals(0, person.getAddresses().size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void dynamicProxy2() {
        Person person = new Person();
        person.getAddresses().add(new Address("address"));

        Person proxy = DynamicProxyEntityInterceptor.createProxy(person);

        proxy.getAddresses().get(0).setAddress("new");

        EntityInterceptor<Person> interceptor = (EntityInterceptor<Person>) proxy;

        assertEquals("address", person.getAddresses().get(0).getAddress());
        assertEquals("new", proxy.getAddresses().get(0).getAddress());

        interceptor.applyChanges();

        assertEquals("new", person.getAddresses().get(0).getAddress());
    }
}
