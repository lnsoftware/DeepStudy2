package use;

import org.junit.Test;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

public class AppTest {
        @Test
        public void testOnDev()
        {
            System.setProperty("ENV", "DEV");
            assumeTrue("DEV".equals(System.getProperty("ENV")));
        }
          
        @Test
       public void testOnProd()
        {
            System.setProperty("ENV", "PROD");
            assumeFalse("DEV".equals(System.getProperty("ENV"))); 
        }
    }