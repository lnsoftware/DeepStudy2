

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
