package com.zhiyin.event.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zhiyin.event.core.factory.EventBuilderFactory;
import com.zhiyin.event.core.EventEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class ControllerTest extends ControllerTestBase{

	private Log log = LogFactory.getLog(ControllerTest.class);

	@Test
	public void test() throws Exception {
		
		String url = "/events/test";
		log.info("start test " + url);
		MvcResult result = this.mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andReturn();
		assertNotNull(result);
		
	}
	
	@Test
	public void testPush() throws Exception {
		
		String url = "/events/push";
		log.info("start test " + url);

		EventEntity event = EventBuilderFactory.buildContentAddEvent();
		
		this.sendPost(url, event);
		
	}
}
