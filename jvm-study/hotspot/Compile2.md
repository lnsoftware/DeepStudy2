
### 下周代码

git clone -b jdk8u/jdk8u https://github.com/dmlloyd/openjdk.git

https://github.com/dmlloyd/openjdk/tree/jdk8u/jdk8u

### 编译参数

```
bash configure --with-debug-level=slowdebug \
--with-freetype=/usr/local/opt/freetype \
--disable-warnings-as-errors \
--with-jvm-features=zero \
--with-jvm-variants=zero \
--with-libffi=/usr/local/opt/libffi \
--with-libffi-include=/usr/local/Cellar/libffi/3.0.13/lib/libffi-3.0.13/include
```

--with-debug-level
设置调试级别，设成 slow debug 可以提供更多的调试信息

--with-free-type
设置 free type 路径

--disable-warnings-as-errors
忽略警告

--with-jvm-features
设置使用 c++ 实现的 zero 解释器，比基于模版的解释器更容易调试（如果汇编功力不够的话）

--with-jvm-variants
配合 --with-jvm-features 使用

--with-libffi
配合 --with-jvm-features 使用

--with-libffi-include
设置 libffi include 文件夹路径

### IDE 调试

1、以源代码方式新建一个工程，将 hotspot 相关的源代码导入 IDE

2、编辑 "运行&调试" configuration，将 executable（要运行的目标程序）修改成 在 make 阶段生成的 jdk/bin/java

3、去掉 Before launch 里面的 build 动作，在 IDE 里面我们无法进行构建

4、打开 jni.cpp 文件，找到 JNI_CreateJavaVM，设置断点，点击工具栏上那个"小臭虫"开始调试


### 参考

https://segmentfault.com/a/1190000008346240