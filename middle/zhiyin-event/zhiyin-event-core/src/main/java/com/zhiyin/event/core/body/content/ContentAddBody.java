package com.zhiyin.event.core.body.content;

import com.zhiyin.event.core.body.BasicEventBody;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContentAddBody extends BasicEventBody{
	
	private long id;
	private String title;

	
}
