package com.dxfx.netty;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.dxfx.netty.client.TcpClient;
import com.dxfx.netty.param.Request;
import com.dxfx.user.model.User;

public class TcpClientTest {

	@Test
	public void testClient(){
		for(int i=0;i<100;i++){
			Request request = new Request();
			request.setCommand("saveUser");
			User user = new User();
			user.setAge("11");
			user.setId(i);
			user.setName("张三"+i);
			request.setContent(user);
			Object result = TcpClient.send(request);
			
			System.out.println("客户端长连接测试返回结果"+JSONObject.toJSONString(result));
		}
	
	}
}
