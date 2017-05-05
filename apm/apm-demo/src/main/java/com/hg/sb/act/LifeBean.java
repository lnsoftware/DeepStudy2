//package com.hg.sb.act;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.BeanFactoryAware;
//import org.springframework.beans.factory.BeanNameAware;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//
//@Slf4j
//public class LifeBean implements BeanFactoryAware, BeanNameAware,
//		InitializingBean, DisposableBean, BeanPostProcessor,
//		BeanFactoryPostProcessor  {
//
//	private static boolean runOnce = false;
//	private String providerMark;
//	private String providerPort;
//
//	/**
//	 * bean被初始化之前调用
//	 */
//	@Override
//	public Object postProcessBeforeInitialization(Object bean, String beanName)
//			throws BeansException {
//		// TODO Auto-generated method stub
//		return bean;
//	}
//
//	/**
//	 * 设置bean属性之后调用
//	 */
//	@Override
//	public void afterPropertiesSet() throws Exception {
//
//		if (!runOnce) {
//			// 对于需要在程序启动运行的线程可以写到此处
//			log.info( "运行需要在程序启动时运行的线程");
//
//			runOnce = true;
//		}
//	}
//
//	/**
//	 * bean实例化之后调用
//	 */
//	@Override
//	public Object postProcessAfterInitialization(Object bean, String beanName)
//			throws BeansException {
//
//		if (bean instanceof  RequestMappingHandlerMapping) {
//			Map<RequestMappingInfo, HandlerMethod> handlerMethods = ((RequestMappingHandlerMapping) bean).getHandlerMethods();
//			Iterator<Entry<RequestMappingInfo, HandlerMethod>> iterator = handlerMethods.entrySet().iterator();
//			ApplicationContext applicationContext = ((RequestMappingHandlerMapping) bean).getApplicationContext();
//			String applicationName = applicationContext.getApplicationName();
//			while (iterator.hasNext()) {
//				Entry<RequestMappingInfo, HandlerMethod> next = iterator.next();
//				RequestMappingInfo key = next.getKey();
//				HandlerMethod value = next.getValue();
////				UmeServiceRegister umeTemplate = value.getBeanType().getAnnotation(UmeServiceRegister.class);
////				if(umeTemplate != null){
////						ProviderConfigBean providerConfigBean = new ProviderConfigBean();
////						//端口和/remoting 需要在进行修改
////						String uri = key.getPatternsCondition().toString().replace("[", "").replace("]", "");
////						String url = "http://" + ip + ":" + providerPort + applicationName  + uri;
////						//diamond的groupid和dataid不支持"/"
////						providerConfigBean.setMasterServiceName(applicationName.replaceAll("/", ""));
////						//bean name 首字母大写
////						char[] charArray = value.getBean().toString().toCharArray();
////						charArray[0] -= 32;
////						providerConfigBean.setSubServiceName(String.valueOf(charArray) + "." + value.getMethod().getName());
////						providerConfigBean.setUrl(url);
////						providerConfigBean.setMark(this.providerMark);
////						serviceList.add(providerConfigBean);
////						System.out.println("url:" + url);
////
////					}
//
//			}
//		}
//
//		if (bean instanceof BeanNameUrlHandlerMapping) {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//			String today = df.format(new Date());
//
//			File file = new File("/opt/applog/MskyLog/" + ip + "_jbossBean." + today);
//			System.out.println("file path:" + file.getAbsolutePath());
//			FileOutputStream fileOutputStream = null;
//			try {
//				fileOutputStream = new FileOutputStream(file);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//			Map<String, Object> handlerMap = ((BeanNameUrlHandlerMapping) bean).getHandlerMap();
//			Iterator<Entry<String, Object>> iterator = handlerMap.entrySet().iterator();
//			ApplicationContext applicationContext = ((BeanNameUrlHandlerMapping) bean).getApplicationContext();
//			String applicationName = applicationContext.getApplicationName();
//			String heartBeatKey = null;
//			InitProviderBeanList initProviderBeanList = new InitProviderBeanList();
//			while (iterator.hasNext()) {
//				ProviderConfigBean providerConfigBean = new ProviderConfigBean();
//				Entry<String, Object> next = iterator.next();
//				String beanNameInXml = next.getKey();
//				//端口和/remoting 需要在进行修改
//				String url = "http://" + ip + ":" + providerPort + applicationName + "/remoting" + beanNameInXml;
//				//diamond的groupid和dataid不支持"/"
//				providerConfigBean.setMasterServiceName(applicationName.replaceAll("/", ""));
//				providerConfigBean.setSubServiceName(beanNameInXml.replaceAll("/",  ""));
//
//				providerConfigBean.setUrl(url);
//				providerConfigBean.setMark(this.providerMark);
//
//				String beanToJson = ServiceRegisterUtil.beanToJson(providerConfigBean);
//
//				if (beanNameInXml.equals("/serviceHeartbeat")) {
//					heartBeatKey = url;
//				}else {
//					serviceList.add(providerConfigBean);
//				}
//				try {
//					fileOutputStream.write((beanToJson + "\r\n").getBytes());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("url:" + url);
//
//			}
//			//在BeanNameUrlHandlerMapping初始化完成后统一向redis写url
//			Map<String, String> initProviderMap = new HashMap<String, String>();
//			initProviderBeanList.setInitProviderConfigBeanList(serviceList);
//			initProviderMap.put(heartBeatKey, ServiceRegisterUtil.beanToJson(initProviderBeanList));
//			RedisWrap2.hmsetDate(RegisterCode.INIT_PROVIDER_SERVICE_LIST, initProviderMap);
//			RedisWrap2.hmsetDate(RegisterCode.All_PROVIDER_HEART_SERVICE_LIST, initProviderMap);
////			http://122.119.120.35:8180/MskyAccess/remoting/access/heartbeat
//
//			System.out.println(handlerMap.size());
//			System.out.println("service register size:" + serviceList.size());
//		}
//		return bean;
//	}
//
//	@Override
//	public void postProcessBeanFactory(
//			ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		if (beanFactory != null){
////			System.out.println(beanFactory.getBeanDefinitionCount());
//			LogUtil.info(this.getClass(),
//					"SpringContext类的数量＝" + beanFactory.getBeanDefinitionCount());
//		}
//	}
//
//	@Override
//	public void setBeanName(String name) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void destroy() throws Exception {
//		LogUtil.info(this.getClass(), "Spring context destroy!");
//	}
//
//	public String getProviderMark() {
//		return providerMark;
//	}
//
//	public void setProviderMark(String providerMark) {
//		this.providerMark = providerMark;
//	}
//
//	public String getProviderPort() {
//		return providerPort;
//	}
//
//	public void setProviderPort(String providerPort) {
//		this.providerPort = providerPort;
//	}
//
//
//}
