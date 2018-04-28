

javac JniAdder.java

javah -jni JniAdder

gcc -shared -I /home/vagrant/Desktop/jamvm/src -I /home/vagrant/Desktop/jamvm/src JniAdder.c -o libbridge.so

java -Djava.library.path=/home/vagrant/Desktop/DeepStudy2/jvm-study/jamvm/java -cp /home/vagrant/Desktop/DeepStudy2/jvm-study/jamvm/java JniAdder

### jamvm debug config
-Djava.library.path=/home/vagrant/Desktop/DeepStudy2/jvm-study/jamvm/java -cp /home/vagrant/Desktop/DeepStudy2/jvm-study/jamvm/java JniAdder

jni reference
https://blog.csdn.net/hackooo/article/details/48395765/