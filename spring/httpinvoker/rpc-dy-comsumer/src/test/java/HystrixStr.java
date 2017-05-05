import com.alibaba.fastjson.JSON;
import com.zhiyin.rpc.shi.demo.rpc.CommandKeyUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangqinghui on 2016/6/24.
 */
public class HystrixStr {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "hystrix-conf-default.properties";

            inputStream =HystrixStr.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


            Map<String, String> map = new LinkedHashMap<String, String>();


            for (String key : prop.stringPropertyNames()) {
                map.put(key, prop.getProperty(key));
            }

            String str = JSON.toJSONString(map);

            System.out.println(JSON.toJSONString(map));

            CommandKeyUtil.setIndex(str);



        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }

    }

}
