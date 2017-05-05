package com.zhiyin.event.core;


/**
 * 事件类型
 * @author wangqinghui
 *
 */

public enum EventType {

	Undefine("未定义事件","0000",EventModuleType.Undefine),
	
	ContentAdd("内容生产","0001",EventModuleType.Content),

	DemoAdd("示例添加","0002",EventModuleType.Demo),

	MysqlBinlog("示例添加","0003",EventModuleType.MysqlBinlog)
	;
	
	private String name;
	private String code;
	private String source;
	private String eventCode;

	private EventType(String name,String code) {
		this.name = name;
		this.code= code;
		this.source = EventModuleType.Undefine.getCode();
	}
	
	private EventType(String name,String code,EventModuleType sourcetype) {
		this.name = name;
		this.code= code;
		this.source = sourcetype.getCode();
		eventCode = this.source + "_"+this.code;
	}
	

	/**
	 * @throws IllegalArgumentException if typeCode is not valid EventType code
	 */
	public static EventType fromEventCode(String eventCode) {
		
		
		if (eventCode != null && eventCode.trim().length()>=0) {
			String[] info = eventCode.split("_");
			if(info==null || info.length !=2){
				throw new IllegalArgumentException("No constant with eventCode " + eventCode + " found");
			}
			for (EventType b : EventType.values()) {
				if (info[0].equalsIgnoreCase(b.source) && info[1].equalsIgnoreCase(b.code)) {
					return b;
				}
			}
		}
		throw new IllegalArgumentException("No constant with eventCode " + eventCode + " found");
	}
	
	public static String getEventCode(EventType tmp){
		if(tmp==null){
			return null;
		}
		return tmp.source + "_"+tmp.code;
	}

	
	
	@Override
	public String toString() {
		return name;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public static void main(String[] args){
		System.out.println(EventType.getEventCode(EventType.DemoAdd));
		System.out.println(EventType.fromEventCode("020_0001"));
	}
}
