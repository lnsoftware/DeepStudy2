package com.zhiyin.cglib.cando;

import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Before;

/**
 * Created by hg on 2017/6/26.
 */
public class BaseTest {
    @Before
    public void be(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./target/cglib_proxy");

    }
}
