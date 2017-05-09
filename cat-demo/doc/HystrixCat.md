

Hystrix Cat打点时，使用HystrixConcurrencyStrategy方式，logRemoteClient会出现两个，因为hystrix会新建两个线程，一个执行run,一个执行timeout  

com.hg.cat.demo.CatHystrixConcurrencyStrategy2.wrapCallable