package com.bfa.sbgl.app.api.security.entity;

import org.springframework.security.core.GrantedAuthority;

public class AuthRole implements GrantedAuthority {

	private static final long serialVersionUID = 1L;


	private Integer id;


	private String name;


	@Override
	public String getAuthority() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}