

JAVA_HOME=/usr/libexec/java_home

sudo java -classpath "$JAVA_HOME/lib/sa-jdi.jar" sun.jvm.hotspot.HSDB





JAVA_HOME=$(/usr/libexec/java_home)
sudo java -classpath "$JAVA_HOME/lib/sa-jdi.jar" sun.jvm.hotspot.HSDB


JAVA_HOME=$(/usr/libexec/java_home)
sudo java -cp "/Users/hg/Software/dumpclass.jar:$JAVA_HOME/lib/sa-jdi.jar" io.github.hengyunabc.dumpclass.DumpMain 3397 '*Proxy6*' /tmp/dump2/

