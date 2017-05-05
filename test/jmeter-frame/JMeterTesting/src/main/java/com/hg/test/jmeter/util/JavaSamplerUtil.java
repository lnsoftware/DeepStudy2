package com.hg.test.jmeter.util;

import com.hg.test.jmeter.helpers.SamplerHelper;
import com.hg.test.jmeter.sampler.JavaDemoSamplerFast;
import org.apache.jmeter.protocol.java.sampler.JavaSampler;

/**
 * Created by hg on 2016/5/11.
 */
public class JavaSamplerUtil {

    public static JavaSampler demoFast(){
        return SamplerHelper.createSampler("demoFast", JavaDemoSamplerFast.class.getName());
    }
}
