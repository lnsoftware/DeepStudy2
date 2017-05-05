package net.shaneconnolly.jmeter.helpers;

import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContextService;

/**
 * @author connollys
 *         25/02/15.
 */
public class ResultHelper {
    
    public static SampleResult setSuccessfulResults(SampleResult sampleResult, String requestBody){
        sampleResult.setSuccessful(true);
        sampleResult.setResponseMessage("Successfully performed " + "\n" + requestBody);
        sampleResult.setResponseCodeOK(); // 200 code
        sampleResult.setDataType(SampleResult.TEXT);
        return sampleResult;
    }

    public static SampleResult setErrorResults(SampleResult sampleResult, Exception e, String url){
        sampleResult.setSuccessful(false);
        sampleResult.setResponseMessage("Exception: " + e);
        // get stack trace as a String to return as document data
        java.io.StringWriter stringWriter = new java.io.StringWriter();
        e.printStackTrace(new java.io.PrintWriter(stringWriter));
        sampleResult.setResponseMessage(stringWriter.toString() + "\n" + url);
        sampleResult.setDataType(SampleResult.TEXT);
        return sampleResult;
    }

    public static SampleResult setErrorResultsBadResponse(SampleResult sampleResult, String message){
        sampleResult.setSuccessful(false);
        sampleResult.setResponseMessage(String.format("TID: %s BAD RESPONSE: %s", JMeterContextService.getContext().getThreadNum(),  message));
        sampleResult.setDataType(SampleResult.TEXT);
        sampleResult.setResponseCodeOK();
        return sampleResult;
    }
    
}
