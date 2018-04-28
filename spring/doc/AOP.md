
 (1) Pointcut：拦截点表达式， 
              (2)Joinpoint：符合上述Pointcut表达式的一个具体的拦截点。他们的关系就 
                            像正则表达式和符合该正则表达式的对象一样。 
              (3)advice:如拦截到对应的某个方法后，我们要做写什么？advice就是拦截后 
                        要执行的动作。
                        
                        
                        
                        
AspectjProxyFactory的特殊之处就在于其可以直接指定需要创建的代理对象需要绑定的切面。在使用ProxyFactory时，我们能够绑定的是Advisor或Advice，但是如果我们的程序中已经有了现成的切面类定义且能够为我们新创建的代理类使用时，我们还要为了ProxyFactory建立代理对象的需要创建对应的Advisor类、Advice类和Pointcut类定义，这无疑是非常麻烦的。AspectjProxyFactory通常用于创建基于Aspectj风格的Aop代理对象。
https://blog.csdn.net/elim168/article/details/78377926


AOP 分析
http://lgbolgger.iteye.com/category/322988
https://blog.csdn.net/luanlouis/article/details/51155821