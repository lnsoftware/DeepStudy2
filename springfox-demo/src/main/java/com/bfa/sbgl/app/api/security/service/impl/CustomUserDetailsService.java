package com.bfa.sbgl.app.api.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.bfa.sbgl.app.api.security.entity.AuthUser;
import com.bfa.sbgl.app.api.security.service.IAuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IAuthUserService authUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser user = authUserService.selectByName(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
        AuthUserDetails d = new AuthUserDetails();
        d.setU(user);

        log.debug("login user info:{}",JSON.toJSONString(d));
		return d;
	}

	private final static class AuthUserDetails implements UserDetails {

		private static final long serialVersionUID = 1L;


        private AuthUser u;

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return u.getRoles();
		}

        @Override
        public String getPassword() {
            return u.getPassword();
        }

        @Override
		public String getUsername() {
			return u.getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public AuthUser getU() {
            return u;
        }

        public void setU(AuthUser u) {
            this.u = u;
        }
    }

}