import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by hg on 2016/4/7.
 */
@Slf4j
public class LogTest {
    @Test
    public void t(){
        log.debug("de");
        log.info("info");

        byte[] byteArray = new byte[] {87, 79, 87, 46, 46, 46};

        String value = new String(byteArray);

        System.out.println(value);
    }
}
