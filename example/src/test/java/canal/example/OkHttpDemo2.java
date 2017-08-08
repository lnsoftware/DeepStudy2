package canal.example;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import okio.BufferedSink;

public class OkHttpDemo2 {

	private static Logger log = LoggerFactory.getLogger(OkHttpDemo2.class);
	private final static OkHttpClient client = new OkHttpClient();
	private final Gson gson = new Gson();

	public static final MediaType MEDIA_TYPE_MARKDOWN
    = MediaType.parse("application/json; charset=utf-8");
	
	
//	static String SearcherHttp = "http://123.57.230.238:8080/searcher/";
	static String SearcherHttp = "http://192.168.1.108:8080/searcher/";
	public void run() throws Exception {
		Request request = new Request.Builder().url("http://123.57.230.238:8080/searcher/contents/search?query=sss")
				.build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()){
			throw new IOException("Unexpected code " + response);
		}
		log.debug("response info:{}",response);
//		System.out.println(responseStr);
		String responseStr = response.body().string();
//		System.out.println(responseStr);
		JSONObject obj = JSON.parseObject(responseStr);
		
		 String metaObjStr = obj.getString("meta");
		 
		Meta meta = JSON.parseObject(metaObjStr, Meta.class);
		log.info("meta code:{}",meta.code);

	}
	
	
	public static void post() throws Exception {
		Content c = new Content();
		c.setId(4162);
		String re = JSON.toJSONString(c);
		
		
		RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, re);
		 
		    Request request = new Request.Builder()
		        .url(SearcherHttp + "contents/del")
		        .post(requestBody)
		        .build();
		
	
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()){
			throw new IOException("Unexpected code " + response);
		}
		log.debug("response info:{}",response);

		String responseStr = response.body().string();
		JSONObject obj = JSON.parseObject(responseStr);
	
		String metaObjStr = obj.getString("meta");
		 
		Meta meta = JSON.parseObject(metaObjStr, Meta.class);
		log.info("meta code:{}",meta.code);

	}
	
	
	public static void add() throws Exception {
		Content c = new Content();
		c.setId(4162);
		c.setTitle("test http add");
		c.setDescription("dfadfdsaa");
		
		String re = JSON.toJSONString(c);
		
		
		RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, re);
		 
		    Request request = new Request.Builder()
		        .url(SearcherHttp + "contents/add")
		        .post(requestBody)
		        .build();
		
	
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()){
			throw new IOException("Unexpected code " + response);
		}
		log.debug("response info:{}",response);

		String responseStr = response.body().string();
		JSONObject obj = JSON.parseObject(responseStr);
	
		String metaObjStr = obj.getString("meta");
		 
		Meta meta = JSON.parseObject(metaObjStr, Meta.class);
		log.info("meta code:{}",meta.code);

	}

	static class Content{
		long id;
		String title;
		Float duration;
		String document;
		String description;
		String tag;
		String savePath;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Float getDuration() {
			return duration;
		}
		public void setDuration(Float duration) {
			this.duration = duration;
		}
		public String getDocument() {
			return document;
		}
		public void setDocument(String document) {
			this.document = document;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		public String getSavePath() {
			return savePath;
		}
		public void setSavePath(String savePath) {
			this.savePath = savePath;
		}
	}

	public static void main(String[] args) throws Exception {

		new OkHttpDemo2().add();
	}

	static class Meta{
		String code;
		String message;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	}
}
