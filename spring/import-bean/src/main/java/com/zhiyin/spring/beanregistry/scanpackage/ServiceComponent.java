package com.zhiyin.spring.beanregistry.scanpackage;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ServiceComponent {

	String value() default "";
}
