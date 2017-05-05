package com.zhiyin.bootx.thirdenv.server;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Test;

public class YamlTest{

    @Test
    public void ymlTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            User user = mapper.readValue(new File(YamlTest.class.getResource("/test-user.yml").getPath()), User.class);
            System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Properties properties = mapper.readValue(new File(YamlTest.class.getResource("/test-properties.yml").getPath()), Properties.class);
        System.out.println(ReflectionToStringBuilder.toString(properties,ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(properties.get("name"));
        System.out.println(JSON.toJSONString(properties));
    }
}

@Data
class User {
    private String name;
    private int age;
    private Map<String, String> address;
    private String[] roles;
}