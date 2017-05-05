//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
//public class SampleIntegrationTest {
//
////    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private static final Logger logger = LoggerFactory.getLogger(SampleIntegrationTest.class);
//
//
//
//    @Test
//    public void test(){
//
//        for(int i = 0; i<500; i++){
//            Thread t = new Thread(new Run());
//            t.start();
//        }
//    }
//
//
//    class Run implements Runnable{
//
//        public void run() {
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder()
//                    .url("http://localhost:9090/test/message.html")
//                    .build();
//            try {
//                Response response = client.newCall(request).execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
////    @Test
////    public void testSampleCallToEndpoint() throws Exception {
////        HttpClient<String, ByteBuf> client = RxNetty.<String, ByteBuf>newHttpClientBuilder("localhost", 8889).build();
////        HttpClientRequest<String> request = HttpClientRequest.create(HttpMethod.POST, "/message");
////
////        String message = objectMapper.writeValueAsString(new Message("1", "Ping", false, 0));
////
////        request.withRawContent(message, StringTransformer.DEFAULT_INSTANCE);
////        CountDownLatch l = new CountDownLatch(1);
////
////        client.submit(request)
////                .flatMap(response -> response.getContent())
////                .map(data -> data.toString(Charset.defaultCharset()))
////                .subscribe(s -> {
////                    logger.info(s);
////                    l.countDown();
////                });
////        l.await();
////    }
//}