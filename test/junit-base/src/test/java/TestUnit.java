import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUnit{
    public TestUnit(){    }  
    @Test
    public void addx(int x){  
        assertEquals(5, 1+x);  
    }  
    @Test  
    public void add(){          
        assertEquals(5.0,4.0, 0.1);  
    }  
    @Test  
    public void hello(){          
        assertEquals(5.0,4.0, 0.1);  
    }  
}  