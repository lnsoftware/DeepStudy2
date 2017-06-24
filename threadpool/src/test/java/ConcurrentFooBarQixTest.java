
import com.monitor.jmx.ConcurrentFooBarQix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentFooBarQixTest {

    private ConcurrentFooBarQix concurrentFooBarQix;

    @Before
    public void setUp() {
        concurrentFooBarQix = new ConcurrentFooBarQix(0);
    }

    @Test
    public void shoud_return_number() {

        Assert.assertEquals("1", concurrentFooBarQix.fooBarQix(1));
    }

    @Test public void should_return_foo() {
        Assert.assertEquals("FOOFOO",concurrentFooBarQix.fooBarQix(3));
        Assert.assertEquals("FOO",concurrentFooBarQix.fooBarQix(6));
        Assert.assertEquals("FOO",concurrentFooBarQix.fooBarQix((13)));
    }

    @Test public void should_return_bar() {
        Assert.assertEquals("BARBAR",concurrentFooBarQix.fooBarQix(5));
        Assert.assertEquals("BAR",concurrentFooBarQix.fooBarQix(10));
        Assert.assertEquals("BAR",concurrentFooBarQix.fooBarQix(52));
    }

    @Test public void should_return_qix() {
        Assert.assertEquals("QIXQIX",concurrentFooBarQix.fooBarQix(7));
    }
}