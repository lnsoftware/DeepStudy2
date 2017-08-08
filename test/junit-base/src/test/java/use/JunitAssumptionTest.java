package use;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
 
public class JunitAssumptionTest {
 
    @Before
    public void setUp() {
        String versionNumber = "77"; //Get it from configuration on runtime
        Assume.assumeTrue(Integer.valueOf(versionNumber) == 7);
    }
 
    @Test
    public void testIfVersioonGreaterThan4()
    {
        System.out.println("Test executed");
    }
 
}