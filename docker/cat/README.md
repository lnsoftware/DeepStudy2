

下载cat代码，运行build.sh

将 /data/appdatas/cat/ 下文件拷贝到./cat

替换datasources.xml的URL	
<url><![CDATA[jdbc:mysql://127.0.0.1:3306/cat]]></url>



https://segmentfault.com/a/1190000006837288

cat部署
http://blog.csdn.net/d6619309/article/details/53510585
http://blog.csdn.net/u011439289/article/details/46988993


删除旧的运行数据
docker rm -f cat_mysql_1
docker rm -f cat_tomcat_1

进入容器
docker exec -it cat_tomcat_1 bash
