//import com.alibaba.fastjson.JSON;
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Event;
//import com.dianping.cat.message.Transaction;
//import org.apache.http.HttpEntity;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HttpClientTest {
//
//    /**
//     * get方法
//     */
//    public static void httpGet( UmeCatContext context) {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        try {
//            // 创建httpget.
//            HttpGet httpget = new HttpGet("http://localhost:8887/discat");
//            httpget.setHeader( UmeCatContext.HttpCatIds, JSON.toJSONString(context.getMap()) );
//
//            System.out.println("executing request " + httpget.getURI());
//            // 执行get请求.
//            CloseableHttpResponse response = httpclient.execute(httpget);
//            try {
//                // 获取响应实体
//                HttpEntity entity = response.getEntity();
//
//                System.out.println("--------------------------------------");
//                // 打印响应状态
//                System.out.println(response.getStatusLine());
//                if (entity != null) {
//                    // 打印响应内容长度
//                    System.out.println("Response content length: " + entity.getContentLength());
//                    // 打印响应内容
//                    System.out.println("Response content: " + EntityUtils.toString(entity));
//                }
//                System.out.println("------------------------------------");
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args){
//
//     while(true){
//         Transaction tr = Cat.getProducer().newTransaction("groupName_Call", "dataName");
//
//         Cat.logEvent("eeeServer", "local", Event.SUCCESS, "ip=loc");
//
//         UmeCatContext context = new UmeCatContext();
//         Cat.logRemoteCallClient(context);
//
//         httpGet( context);
//
//         tr.setStatus(Transaction.SUCCESS);
//         tr.complete();
//
//         try {
//             Thread.sleep(1000);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }
//    }
//
//
//
//}