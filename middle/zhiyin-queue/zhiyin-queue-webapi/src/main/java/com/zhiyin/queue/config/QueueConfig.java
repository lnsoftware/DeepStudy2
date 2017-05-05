package com.zhiyin.queue.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 队列连接配置信息
 * Created by hg on 2016/3/9.
 */
@Getter
@Configuration
@PropertySource("classpath:config/ali-queue.properties")
public class QueueConfig {

//    private @Value("${acckey}") String acckey;
//    private @Value("${seckey}") String seckey;

    public static String acckey = "q7LPbNcpmdRdT5Rv"; //"Ocz1wInjxU0LyBx4";
    public static String seckey = "Ozx88aEtArWNvz5IJ3ihLn0bmFZjJv";//"SHBpCSCMfoaIuNlriqtqJwiLNuGOsc";
}
