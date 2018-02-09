import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

/**
 * Created by wangqinghui on 2017/12/21.
 */
public class Jack {

    public static void main(String[] args) throws IOException {

        String json = "{\"name\":\"小民\",\"age\":20}";

        /**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 100; i++) {
            mapper.canSerialize(User.class);
        }
        User user = mapper.readValue(json, User.class);
        System.out.println(user);
    }
}

@Data
class User {
    private String name;
    private Integer age;
}