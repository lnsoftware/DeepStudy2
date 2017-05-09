

公平锁和非公平锁，在CHL队列抢占模式上都是一致的，也就是在进入acquireQueued这个方法之后都一样，它们的区别在初次抢占上有区别，也就是tryAcquire上的区别，下面是两者内部调用关系的简图：

```
NonfairSync
lock —> compareAndSetState
                | —> setExclusiveOwnerThread
      —> accquire
		     | —> tryAcquire
                           |—>nonfairTryAcquire
                |—> acquireQueued

FairSync
lock —> acquire
               | —> tryAcquire
                           |—>!hasQueuePredecessors
                           |—>compareAndSetState
                           |—>setExclusiveOwnerThread
               |—> acquireQueued
```
真正的区别就是公平锁多了hasQueuePredecessors这个方法，这个方法用于判断CHL队列中是否有节点，对于公平锁，如果CHL队列有节点，则新进入竞争的线程一定要在CHL上排队，而非公平锁则是无视CHL队列中的节点，直接进行竞争抢占，这就有可能导致CHL队列上的节点永远获取不到锁，这就是非公平锁之所以不公平的原因。