package com.zhiyin.oauth2.oauthserver.user;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "zhiyin_user_user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
	private String telephone;
	private String password;
//    private Boolean enabled;
//    private List<UserRole> roles = new ArrayList<UserRole>();

} 