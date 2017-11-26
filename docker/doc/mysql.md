

docker compose 初始化执行SQL，配置volumes，设置MYSQL_DATABASE

```
  mysql:
    image: mysql:5.6
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=cat
    volumes:
      - ./cat/Cat.sql:/docker-entrypoint-initdb.d
    ports:
      - "3306:3306"
```


docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:5.6







docker run --name first-mysql -p 3306:3306  -e MYSQL_DATABASE=test -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6






# 

docker run  -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v ./db.sql:/docker-entrypoint-initdb.d
docker run  -e MYSQL_ROOT_PASSWORD=root  -e MYSQL_DATABASE=test  -p 3306:3306 -v /Users/hg/Github/DeepStudy2/mybatis-study/basic/src/test/resources/:/docker-entrypoint-initdb.d/ -i -t mysql:5.6




 bd7823bdd7728022b258aa3436f23532d49fce2e