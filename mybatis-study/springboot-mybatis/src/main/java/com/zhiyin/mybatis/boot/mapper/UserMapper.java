package com.zhiyin.mybatis.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.zhiyin.mybatis.boot.entity.User;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM User WHERE state = #{state}")
	User findByState(@Param("state") String state);
	
	//@Select("SELECT * FROM User WHERE state in ( #{state} )")
	//List<User> findByStates(@Param("state") String state);
	
	@Select({"<script>",
        "SELECT *", 
        "FROM User",
        "WHERE state IN", 
          "<foreach item='item' index='index' collection='states'",
            "open='(' separator=',' close=')'>",
            "#{item}",
          "</foreach>",
        "</script>"}) 
	List<User> findByStates(@Param("states") String[] states);

	@Insert("INSERT INTO User(name,state,country) VALUES(#{name},#{state},#{country})")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Integer.class)
	void insertUser(User user);
	
	@Select("SELECT * FROM User")
	List<User> getAllUsers();
	
	@Select("SELECT count(*) FROM User")
	int getUserCount();
}
