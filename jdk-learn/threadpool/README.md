

防止任务重复提交
https://stackoverflow.com/questions/8905780/thread-pool-handling-duplicate-tasks


### 线程池获取真实任务
任务提交到线程池后，会变成FutureTask，提交的对象是它的一个属性
https://www.javaspecialists.eu/archive/Issue228.html