import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestDemo{

    public static void main(String[] args) {
        Request rqst = Request.method(AdderTester.class,"add");
        Result r = new JUnitCore().run(rqst);
        System.out.println(r.wasSuccessful() );  
    }

    public class AdderTester{
//        @Test
        public void add(){
            assertTrue(true);
//            Adder h = new Adder();
//            assertEquals(3.0, h.add(1, 2), 0.1);
        }
    }

    class Adder {
        public double add(int m,int n){
            return m+n;
        }
    }
}  