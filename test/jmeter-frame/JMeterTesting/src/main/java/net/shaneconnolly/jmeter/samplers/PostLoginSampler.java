package net.shaneconnolly.jmeter.samplers;

import net.shaneconnolly.jmeter.helpers.HttpHelper;
import net.shaneconnolly.jmeter.helpers.LogHelper;
import net.shaneconnolly.jmeter.helpers.SamplerConstants;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;

import java.io.Serializable;

/**
 * @author connollys
 *         25/02/15.
 */
public class PostLoginSampler extends AbstractJavaSamplerClient implements Serializable {
    private static final long serialVersionUID = 234234;
    private JMeterVariables threadVars;
    private String requestBody;
    private SampleResult result;

    private void setUp() {
        threadVars = JMeterContextService.getContext().getVariables();
        requestBody = "{\"username\" : \"tommy\",\n" +
                "\"password\" : \"123\"}";
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        String urlString = SamplerConstants.API_URL + SamplerConstants.LOGIN_EP;
        setUp();
        result = HttpHelper.executeHttpPost(urlString, "POST_LOGIN", requestBody);
        verifyResponse(result.getResponseDataAsString());
        return result;
    }

    private void verifyResponse(String json) {
        LogHelper.logInfo("LoginResponse : " + json);
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
