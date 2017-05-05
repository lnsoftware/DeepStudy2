package com.hg.test.jmeter.sampler;

import com.hg.test.jmeter.helpers.HttpHelper;
import com.hg.test.jmeter.helpers.LogHelper;
import com.hg.test.jmeter.helpers.SamplerConstants;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;

import java.io.Serializable;


/**
 * @author connollys
 *         25/02/15.
 */
public class GetHomeSampler extends AbstractJavaSamplerClient implements Serializable {
    private static final long serialVersionUID = 5412121L;
    private SampleResult result;

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        String urlString = SamplerConstants.WEB_URL + SamplerConstants.HOME_EP;
        result = HttpHelper.executeHttpGet(urlString, "GetHome");
        verifyResponse(result.getResponseDataAsString());
        return result;
    }

    private void verifyResponse(String response) {
        if (response == null) {
            LogHelper.logInfo("HOME_RESPONSE: IS NULL");
        }
        //LogHelper.logInfo("HOME_RESPONSE: " + response);
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
    }

    public JMeterContext getThreadContext() {
        return JMeterContextService.getContext();
    }

}
