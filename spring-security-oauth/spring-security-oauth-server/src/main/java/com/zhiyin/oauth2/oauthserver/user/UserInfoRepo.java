package com.zhiyin.oauth2.oauthserver.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoRepo {

    private JdbcTemplate jdbcTemplate;


	private static String tableUser = "users";
    private static String tableAuthority = "user_authorities";


//    @Resource(name="userInfoDataSource")
//    private DataSource userInfoDataSource;

    @Autowired
    public void setUserInfoDataSource( @Qualifier("userInfoDataSource") DataSource userInfoDataSource) {
        this.jdbcTemplate = new JdbcTemplate(userInfoDataSource);
    }

    public UserInfo getUserInfo(String username){
		String sql = "SELECT u.username name, u.password pass FROM "+
    			     tableUser +" u INNER JOIN "+tableAuthority+" a on u.username=a.username WHERE "+
    			     "u.enabled =1 and u.username = ?";
    	UserInfo userInfo = (UserInfo)jdbcTemplate.queryForObject(sql, new Object[]{username},
    		new RowMapper<UserInfo>() {
	            public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	                UserInfo user = new UserInfo();
	                user.setUsername(rs.getString("name"));
	                user.setPassword(rs.getString("pass"));
	                return user;
	            }
        });

//		jdbcTemplate.queryForList(" SELECT a.authority from user_authorities a where a.username=? ");
    	return userInfo;
    }
} 