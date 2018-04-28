package com.zhiyin.spring.bfpr;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class HelloBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
            ConfigurableListableBeanFactory beanFactory) throws BeansException {
		//获取所有的beanName
		String beanNames[] = beanFactory.getBeanDefinitionNames();
		if (beanNames != null && beanNames.length > 0) {
			for (String beanName : beanNames) {
				//获取对应的bean定义
				BeanDefinition beanDef = beanFactory.getBeanDefinition(beanName);
				if(beanDef instanceof RootBeanDefinition){
					this.printBeanDef(beanName, (RootBeanDefinition)beanDef);
				}
			}
		}
	}
	
	/**
	 * 打印bean定义的基本信息
	 * @param beanName
	 * @param beanDef
	 */
	private void printBeanDef(String beanName, RootBeanDefinition beanDef) {
		StringBuilder defStr = new StringBuilder("beanName: ").append(beanName);
		defStr.append(", className: ").append(beanDef.getBeanClassName());
		defStr.append(", singleton: ").append(beanDef.isSingleton());
//		defStr.append(", parent: ").append(beanDef.getParentName());
//		defStr.append(", factoryBean: ").append(beanDef.getFactoryBeanName());
//		defStr.append(", factoryMethod: ").append(beanDef.getFactoryMethodName());
		System.out.println(defStr);
	}

}
