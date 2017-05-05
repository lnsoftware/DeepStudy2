package com.zhiyin.oauth.thrid.oauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyin.oauth.thrid.util.TokenUtil;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Oauth osc
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 * @date Jun 24, 2013 9:58:25 PM
 */
public class OauthOsc extends Oauth {
	
	private static final Logger LOGGER = Logger.getLogger(OauthOsc.class);
	
	private static final String AUTH_URL = "http://www.oschina.net/action/oauth2/authorize"; // 授权
	private static final String TOKEN_URL = "http://www.oschina.net/action/openapi/token"; // tonken
	private static final String USER_INFO_URL = "http://www.oschina.net/action/openapi/user"; // 用户信息
	private static final String TWEET_PUB = "http://www.oschina.net/action/openapi/tweet_pub"; // 动弹

	private static OauthOsc oauthOsc = new OauthOsc();

	/**
	 * 用于链式操作
	 * @return
	 */
	public static OauthOsc me() {
		return oauthOsc;
	}
	
	/**
	 * @throws UnsupportedEncodingException 
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
		if (StrKit.isBlank(state)) {
			params.put("state", state); //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
		}
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
		String token = TokenUtil.getAccessToken(super.doGet(TOKEN_URL, params));
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
		params.put("access_token", accessToken);
		String userInfo = super.doGet(USER_INFO_URL, params);
		JSONObject dataMap = JSON.parseObject(userInfo);
		LOGGER.debug(dataMap.toJSONString());
		return dataMap;
	}
	
	/**
	 * 发送文字动弹
	 * @param accessToken
	 * @param msg
	 * @return
	 */
	public JSONObject tweetPub(String accessToken, String msg) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("msg", msg);
		return JSON.parseObject(super.doPost(TWEET_PUB, params));
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
}
