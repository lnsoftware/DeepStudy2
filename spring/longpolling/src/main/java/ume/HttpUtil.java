package ume;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.io.BaseEncoding;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class HttpUtil {

  private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

  private Gson gson;
  private String basicAuth;

  /**
   * Constructor.
   */
  public HttpUtil() {
    gson = new Gson();
    try {
      basicAuth = "Basic " + BaseEncoding.base64().encode("user:".getBytes("UTF-8"));
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    }
  }

  public <T> HttpResponse<T> doGet(HttpRequest httpRequest, final Class<T> responseType) {
    Function<String, T> convertResponse = new Function<String, T>() {
      @Override
      public T apply(String input) {
        return gson.fromJson(input, responseType);
      }
    };

    return doGetWithSerializeFunction(httpRequest, convertResponse);
  }


  public <T> HttpResponse<T> doGet(HttpRequest httpRequest, final Type responseType) {
    Function<String, T> convertResponse = new Function<String, T>() {
      @Override
      public T apply(String input) {
        return gson.fromJson(input, responseType);
      }
    };

    return doGetWithSerializeFunction(httpRequest, convertResponse);
  }

  private <T> HttpResponse<T> doGetWithSerializeFunction(HttpRequest httpRequest,
                                                         Function<String, T> serializeFunction) {
    InputStream is = null;
    int statusCode;
    try {
      HttpURLConnection conn = (HttpURLConnection) new URL(httpRequest.getUrl()).openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Authorization", basicAuth);

      int connectTimeout = httpRequest.getConnectTimeout();
      if (connectTimeout < 0) {
        connectTimeout = 1000;
      }

      int readTimeout = httpRequest.getReadTimeout();
      if (readTimeout < 0) {
        readTimeout = 100000;
      }

      conn.setConnectTimeout(connectTimeout);
      conn.setReadTimeout(readTimeout);

      conn.connect();

      statusCode = conn.getResponseCode();

      if (statusCode == 200) {
        is = conn.getInputStream();
        String content = CharStreams.toString(new InputStreamReader(
            is, Charsets.UTF_8));
        return new HttpResponse<>(statusCode, serializeFunction.apply(content));
      }

      if (statusCode == 304) {
        return new HttpResponse<>(statusCode, null);
      }

    } catch (Throwable ex) {
//      ex.printStackTrace();
      log.error("poll response error.",ex);
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException ex) {
          // ignore
        }
      }
    }

    throw new RuntimeException(
        String.format("Get operation failed for %s", httpRequest.getUrl()));
  }

}
