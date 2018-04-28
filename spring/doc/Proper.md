

## Bean属性配置支持原理
1. 前提
在配置文件中需要引入 bean PreferencesPlaceholderConfigurer。

2. PreferencesPlaceholderConfigurer分析

它是一个 BeanFactoryPostProcessor，在PropertyResourceConfigurer实现方法postProcessBeanFactory。
在创建bean创建时，先设置bean的配置文件路径

