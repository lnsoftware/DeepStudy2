package com.zhiyin.spring.beanregistry.scanpackage.test;

import com.zhiyin.spring.beanregistry.scanpackage.ServiceScannerConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class CustomizeScanTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
		UserService injectClass = annotationConfigApplicationContext.getBean(UserService.class);
		System.out.println(injectClass.hello("HG"));
	}

}

@Configuration
class Config {

	@Bean
	public ServiceScannerConfigurer scannerConfigurer(){
		ServiceScannerConfigurer configurer = new ServiceScannerConfigurer();
		configurer.setScanPackage(CustomizeScanTest.class.getPackage().getName());
		return configurer;
	}
}
