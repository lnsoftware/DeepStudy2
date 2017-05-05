package org.molabs.jmeter;

import java.io.Serializable;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class JMeterFast extends AbstractJavaSamplerClient implements Serializable {
  private static final long serialVersionUID = 1L;
  public SampleResult runTest(JavaSamplerContext context) {    
    SampleResult result = new SampleResult();
    result.sampleStart();
    for(int i = 0; i < 1000; i++) {
      System.out.println("Testing JMeter Code");
    }
    result.setResponseCode("Okay");
    result.setResponseMessage("JMeterFast");
    result.setSampleLabel("JMeterFast");
    result.sampleEnd();
    
    return result;
  }  
}
