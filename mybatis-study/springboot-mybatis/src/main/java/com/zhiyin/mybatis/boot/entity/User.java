package com.zhiyin.mybatis.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private String state;

	private String country;

	private Integer status;

}
