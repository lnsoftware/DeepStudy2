package com.zhiyin.oauth2.oauthserver.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by hg on 2016/7/6.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "zhiyin_user_user_privilege")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String username;

    private String authority;

    public UserRole(String authority){
        this.authority = authority;
    }

}
