
docker run --privileged -i -t -v "$(pwd):/root" huiwq1990/ubuntu16

### 入门资料
http://blog.csdn.net/feixiaoxing/article/category/756837

### 01

 gcc hello.c -o hello


### 04 

as -gstabs -o hello.o hello.s

ld -o hello hello.o

objdump -S -d hello


### 05 

1) 首先生成*.o文件
gcc -c add.c -o add.o

2) 利用ar命令生成静态库
ar rc libadd.a add.o

3) 生成执行文件
gcc test.c -o test ./libadd.a

4) ./test



### 06

在编写静态库的时候，我说过静态库是汇编链接到执行文件当中的，而动态库不会。朋友们可以做个小实验，删除libtest.so，然后输入./hello。

gcc -shared -fPIC -o libadd.so add.c

gcc test.c -o test ./libadd.so