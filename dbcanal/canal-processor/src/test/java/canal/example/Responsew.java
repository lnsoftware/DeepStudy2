//package canal.example;
//
//import com.google.api.client.http.GenericUrl;
//import com.google.api.client.http.HttpRequest;
//import com.google.api.client.http.HttpRequestFactory;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.JsonObjectParser;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.util.Key;
//
//public class Responsew {
//
//	 private static final int MAX_RESULTS = 3;
//
//	  static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//	  static final JsonFactory JSON_FACTORY = new JacksonFactory();
//
//	public static void main(String[] args) {
//		 HttpRequestFactory requestFactory =
//			        HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
//			            @Override
//			          public void initialize(HttpRequest request) {
//			            request.setParser(new JsonObjectParser(JSON_FACTORY));
//			          }
//			        });
//			    PlusUrl url = PlusUrl.listPublicActivities(USER_ID).setMaxResults(MAX_RESULTS);
////			    url.put("fields", "items(id,url,object(content,plusoners/totalItems))");
//			    HttpRequest request = requestFactory.buildGetRequest(url);
//			    request.
//			    parseResponse(request.execute());
//	}
//
//
//	  public static class PlusUrl extends GenericUrl {
//
//		    public PlusUrl(String encodedUrl) {
//		      super(encodedUrl);
//		    }
//
//
//
//		    /** Maximum number of results. */
//		    @Key
//		    private int maxResults;
//
//		    public int getMaxResults() {
//		      return maxResults;
//		    }
//
//		    public PlusUrl setMaxResults(int maxResults) {
//		      this.maxResults = maxResults;
//		      return this;
//		    }
//
//		    /** Lists the public activities for the given Google+ user ID. */
//		    public static PlusUrl listPublicActivities(String userId) {
//		      return new PlusUrl(
//		          "https://www.googleapis.com/plus/v1/people/" + userId + "/activities/public");
//		    }
//		  }
//
//}
