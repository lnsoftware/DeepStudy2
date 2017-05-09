package base;

import br.com.fabriciodeb.sample.IHello;

public class HelloProxyImpl implements IHello {

    private IHello inner;

    public HelloProxyImpl(IHello inner) {
        this.inner = inner;
    }

    @Override
    public void hello() {
        doSomethingBefore();
        
        inner.hello();
        
        doSomethingAfter();
    }

    private void doSomethingBefore() {
        System.out.println("HelloProxyImpl: Before hello()...");
    }

    private void doSomethingAfter() {
        System.out.println("HelloProxyImpl: After hello()...");
    }

}