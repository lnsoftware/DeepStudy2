
cd cglib_proxy/com

##find ./ -name "*.class" | xargs -0  echo 'ss'

##find ./ -name "*.class" -print0 | xargs -0 java -jar ~/Software/cfr.jar

for line in $(find . -iname '*.class'); do
     echo $line
     java -jar ~/Software/cfr.jar $line > ${line/class/java}
     ##ls -l $line;
done