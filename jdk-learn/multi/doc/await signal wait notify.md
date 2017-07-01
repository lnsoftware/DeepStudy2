

wait()和notify()必须在synchronized的代码块中使用，因为只有在获取当前对象的锁时才能进行这两个操作，否则会报异常。
而await()和signal()一般与Lock()配合使用。


https://my.oschina.net/u/174366/blog/608509