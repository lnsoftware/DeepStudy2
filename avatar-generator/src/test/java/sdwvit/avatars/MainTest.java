package sdwvit.avatars;

import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertTrue;

public class MainTest {

    @Test
    public void testMain() throws Exception {
        String[] args = {"1", "100", "0.5", "0.5f", "20", "true"};
        Main.main(args);
        File file = new File("./images/img5.png");
        assertTrue(file.exists() && file.length() > 0);
        file.deleteOnExit();
    }
}