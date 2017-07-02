
断点技巧


1. 获取Bean地方断点：context.getBean(Car.class);

2. 解析BeanName断点：org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(java.lang.Class<?>, boolean, boolean)

将获取Bean的类型转换为FactoryBean类型

3. 类初始化地方

http://blog.csdn.net/turkeyzhou/article/details/2974922

http://www.baeldung.com/spring-factorybean