#!/usr/bin/bash
JAR_PATH=/Users/hg/.m2/repository/net/sourceforge/plantuml/plantuml/8059/plantuml-8059.jar
UML=$1
FILE=`head -n1 $UML | awk '{print $2}'`
if [ -n "$FILE" ]; then
    EXT=${FILE##*.}
    java -jar $JAR_PATH -t$EXT $@
    echo $FILE
else
    java -jar $JAR_PATH $@
    echo ${UML%.*}.png
fi
