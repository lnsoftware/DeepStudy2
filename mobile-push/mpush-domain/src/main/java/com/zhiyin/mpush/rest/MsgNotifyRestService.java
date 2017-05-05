//package com.hg.msg.rest;
//
//import com.hg.msg.entity.MsgUserNotify;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
///**
// * Hide the access to the microservice inside this local service.
// *
// * @author Paul Chapman
// */
//@Slf4j
//@Service
//public class MsgNotifyRestService {
//
//	@Autowired
//	protected RestTemplate restTemplate;
//
//	protected String serviceUrl = "http://MSG-PROVIDER" ;
//
//	/**
//	 * The RestTemplate works because it uses a custom request-factory that uses
//	 * Ribbon to look-up the service to use. This method simply exists to show
//	 * this.
//	 */
//	@PostConstruct
//	public void demoOnly() {
//		// Can't do this in the constructor because the RestTemplate injection
//		// happens afterwards.
//		log.warn("The RestTemplate request factory is "
//				+ restTemplate.getRequestFactory());
//	}
//
//    public List<MsgUserNotify> getUserNotify(Long uid){
//
//        log.info("byOwnerContains() invoked:  for " + uid);
//        List<MsgUserNotify> accounts = null;
//
//
//        try {
//            accounts = restTemplate.getForObject(serviceUrl
//                    + "/msgnotify/notify/{uid}", List.class, uid);
////            restTemplate.
//        } catch (HttpClientErrorException e) { // 404
//            // Nothing found
//            e.printStackTrace();
//        }
//
//        return accounts;
//
////        if (accounts == null || accounts.length == 0)
////            return null;
////        else
////            return Arrays.asList(accounts);
//    }
//
//}
