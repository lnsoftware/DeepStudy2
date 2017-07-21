package com.zhiyin.hystrix.core.property;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;
import com.netflix.config.ConfigurationManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * Created by hg on 2016/10/26.
 */
@Slf4j
public class LoadHystrixConfig {

    public static void load() {
        InputStream info = LoadHystrixConfig.class.getResourceAsStream("/hystrix.config.properties");
        try {
            List<String> lines = IOUtils.readLines(info, "utf-8");

            lines = Optional.fromNullable(lines).or(new ArrayList<String>());
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("#")) {
                    continue;
                }
                String[] keyVal = line.split("=");
                if (keyVal == null || keyVal.length != 2) {
                    continue;
                }
                ConfigurationManager.getConfigInstance().setProperty(keyVal[0], keyVal[1]);
            }
            log.info("config:" + JSON.toJSONString(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        System.out.println();
        LoadHystrixConfig.load();

    }
}
