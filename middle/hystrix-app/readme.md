
### 配置组

HystrixCommandGroupKey.Factory.asKey("ExampleGroup")
会有缓存，key为name

CommandKey
HystrixCommandKey 默认使用Command类作为key


### HystrixCommandProperties Command属性
HystrixPropertiesFactory提供了以commandKey为键的缓存
```
    private static HystrixCommandProperties initCommandProperties(HystrixCommandKey commandKey, HystrixPropertiesStrategy propertiesStrategy, HystrixCommandProperties.Setter commandPropertiesDefaults) {
        if (propertiesStrategy == null) {
            return HystrixPropertiesFactory.getCommandProperties(commandKey, commandPropertiesDefaults);
        } else {
            // used for unit testing
            return propertiesStrategy.getCommandProperties(commandKey, commandPropertiesDefaults);
        }
    }
```


HystrixThreadPoolKey 决定command在哪个线程池运行





HystrixCircuitBreaker 

如果command已经存在，则直接返回

```
            HystrixCircuitBreaker previouslyCached = circuitBreakersByCommand.get(key.name());
            if (previouslyCached != null) {
                return previouslyCached;
            }
			 HystrixCircuitBreaker cbForCommand = circuitBreakersByCommand.putIfAbsent(key.name(), new HystrixCircuitBreakerImpl(key, group, properties, metrics));
            if (cbForCommand == null) {
                // this means the putIfAbsent step just created a new one so let's retrieve and return it
                return circuitBreakersByCommand.get(key.name());
            } else {
                // this means a race occurred and while attempting to 'put' another one got there before
                // and we instead retrieved it and will now return it
                return cbForCommand;
            }

```

## 通过archaius配置hystrix属性


``` ConfigurationManager.getConfigInstance().setProperty("hystrix.threadpool.default.coreSize", 8);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.CreditCardCommand.execution.isolation.thread.timeoutInMilliseconds", 3000);
```

```
public static HystrixCommandProperties getCommandProperties(HystrixCommandKey key, HystrixCommandProperties.Setter builder) {
        HystrixPropertiesStrategy hystrixPropertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
        String cacheKey = hystrixPropertiesStrategy.getCommandPropertiesCacheKey(key, builder);
```
		
		
		    public static HystrixPlugins getInstance() {
        return LazyHolder.INSTANCE;
    }
	
	
	```
	private static HystrixDynamicProperties resolveDynamicProperties(ClassLoader classLoader, LoggerSupplier logSupplier) {
        HystrixDynamicProperties hp = getPluginImplementationViaProperties(HystrixDynamicProperties.class, 
                HystrixDynamicPropertiesSystemProperties.getInstance());
        if (hp != null) {
            logSupplier.getLogger().debug(
                    "Created HystrixDynamicProperties instance from System property named "
                    + "\"hystrix.plugin.HystrixDynamicProperties.implementation\". Using class: {}", 
                    hp.getClass().getCanonicalName());
            return hp;
        }
        hp = findService(HystrixDynamicProperties.class, classLoader);
        if (hp != null) {
            logSupplier.getLogger()
                    .debug("Created HystrixDynamicProperties instance by loading from ServiceLoader. Using class: {}", 
                            hp.getClass().getCanonicalName());
            return hp;
        }
        hp = HystrixArchaiusHelper.createArchaiusDynamicProperties();
        if (hp != null) {
            logSupplier.getLogger().debug("Created HystrixDynamicProperties. Using class : {}", 
                    hp.getClass().getCanonicalName());
            return hp;
        }
        hp = HystrixDynamicPropertiesSystemProperties.getInstance();
        logSupplier.getLogger().info("Using System Properties for HystrixDynamicProperties! Using class: {}", 
                hp.getClass().getCanonicalName());
        return hp;
    }
	```
	
	        hp = HystrixArchaiusHelper.createArchaiusDynamicProperties();
