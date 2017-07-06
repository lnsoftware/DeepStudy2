package com.zhiyin.spring.beanregistry.scanpackage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceScannerConfigurer implements BeanFactoryPostProcessor,BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

	private ApplicationContext applicationContext;

	private String scanPackage;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		// left intentionally blank
	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		Scanner scanner = new Scanner(registry);
		scanner.setResourceLoader(this.applicationContext);
		scanner.scan(scanPackage);
	}

	public String getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}
}