package com.zhiyin.oauth2.oauthserver.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.sql.DataSource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

    public UserInfo findByTelephone(String telephone);

} 