package com.hg.dubbo.study.url;

import com.alibaba.dubbo.common.URL;
import org.junit.Test;

/**
 * Created by hg on 2017/4/22.
 */
public class UrlTest {



    @Test
    public void test() {
            URL url = new URL("dubbo", "admin","123456",
                    "localhost", 8080, "/index.html",
                    "key1", "value1", "key2", "value2");
            System.out.println(url.getIp());
            System.out.println(url.getAuthority());
            System.out.println(url.toFullString());
            System.out.println(url.toIdentityString());
            System.out.println(url.toParameterString());
            System.out.println(url.toString());
        }

}
