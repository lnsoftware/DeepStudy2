package com.zhiyin.event.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zhiyin.utils.json.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-controller-test.xml")
//@ActiveProfiles("dev")
@WebAppConfiguration
public class ControllerTestBase {

	private Log log = LogFactory.getLog(ControllerTestBase.class);

	@Autowired
	private WebApplicationContext wac;

	public MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		// System.out.println("base setup");
		// this.wac.addFilter(new DefaultWebappMetricsFilter(), "/*").build()
		// this.wac.getServletContext().addListener(com.bfa.sbgl.api.listener.ResourcesListener.class);
//		this.wac.getServletContext().setInitParameter("test_parm", "value");
		wac.getServletContext().setAttribute("test_parm", "value");
		// putLan(wac.getServletContext());
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/*
	 * public static void putLan(ServletContext context) throws Exception {
	 * CompositeConfiguration config = new CompositeConfiguration(); Map<String,
	 * String> textMap = new ConcurrentHashMap<String, String>();
	 * 
	 * String base = "";
	 * 
	 * config.addConfiguration(new PropertiesConfiguration(base +
	 * "language/messages_zh_CN_common.properties")); //
	 * messages_zh_CN_common.properties config.addConfiguration(new
	 * PropertiesConfiguration(base+ "language/messages_zh_CN_hg.properties"));
	 * config.addConfiguration(new PropertiesConfiguration(base+
	 * "language/messages_zh_CN_wm.properties")); config.addConfiguration(new
	 * PropertiesConfiguration(base+ "language/messages_zh_CN_be.properties"));
	 * Iterator<String> keys = config.getKeys();
	 * 
	 * while (keys.hasNext()) { String key = keys.next(); textMap.put(key,
	 * config.getString(key)); }
	 * 
	 * context.setAttribute(CommonConfig.resourcetextmapch, textMap);
	 * 
	 * 
	 * config = new CompositeConfiguration(); Map<String,String> textMapEn = new
	 * ConcurrentHashMap<String,String>();
	 * 
	 * config.addConfiguration(new
	 * PropertiesConfiguration(base+"language/messages_en_US_common.properties"
	 * )); config.addConfiguration(new
	 * PropertiesConfiguration(base+"language/messages_en_US_hg.properties"));
	 * config.addConfiguration(new
	 * PropertiesConfiguration(base+"language/messages_en_US_wm.properties"));
	 * config.addConfiguration(new
	 * PropertiesConfiguration(base+"language/messages_en_US_be.properties"));
	 * Iterator<String> keysEn = config.getKeys(); //
	 * System.out.println(config.getString("usercenter_accountsetup"));
	 * while(keysEn.hasNext()){ String key = keysEn.next(); textMapEn.put(key,
	 * config.getString(key)); }
	 * context.setAttribute(CommonConfig.resourcetextmapen, textMapEn); }
	 */

	public void sendPost(String url, Object tmp) throws Exception {

		String requestBody = JSONUtil.toJson(tmp);

		ResultActions resultActions = mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestBody)
				.accept(MediaType.APPLICATION_JSON));

		resultActions.andDo(MockMvcResultHandlers.print());
		System.out.println(requestBody);
		log.info(resultActions.andReturn().getResponse().getContentAsString());
	}
}
