package com.zhiyin.event.core;

/**
 *事件生产者类型
 *
 */
public enum EventProducerType {
	
	AppManager("APP后台管理端", "001"),
	MysqlBinlog("MysqlBinlog", "002");
	
	private String name;
	private String code;

	private EventProducerType(String name, String code) {
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

	public static EventProducerType fromCode(String code) {
		for (EventProducerType b : EventProducerType.values()) {
			if (b.code.equals(code)) {
				return b;
			}
		}
		return null;
	}
}
