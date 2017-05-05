package com.zhiyin.oauth2.oauthserver.user;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserInfo {

    private Long id;
	private String username;
	private String password;
    private Boolean enabled;
    private List<UserRole> roles = new ArrayList<UserRole>();

} 