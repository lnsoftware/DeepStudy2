package net.xiayule.mockito;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static net.xiayule.mockito.Mockito.mock;
import static net.xiayule.mockito.Mockito.spy;
import static net.xiayule.mockito.Mockito.when;

public class MockitoTest extends TestCase {

    @Test
    public void testMock2() {
        Calculate cal = mock(Calculate.class);

        Assert.assertEquals(0, cal.add(0, 1));

        when(cal.add(0, 1)).thenReturn(1);
        when(cal.add(1, 1)).thenReturn(2);

        Assert.assertEquals(1, cal.add(0, 1));
        Assert.assertEquals(2, cal.add(1, 1));
    }

    @Test
    public void testSpy() {
        Calculate cal = spy(Calculate.class);

        Assert.assertEquals(3, cal.add(1, 2));

        when(cal.add(1, 2)).thenReturn(0);

        Assert.assertEquals(0, cal.add(1, 2));
    }
}