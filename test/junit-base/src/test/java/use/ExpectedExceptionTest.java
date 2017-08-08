package use;

import org.junit.Test;
 
public class ExpectedExceptionTest
{
    //This test case fails because it was expecting ArithmeticException
    @Test(expected = ArithmeticException.class)
    public void expectArithmeticException()
    {
        System.out.println("Everything was fine here !!");
    }
 
    //This test case fails because it was expecting ArithmeticException
    @Test(expected = ArithmeticException.class)
    public void expectArithmeticException2()
    {
        throw new NullPointerException();
    }
 
    //This test case passes because it was expecting NullPointerException
    @Test(expected = NullPointerException.class)
    public void expectNullPointerException()
    {
        //some code which throw NullPointerException in run time
        throw new NullPointerException();
    }
}