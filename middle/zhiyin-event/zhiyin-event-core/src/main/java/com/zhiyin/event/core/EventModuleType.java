package com.zhiyin.event.core;

/**
 * 事件所属模块
 *
 */
public enum EventModuleType {
	

	Undefine("未定义事件来源","0000"),
	// 内容相关的 0001
	Content("内容","0001"),

	Demo("测试", "9999"),

	MysqlBinlog("数据库binlog","0002")
	;
	


	
	private String name;
	private String code;

	private EventModuleType(String name, String code) {
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

	public static EventModuleType fromCode(String code) {
		for (EventModuleType b : EventModuleType.values()) {
			if (b.code.equals(code)) {
				return b;
			}
		}
		return null;
	}
}
