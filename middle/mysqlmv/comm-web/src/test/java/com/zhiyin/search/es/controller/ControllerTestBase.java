package com.zhiyin.search.es.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyin.search.es.Application;
import com.zhiyin.search.es.web.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@Configuration


@Slf4j
//@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        locations = "classpath:applicationContext-controller-test.xml")
//@ContextConfiguration(Application.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class ControllerTestBase {


    @Autowired
    private WebApplicationContext wac;

    public MockMvc mockMvc;

//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;

//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;

//	@Autowired
//	private Filter springSecurityFilterChain;

    @Before
    public void setup() throws Exception {
        // System.out.println("base setup");
        // this.wac.addFilter(new DefaultWebappMetricsFilter(), "/*").build()
        // this.wac.getServletContext().addListener(com.bfa.sbgl.api.listener.ResourcesListener.class);
        this.wac.getServletContext().setInitParameter("test_parm", "value");
        wac.getServletContext().setAttribute("test_parm", "value");
        // putLan(wac.getServletContext());
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .alwaysDo(print())
//                .apply(SecurityMockMvcConfigurers.springSecurity(  ))
//				.addFilters(springSecurityFilterChain)
                .build();
        MockitoAnnotations.initMocks(this);

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

    /**
     * post数据之前需要进行验证
     *
     * @param url
     * @param tmp
     * @throws Exception
     */
    public String sendAuthPost(String url, Object tmp) throws Exception {

        String requestBody = JSON.toJSONString(tmp);

        ResultActions resultActions = mockMvc.perform(
                post(url)
                        .header("Authorization", "Bearer " + getAccessToken("18811591896", "123456"))
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                        .accept(MediaType.APPLICATION_JSON));

        resultActions.andDo(MockMvcResultHandlers.print());
        log.info("req:{}" + JSON.toJSONString(requestBody));

        String ret = resultActions.andReturn().getResponse().getContentAsString();
        log.info("ret:{}", ret);

//        JSON.parseObject(ret, WebResponse.class).
        Object data = JSON.parseObject(ret, WebResponse.class).getData();

        log.info("s2c:{}", JSON.toJSONString(data));

        return JSON.toJSONString(data);
    }

    public void sendPost(String url, Object tmp) throws Exception {

        String requestBody = JSON.toJSONString(tmp);

        ResultActions resultActions = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                        .accept(MediaType.APPLICATION_JSON));

        resultActions.andDo(MockMvcResultHandlers.print());
        System.out.println(requestBody);
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }


    //    @Test
    public void greetingAuthorized() throws Exception {
        String accessToken = getAccessToken("18811591896", "123456");
    }

    private String getAccessToken(String username, String password) throws Exception {
        String authorization = "Basic "
                + new String(Base64Utils.encode("mobile-client:mobile".getBytes()));
        String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";
        // @formatter:off
        String content = mockMvc
                .perform(
                        post("/oauth/token")
                                .header("Authorization", authorization)
                                .contentType(
                                        MediaType.APPLICATION_FORM_URLENCODED)
                                .param("username", username)
                                .param("password", password)
                                .param("grant_type", "password")
                                .param("scope", "read write")
                                .param("client_id", "mobile-client")
                                .param("client_secret", "mobile"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.access_token", is(notNullValue())))
                .andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
                .andExpect(jsonPath("$.refresh_token", is(notNullValue())))
                .andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
                .andExpect(jsonPath("$.scope", is(equalTo("read write"))))
                .andReturn().getResponse().getContentAsString();

        // @formatter:on

        return content.substring(17, 53);
    }


}



/*
class WebContextLoader extends GenericWebContextLoader {

    public WebContextLoader() {
        super("src/test/resources/META-INF/web-resources", false);
    }

}

 class GenericWebContextLoader extends AbstractContextLoader {
    protected final MockServletContext servletContext;

    public GenericWebContextLoader(String warRootDir, boolean isClasspathRelative) {
        ResourceLoader resourceLoader = isClasspathRelative ? new DefaultResourceLoader() : new FileSystemResourceLoader();
        this.servletContext = initServletContext(warRootDir, resourceLoader);
    }

    private MockServletContext initServletContext(String warRootDir, ResourceLoader resourceLoader) {
        return new MockServletContext(warRootDir, resourceLoader) {
            // Required for DefaultServletHttpRequestHandler...
            public RequestDispatcher getNamedDispatcher(String path) {
                return (path.equals("default")) ? new MockRequestDispatcher(path) : super.getNamedDispatcher(path);
            }
        };
    }

    public ApplicationContext loadContext(MergedContextConfiguration mergedConfig) throws Exception {
        GenericWebApplicationContext context = new GenericWebApplicationContext();
        context.getEnvironment().setActiveProfiles(mergedConfig.getActiveProfiles());
        prepareContext(context);
        loadBeanDefinitions(context, mergedConfig);
        return context;
    }

    public ApplicationContext loadContext(String... locations) throws Exception {
        // should never be called
        throw new UnsupportedOperationException();
    }

    protected void prepareContext(GenericWebApplicationContext context) {
        this.servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
        context.setServletContext(this.servletContext);
    }

    protected void loadBeanDefinitions(GenericWebApplicationContext context, String[] locations) {
        new XmlBeanDefinitionReader(context).loadBeanDefinitions(locations);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
        context.refresh();
        context.registerShutdownHook();
    }

    protected void loadBeanDefinitions(GenericWebApplicationContext context, MergedContextConfiguration mergedConfig) {
        new AnnotatedBeanDefinitionReader(context).register(mergedConfig.getClasses());
        loadBeanDefinitions(context, mergedConfig.getLocations());
    }

    @Override
    protected String getResourceSuffix() {
        return "-context.xml";
    }
}*/