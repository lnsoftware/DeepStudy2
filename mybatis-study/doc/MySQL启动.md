
docker run  -e MYSQL_ROOT_PASSWORD=root  -e MYSQL_DATABASE=test  -p 3306:3306 -v /Users/hg/Github/DeepStudy2/mybatis-study/basic/src/test/resources/:/docker-entrypoint-initdb.d/ -i -t mysql:5.6



docker run  -e MYSQL_ROOT_PASSWORD=root  -e MYSQL_DATABASE=test  -p 3306:3306 -v `pwd`:/docker-entrypoint-initdb.d/ -d mysql:5.6
