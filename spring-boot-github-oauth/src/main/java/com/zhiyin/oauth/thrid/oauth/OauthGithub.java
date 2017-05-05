package com.zhiyin.oauth.thrid.oauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.ericdahl.spring_boot_github_oauth.api.ApiException;
import com.github.ericdahl.spring_boot_github_oauth.oauth.AccessTokenRequest;
import com.zhiyin.oauth.thrid.util.TokenUtil;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * github 登录
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 * @date Jun 24, 2013 10:18:23 PM
 * 
 * DOC :https://developer.github.com/v3/oauth/
 */
public class OauthGithub extends Oauth {

	private static final Logger LOGGER = Logger.getLogger(OauthGithub.class);


    private final ParameterizedTypeReference<Map<String, String>> TYPE_REF_MAP_STRING_STRING = new ParameterizedTypeReference<Map<String, String>>() { };


    private RestTemplate restTemplate = new RestTemplate();

	private static final String AUTH_URL = "https://github.com/login/oauth/authorize";
	private static final String TOKEN_URL = "https://github.com/login/oauth/access_token";
	private static final String USER_INFO_URL = "https://api.github.com/user";
//    "https://api.github.com/user/emails"

	private static OauthGithub oauthGithub = new OauthGithub();

	/**
	 * 用于链式操作
	 * @return
	 */
	public static OauthGithub me() {
		return oauthGithub;
	}

	/**
	 * 获取授权url
	 * @param @return	设定文件
	 * @return String	返回类型
	 * @throws
	 */ 
	public String getAuthorizeUrl(String state) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("response_type", "code");
		params.put("client_id", getClientId());
		params.put("redirect_uri", getRedirectUri());

		final Random RANDOM = new SecureRandom();


			params.put("state", state); //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中

		return super.getAuthorizeUrl(AUTH_URL, params);
	}

	/**
	 * 获取token
	 * @param @param code
	 * @param @return	设定文件
	 * @return String	返回类型
	 * @throws
	 */
	public String getTokenByCode(String code) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("code", code);
		params.put("client_id", getClientId());
		params.put("client_secret", getClientSecret());
		params.put("grant_type", "authorization_code");
		params.put("redirect_uri", getRedirectUri());
		String token = TokenUtil.getAccessToken(super.doPost(TOKEN_URL, params));
		LOGGER.debug(token);
		return token;
	}

	/**
	 *  获取用户信息
	 * @param accessToken
	 * @return
	 */
	public JSONObject getUserInfo(String accessToken) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Authorization", "token " + accessToken);
		String userInfo = super.doGetWithHeaders(USER_INFO_URL, params);
		JSONObject dataMap = JSON.parseObject(userInfo);
		LOGGER.debug(dataMap.toJSONString());
		return dataMap;
	}
	
	/**
	 * 根据code一步获取用户信息
	 * @param @param args	设定文件
	 * @return void	返回类型
	 * @throws
	 */
	public JSONObject getUserInfoByCode(String code) {
		String accessToken = getTokenByCode(code);
		if (StrKit.isBlank(accessToken)) {
			return null;
		}
		JSONObject dataMap = getUserInfo(accessToken);
		dataMap.put("access_token", accessToken);
		return dataMap;
	}
	
	@Override
	public Oauth getSelf() {
		return this;
	}




    public String getAccessToken(final String code) throws URISyntaxException {

        final AccessTokenRequest accessTokenRequest = new AccessTokenRequest( getClientId(), getClientSecret(), code);
        final RequestEntity<AccessTokenRequest> requestEntity = new RequestEntity<>(accessTokenRequest, HttpMethod.POST, new URI("https://github.com/login/oauth/access_token"));
        final ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(requestEntity, TYPE_REF_MAP_STRING_STRING);

        final Map<String, String> responseBody = responseEntity.getBody();
        if (responseEntity.getStatusCode().is4xxClientError() ||
                responseEntity.getStatusCode().is5xxServerError() ||
                responseBody.containsKey("error")) {
            throw new ApiException(responseEntity);
        }


//        LOGGER.info("access_token response payload {}", responseBody);

        return responseBody.get("access_token");
    }




    private final ParameterizedTypeReference<List<Object>> TYPE_REF_LIST_OBJECT = new ParameterizedTypeReference<List<Object>>() { };




    public List<Object> getUserEmails(final String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + accessToken);
        URI URI_API_EMAILS = null;
        try {
            URI_API_EMAILS = new URI( USER_INFO_URL );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        RequestEntity<String> entity1 = new RequestEntity<>(headers, HttpMethod.GET, URI_API_EMAILS);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(entity1, TYPE_REF_LIST_OBJECT);

        if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()) {
            throw new ApiException(responseEntity);
        }

//        LOGGER.info("API response [{}]", responseEntity);

        return responseEntity.getBody();
    }

}
