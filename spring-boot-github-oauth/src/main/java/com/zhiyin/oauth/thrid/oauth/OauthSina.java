package com.zhiyin.oauth.thrid.oauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyin.oauth.thrid.util.TokenUtil;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * sina 登录 BAE
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 * @date Jun 24, 2013 10:18:23 PM
 */
public class OauthSina extends Oauth {
	
	private static final Logger LOGGER = Logger.getLogger(OauthSina.class);

	private static final String AUTH_URL = "https://api.weibo.com/oauth2/authorize";
	private static final String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
	private static final String TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
	private static final String USER_INFO_URL = "https://api.weibo.com/2/users/show.json";

	private static OauthSina oauthSina = new OauthSina();

	/**
	 * 用于链式操作
	 * @return
	 */
	public static OauthSina me() {
		return oauthSina;
	}

	/**
	 * 获取授权url
	 * DOC：http://open.weibo.com/wiki/Oauth2/authorize
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
		String token = TokenUtil.getAccessToken(super.doPost(TOKEN_URL, params));
		LOGGER.debug(token);
		return token;
	}
	
	/**
	 * 获取TokenInfo
	 * @return	设定文件
	 * @return String	返回类型
	 * @throws
	 */
	public String getTokenInfo(String accessToken) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		String openid = TokenUtil.getUid(super.doPost(TOKEN_INFO_URL, params));
		LOGGER.debug(openid);
		return openid;
	}
	
	/**
	 * 获取用户信息
	 * DOC：http://open.weibo.com/wiki/2/users/show
	 * @param accessToken
	 * @param uid
	 * @return	设定文件
	 * @return String	返回类型
	 * @throws
	 */
	public String getUserInfo(String accessToken, String uid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", uid);
		params.put("access_token", accessToken);
		String userInfo = super.doGet(USER_INFO_URL, params);
		LOGGER.debug(userInfo);
		return userInfo;
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
		String uid = getTokenInfo(accessToken);
		if (StrKit.isBlank(uid)) {
			return null;
		}
		JSONObject dataMap = JSON.parseObject(getUserInfo(accessToken, uid));
		dataMap.put("access_token", accessToken);
		return dataMap;
	}
	
	@Override
	public Oauth getSelf() {
		return this;
	}
}
