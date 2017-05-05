package com.hg.test.jmeter.sampler;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.Serializable;

public class JavaDemoSamplerFast extends AbstractJavaSamplerClient implements Serializable {

    private static final long serialVersionUID = 1L;

    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        for (int i = 0; i < 1000; i++) {
//            System.out.println("Testing JMeter Code");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result.setResponseCode("Bas");
        result.setResponseMessage("JMeterFast");
        result.setSampleLabel("JMeterFast");
        result.sampleEnd();

        return result;
    }
}
