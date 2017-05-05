package com.zhiyin.event.core.factory;


import com.zhiyin.event.core.EventProducerType;
import com.zhiyin.event.core.EventType;
import com.zhiyin.event.core.body.BasicEventBody;
import com.zhiyin.event.core.EventEntity;
import com.zhiyin.event.core.body.content.ContentAddBody;

public class EventBuilderFactory {

	public static EventEntity build(EventProducerType producer, EventType type,
									BasicEventBody body) {
		EventEntity e = new EventEntity();

		e.setType(type.getEventCode());
		e.setBody(body);
		e.setProducer(producer.getCode());

		return e;

	}

	/**
	 * 创建内容添加事件
	 * @return
     */
	public static EventEntity buildContentAddEvent() {
		ContentAddBody tmp = new ContentAddBody();
		tmp.setId(100L);
		tmp.setTitle("Hello");
		EventEntity event = EventBuilderFactory.build(
				EventProducerType.AppManager, EventType.ContentAdd, tmp);

//		System.out.println(event);
		return event;
	}

}
