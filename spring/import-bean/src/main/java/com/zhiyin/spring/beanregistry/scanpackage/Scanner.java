package com.zhiyin.spring.beanregistry.scanpackage;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class Scanner extends ClassPathBeanDefinitionScanner {

	public Scanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	public void registerDefaultFilters() {
		this.addIncludeFilter(new AnnotationTypeFilter(ServiceComponent.class));
	}

	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		for (BeanDefinitionHolder holder : beanDefinitions) {
			GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
			definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
			// 用工厂类生成
			definition.setBeanClass(ServiceFactoryBean.class);
		}
		return beanDefinitions;
	}

	public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
				.hasAnnotation(ServiceComponent.class.getName());
	}

}