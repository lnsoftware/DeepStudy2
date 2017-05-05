//package net.shaneconnolly.jmeter.samplers;
//
//import com.alibaba.fastjson.JSON;
//
//import net.shaneconnolly.jmeter.helpers.SamplerConstants;
//import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
//import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
//import org.apache.jmeter.samplers.SampleResult;
//import org.apache.jmeter.threads.JMeterContext;
//import org.apache.jmeter.threads.JMeterContextService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.io.Serializable;
//
//
///**
// * @author connollys
// *         25/02/15.
// */
//public class UmeSampler extends AbstractJavaSamplerClient implements Serializable {
//    private static final long serialVersionUID = 5412121L;
//    private SampleResult result;
//    ICityCodeSVC cityCodeSVC;
//
//    @Override
//    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
//        String urlString =  SamplerConstants.WEB_URL + SamplerConstants.HOME_EP;
//
//        try {
//            CityCodeInfoBean tmp = cityCodeSVC.getCityCodeInfoBeanByCityCode("SHA");
//
//            System.out.print("remote response:" + JSON.toJSONString(tmp));
//
//        }catch (Exception e){
//            System.out.print( "error" );
//
//        }
//        return result;
//    }
//
//    public void testGetContext() {
//
//
//        new JettyServer().init();
//
//        Runnable cityCodeSVCThread = new CityCodeSVCThread(cityCodeSVC);
//        new Thread(cityCodeSVCThread).start();
//
//
////        Runnable aircorpSVCThread = new AircorpSVCThread(aircorpSVC);
////        new Thread(aircorpSVCThread).start();
//
//
//
//        new Thread(new MetticsThread()).start();
//
//
//        try {
//            Thread.sleep(Long.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);
////        for (int i = 0; i < 10; i++) {
////            executor.execute(new Thread(new ThreadPoolTest(), "TestThread".concat(""+i)));
////            int threadSize = queue.size();
////            System.out.println("线程队列大小为-->"+threadSize);
////        }
////        executor.shutdown();
//
//
//    }
//
//
//    @Override
//    public void setupTest(JavaSamplerContext context) {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(
//                new String[] {"applicationContext-infocenter-client.xml"});
//
////        IAircorpSVC aircorpSVC = (IAircorpSVC)ctx.getBean("aircorpSVCInvoker");
//
//         cityCodeSVC = ctx.getBean("cityCodeSVCInvoker", ICityCodeSVC.class);
//    }
//
//    @Override
//    public void teardownTest(JavaSamplerContext context) {
//    }
//
//    public JMeterContext getThreadContext() {
//        return JMeterContextService.getContext();
//    }
//
//}
