
jarName=demo.jar

mvn install > /dev/null

cd target/classes

echo 'make empty file.'
dd if=/dev/zero of=empty.log bs=1m count=1 >> /dev/null

echo 'make jar file.'
#打包当前目录，不压缩
#jar cf0 ${jarName} ./*

jar uf ${jarName} ./*

#显示内容
#jar tvf test.jar

#java -server -Xmx10m -Xms10m -classpath ${jarName} com.zhiyin.classloader.hotdeploy.TestHotSwap


java -server -Xmx10m -Xms10m -classpath ${jarName} com.zhiyin.classloader.hotdeploy.CustomerJarUrlLoader


