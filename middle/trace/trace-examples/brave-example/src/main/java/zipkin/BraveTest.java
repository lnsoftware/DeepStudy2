package zipkin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.github.kristofa.brave.*;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.twitter.zipkin.gen.Endpoint;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.okhttp3.OkHttpSender;

/**
 * https://my.oschina.net/u/223522/blog/736852
 */
public class BraveTest {

    private static Brave brave = null;
    private static Brave brave2 = null;

    private static void braveInit(){

        String server = "localhost:9411";
        Reporter<Span> reporter = null ;
        reporter = AsyncReporter.builder(OkHttpSender.create("http://"+server +"/api/v1/spans")).build();

        brave = new Brave.Builder("appserver").reporter( reporter ).build();
        brave2 = new Brave.Builder("datacenter").reporter( reporter ).build();
    }

    static class Task {
        String name;
        SpanId spanId;
        public Task(String name, SpanId spanId) {
            super();
            this.name = name;
            this.spanId = spanId;
        }
    }

    public static void main(String[] args) throws Exception {
        for(int i=1;i<20;i++){
            test();
        }
   }

        public static void test() throws Exception {
        braveInit();

        final BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(10);
        Thread thread = new Thread(){
            public void run() {
                while (true) {
                    try {
                        Task task = queue.take();
                        dcHandle(task.name, task.spanId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        {
            ServerRequestInterceptor serverRequestInterceptor = brave.serverRequestInterceptor();
            ServerResponseInterceptor serverResponseInterceptor = brave.serverResponseInterceptor();
            ClientRequestInterceptor clientRequestInterceptor = brave.clientRequestInterceptor();
            ClientResponseInterceptor clientResponseInterceptor = brave.clientResponseInterceptor();

            serverRequestInterceptor.handle(new ServerRequestAdapterImpl("group_data"));

            ClientRequestAdapterImpl clientRequestAdapterImpl = new ClientRequestAdapterImpl("get_radio_list");
            clientRequestInterceptor.handle(clientRequestAdapterImpl);
//            System.out.println("tl:" + ThreadLocalServerClientAndLocalSpanState.);
            queue.offer(new Task("get_radio_list", clientRequestAdapterImpl.getSpanId()));
            Thread.sleep(10);
            clientResponseInterceptor.handle(new ClientResponseAdapterImpl());

            clientRequestAdapterImpl = new ClientRequestAdapterImpl("get_user_list");
            clientRequestInterceptor.handle(clientRequestAdapterImpl);
            queue.offer(new Task("get_user_list", clientRequestAdapterImpl.getSpanId()));
            Thread.sleep(10);
            clientResponseInterceptor.handle(new ClientResponseAdapterImpl());

            clientRequestAdapterImpl = new ClientRequestAdapterImpl("get_program_list");
            clientRequestInterceptor.handle(clientRequestAdapterImpl);
            queue.offer(new Task("get_program_list", clientRequestAdapterImpl.getSpanId()));
            Thread.sleep(10);
            clientResponseInterceptor.handle(new ClientResponseAdapterImpl());

            serverResponseInterceptor.handle(new ServerResponseAdapterImpl());
        }
        System.out.println("end");
        Thread.sleep(3000);
    }

    public static void dcHandle(String spanName, SpanId spanId){
        ServerRequestInterceptor serverRequestInterceptor = brave2.serverRequestInterceptor();
        ServerResponseInterceptor serverResponseInterceptor = brave2.serverResponseInterceptor();
        serverRequestInterceptor.handle(new ServerRequestAdapterImpl(spanName, spanId));

        serverResponseInterceptor.handle(new ServerResponseAdapterImpl());
    }

    static class ServerRequestAdapterImpl implements ServerRequestAdapter {

        Random randomGenerator = new Random();
        SpanId spanId;
        String spanName;

        ServerRequestAdapterImpl(String spanName){
            this.spanName = spanName;
            long startId = randomGenerator.nextLong();
            SpanId spanId = SpanId.builder().spanId(startId).traceId(startId).parentId(startId).build();
            this.spanId = spanId;
            System.out.println("sr:" + spanId);
        }

        ServerRequestAdapterImpl(String spanName, SpanId spanId){
            this.spanName = spanName;
            this.spanId = spanId;
            System.out.println("sr span:" + spanId);
        }

        @Override
        public TraceData getTraceData() {
            if (this.spanId != null) {
                return TraceData.create(this.spanId);
            }
            long startId = randomGenerator.nextLong();
            SpanId spanId = SpanId.builder().spanId(startId).traceId(startId).parentId(startId).build();
            return TraceData.create(spanId);
        }

        @Override
        public String getSpanName() {
            return spanName;
        }

        @Override
        public Collection<KeyValueAnnotation> requestAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioid", "123");
            collection.add(kv);
            return collection;
        }

    }

    static class ServerResponseAdapterImpl implements ServerResponseAdapter {

        @Override
        public Collection<KeyValueAnnotation> responseAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioid", "165646485468486364");
            collection.add(kv);
            return collection;
        }

    }

    static class ClientRequestAdapterImpl implements ClientRequestAdapter {

        String spanName;
        SpanId spanId;

        ClientRequestAdapterImpl(String spanName){
            this.spanName = spanName;
        }

        public SpanId getSpanId() {
            return spanId;
        }

        @Override
        public String getSpanName() {
            return this.spanName;
        }

        @Override
        public void addSpanIdToRequest(SpanId spanId) {
            //记录传输到远程服务
            System.out.println("cr span:" + spanId);
            if (spanId != null) {
                this.spanId = spanId;
                System.out.println(String.format("trace_id=%s, parent_id=%s, span_id=%s", spanId.traceId, spanId.parentId, spanId.spanId));
            }
        }

        @Override
        public Collection<KeyValueAnnotation> requestAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioid", "165646485468486364");
            collection.add(kv);
            return collection;
        }

        @Override
        public Endpoint serverAddress() {
            return null;
        }

    }

    static class ClientResponseAdapterImpl implements ClientResponseAdapter {

        @Override
        public Collection<KeyValueAnnotation> responseAnnotations() {
            Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
            KeyValueAnnotation kv = KeyValueAnnotation.create("radioname", "火星人1");
            collection.add(kv);
            return collection;
        }

    }
}