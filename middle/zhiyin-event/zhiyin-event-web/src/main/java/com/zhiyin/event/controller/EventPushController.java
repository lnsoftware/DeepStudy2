package com.zhiyin.event.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhiyin.event.core.EventEntity;
import com.zhiyin.event.web.WebResponse;

@Controller
@RequestMapping("/events")
public class EventPushController {

	
	private static final Logger logger = LoggerFactory
			.getLogger(EventPushController.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;
	 
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody WebResponse<Map<String, Object>> test() {
		
		logger.info("events test");
		return new WebResponse<Map<String, Object>>(HttpStatus.OK.value(), "",
				null);
	}

	@ApiOperation(value = "内容查询", notes = "内容查询", response = EventEntity.class )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "查询成功", response = EventEntity.class),
			@ApiResponse(code = 500, message = "内部报错") })

	@RequestMapping(value = "/push", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	WebResponse<Map<String, Object>> push(@RequestBody final EventEntity event) {
		logger.info("receive event info:"+JSON.toJSONString(event));
//		EventEntity event = EventBuilderFactory.buildContentAddEvent();
		try{
		 jmsTemplate.send( new MessageCreator() {
		      public Message createMessage(Session session) throws JMSException {
		        return session.createTextMessage(JSON.toJSONString(event));
		      }
		   });
		}catch(Exception e){
			logger.error("fail to push event,event info:{}",JSON.toJSONString(event));
		}
		
		return null;
	}

}
