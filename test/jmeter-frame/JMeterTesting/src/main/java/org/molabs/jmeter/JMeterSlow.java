package org.molabs.jmeter;

import java.io.Serializable;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class JMeterSlow extends AbstractJavaSamplerClient implements Serializable {
  private static final long serialVersionUID = 1L;

  public SampleResult runTest(JavaSamplerContext context) {
      SampleResult result = new SampleResult();
      result.sampleStart();
      
      //pi algorithm from: http://www.cs.utsa.edu/~wagner/pi/PiSlow.html
      int N = 100000000;
      double sum = 0.0;      // final sum
      double term;           // term without sign
      double sign = 1.0;     // sign on each term
      for (int k = 0; k < N; k++) {
         term = 1.0/(2.0*k + 1.0);
         sum = sum + sign*term;
         if (k%(N/50) == 0) // print one in 50
            System.out.println ("k: " + k + ", "
               + sum + ", pi: " + sum*4.0);
         sign = -sign;
      }
      System.out.println("Final pi/4 (approx., " +
         N + " terms): " + sum);
      System.out.println("Actual pi/4: " +
         Math.PI/4.0);
      System.out.println("Final pi (approx., " +
         N + " terms): " + sum*4.0);
      System.out.println("Actual pi: " + Math.PI);
      System.out.println(sum);
      
      result.setResponseCode("Okay");
      result.setResponseMessage("JMeterSlow");
      result.setSampleLabel("JMeterSlow");
      result.sampleEnd();
      
      return result;
  }
  
  
  public double calculatePi() {
    int N = 100000000;
    double sum = 0.0;      // final sum
    double term;           // term without sign
    double sign = 1.0;     // sign on each term
    for (int k = 0; k < N; k++) {
       term = 1.0/(2.0*k + 1.0);
       sum = sum + sign*term;
       if (k%(N/50) == 0) // print one in 50
          System.out.println ("k: " + k + ", "
             + sum + ", pi: " + sum*4.0);
       sign = -sign;
    }
    System.out.println("Final pi/4 (approx., " +
       N + " terms): " + sum);
    System.out.println("Actual pi/4: " +
       Math.PI/4.0);
    System.out.println("Final pi (approx., " +
       N + " terms): " + sum*4.0);
    System.out.println("Actual pi: " + Math.PI);
    return sum;
  }
}
