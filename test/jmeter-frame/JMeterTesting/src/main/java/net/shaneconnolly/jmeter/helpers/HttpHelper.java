package net.shaneconnolly.jmeter.helpers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;


/**
 * @author connollys
 *         25/02/15.
 */
public class HttpHelper {

    public static SampleResult executeHttpPost(String url, String resultLabel, String requestBody) {
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
        DefaultHttpClient httpClient = getHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity flights = new StringEntity(requestBody);
            httpPost.setEntity(flights);
            LogHelper.logInfo("Executing request " + httpPost.getRequestLine());
            String responseBody = httpClient.execute(httpPost, getResponseHandler(result));
            result = ResultHelper.setSuccessfulResults(result, requestBody);
            result.setResponseData(responseBody.getBytes());
        } catch (Exception e) {
            result = ResultHelper.setErrorResults(result, e, requestBody);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
            result.sampleEnd();
            result.setSampleLabel(resultLabel);
        }
        return result;
    }

    public static SampleResult executeHttpGet(String url, String resultLabel) {
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
        DefaultHttpClient httpClient = getHttpClient();
        try {
            HttpGet httpget;
            httpget = new HttpGet(url);
            httpget.addHeader("Content-Type", "application/json");
            String responseBody = httpClient.execute(httpget, getResponseHandler(result));
            LogHelper.logInfo("Executing request " + httpget.getRequestLine());
            result.setResponseData(responseBody.getBytes());
            result = ResultHelper.setSuccessfulResults(result, url);
        } catch (Exception e) {
            result = ResultHelper.setErrorResults(result, e, url);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
            result.sampleEnd();
            result.setSampleLabel(resultLabel);
        }
        return result;
    }


    public static ResponseHandler<String> getResponseHandler(final SampleResult result) {
        return new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                result.setResponseCode(String.valueOf(status));
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    ResultHelper.setErrorResultsBadResponse(result, "Response BAD CODE");
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
    }

    /**
     * @return
     */
    public static DefaultHttpClient getHttpClient() {
        return new DefaultHttpClient();

    }
}
