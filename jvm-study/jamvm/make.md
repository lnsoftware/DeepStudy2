



### 代码下载
https://github.com/cfriedt/jamvm

### 编译安装

gun classpath下载
https://link.jianshu.com/?t=ftp://ftp.gnu.org/gnu/classpath/classpath-0.99.tar.gz
git clone git://git.savannah.gnu.org/classpath.git

```

sudo apt-get install texinfo autotools-dev automake antlr libgconf2-dev libgtk2.0-dev ecj fastjar pccts

./autogen.sh
./configure --disable-Werror --disable-plugin
make
# and install it to /usr/local:
sudo make install
```

```
export CFLAGS='-g -O0'
```

# 将通过${with-classpath-install-dir}最终会有一个rt.jar的快捷方式
# 并指向 /tmp/classpath/share/classpath/glibj.zip
./configure  --enable-tracethread



### 参考

GNU Classpath安装
https://github.com/jatovm/jato

jamvm安装
https://www.jianshu.com/p/ca46826073a0

