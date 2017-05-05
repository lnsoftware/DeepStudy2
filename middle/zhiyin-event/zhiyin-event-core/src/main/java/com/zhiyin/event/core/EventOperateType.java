package com.zhiyin.event.core;


/**
 * 事件操作类型
 * @author hg
 *
 */
public enum EventOperateType {
	
	Undefine("未定义","0000"),
	AddType("增加", "0001"),
	DelType("删除", "0002"),
	UpdateType("更新", "0003"),
	SearchType("查找", "0004"),
	

	EventOperateType("APP后台管理端", "001");
	
	private String name;
	private String code;

	private EventOperateType(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
