import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.zhiyin.jagent.AgentConfig;
import com.zhiyin.jagent.transformer.TelemetryTransformer;
import com.zhiyin.jagent.transformer.handler.config.TelemetryConfiguration;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hg on 2017/1/6.
 * http://blog.sina.com.cn/s/blog_72ef7bea0102vvmg.html
 */
public class AgentConfTest {

    /**
     * 测试读取yml配置文件
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        InputStream schemaIS = AgentConfTest.class.getClassLoader().getResourceAsStream(AgentConfig.HANDLER_CONFI_PATH_DEFAULT);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TelemetryConfiguration config = mapper.readValue(schemaIS, TelemetryConfiguration.class);

        Assert.assertNotNull(config);
        Assert.assertNotNull(config.getHandlers());
    }
}
