

https://my.oschina.net/flags/blog/385126
https://gitee.com/free/EntityMapper

如果线上数据库有分库和分表，查数据非常不方便。所以通过mybatis操作数据库，自己写SQL时，发现SQL没有对应的方法。
怎么通过Mybatis支持直接操作SQL？

```
<dependency>
    <groupId>com.github.abel533</groupId>
    <artifactId>entitymapper</artifactId>
    <version>1.0.0</version>
</dependency>
```


//创建sqlMapper
SqlMapper sqlMapper = new SqlMapper(sqlSession);
//查询，返回List<Map>
List<Map<String, Object>> list = sqlMapper.selectList("select * from country where id < 11");
