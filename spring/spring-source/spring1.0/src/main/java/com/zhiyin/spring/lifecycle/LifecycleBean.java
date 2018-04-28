package com.zhiyin.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * Simple test of BeanFactory initialization
 * and lifecycle callbacks.
 */
@Slf4j
public class LifecycleBean implements BeanNameAware, InitializingBean,BeanPostProcessor, BeanFactoryAware, DisposableBean {

	private String beanName;

	private BeanFactory owningFactory;

	private boolean postProcessedBeforeInit;

	private boolean inited;

	private boolean postProcessedAfterInit;



	private boolean destroyed;

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	public String getBeanName() {
		return beanName;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.owningFactory = beanFactory;
	}

//	public void postProcessBeforeInit() {
//		if (this.inited) {
//			throw new RuntimeException("Factory called postProcessBeforeInit after afterPropertiesSet");
//		}
//		if (this.postProcessedBeforeInit) {
//			throw new RuntimeException("Factory called postProcessBeforeInit twice");
//		}
//		this.postProcessedBeforeInit = true;
//	}

	@Override
	public void afterPropertiesSet() {
//		if (this.owningFactory == null) {
//			throw new RuntimeException("Factory didn't call setBeanFactory before afterPropertiesSet on lifecycle bean");
//		}
//		if (!this.postProcessedBeforeInit) {
//			throw new RuntimeException("Factory didn't call postProcessBeforeInit before afterPropertiesSet on lifecycle bean");
//		}
//		if (this.inited) {
//			throw new RuntimeException("Factory called afterPropertiesSet twice");
//		}
		this.inited = true;
	}

//	public void postProcessAfterInit() {
//		if (!this.inited) {
//			throw new RuntimeException("Factory called postProcessAfterInit before afterPropertiesSet");
//		}
//		if (this.postProcessedAfterInit) {
//			throw new RuntimeException("Factory called postProcessAfterInit twice");
//		}
//		this.postProcessedAfterInit = true;
//	}

	/**
	 * Dummy business method that will fail unless the factory
	 * managed the bean's lifecycle correctly
	 */
	public String businessMethod() {
//		if (!this.inited || !this.postProcessedAfterInit) {
//			throw new RuntimeException("Factory didn't initialize lifecycle object correctly");
//		}
		return "hello";
	}

	@Override
	public void destroy() {
		if (this.destroyed) {
			throw new IllegalStateException("Already destroyed");
		}
		this.destroyed = true;
		log.info("invoke destroy.");
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
		log.info("");
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
		return null;
	}
}
