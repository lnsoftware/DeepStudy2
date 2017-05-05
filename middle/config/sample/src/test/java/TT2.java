import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by hg on 2016/10/26.
 */
@Slf4j
public class TT2 {
    public static void main(String[] args) throws ConfigurationException, InterruptedException {


        String fileName = TT2.class.getResource("/").getPath() + "config.properties";
        ConcurrentMapConfiguration configFromPropertiesFile =
                new ConcurrentMapConfiguration(new PropertiesConfiguration(fileName));

//        ConcurrentCompositeConfiguration finalConfig = new ConcurrentCompositeConfiguration();
//        finalConfig.addConfiguration(configFromPropertiesFile, "fileConfig");
//        ConfigurationManager.install(finalConfig);

        DynamicPropertyFactory.initWithConfigurationSource(configFromPropertiesFile);

        while (true) {
            DynamicLongProperty timeToWait =
                    DynamicPropertyFactory.getInstance().getLongProperty("lock.waitTime", 1000);
            log.info(timeToWait.get() + " " + configFromPropertiesFile.getString("lock.waitTime"));
            Thread.sleep(30000);
        }

    }
}
