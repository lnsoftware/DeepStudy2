
# c
gcc -o grace_close grace_close.c
./grace_close &
pid=`ps -ef | grep grace_close | grep -v 'grep' | awk '{print $2}'`
kill ${pid}

# java
cd ..
mvn compile > /dev/null

cd target/classes

java ShutdownHookDemo &

pid=`ps -ef | grep ShutdownHookDemo | grep -v 'grep' | awk '{print $2}'`
# wait java server start
sleep 1
kill ${pid}



