package com.github.ericdahl.spring_boot_github_oauth.api;

import com.github.ericdahl.spring_boot_github_oauth.oauth.GithubAccessTokenService;
import com.github.ericdahl.spring_boot_github_oauth.oauth.InvalidOAuthStateException;
import com.google.common.collect.Maps;
import com.zhiyin.oauth.thrid.oauth.OauthGithub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/oauth/")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    private static final Random RANDOM = new SecureRandom();

    private final GithubAccessTokenService accessTokenService;
    private final UserService userService;

    private final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/authorize");


    @Autowired
    public ApiController(final GithubAccessTokenService accessTokenService,
                         final UserService userService,
                         @Value("${api.client_id}") String clientId,
                         @Value("${api.scope}") String scope,
                         @Value("${api.redirect_uri}") String redirectUri) {
        this.accessTokenService = accessTokenService;
        this.userService = userService;

        builder.queryParam("client_id", clientId);
        builder.queryParam("scope", scope);
        builder.queryParam("redirect_uri", redirectUri);
    }


    public static Map<String,String> oauthStateMap = Maps.newHashMap();

    // 获取授权界面路径
    @RequestMapping(value = "authorize/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String index( @RequestParam(value = "clientid", required = true) String clientid,
                         @PathVariable(value = "type") String type
                         ) {

        String authorizeUrl = "";
        if(type.equals(OauthType.github)){
            // state必须是整数
            final long state = RANDOM.nextLong();
            String str =  String.valueOf(state);
            oauthStateMap.put(clientid, str);
            authorizeUrl = OauthGithub.me().getAuthorizeUrl( str );
            LOGGER.info( clientid + " " + str);
        }else{

        }
        return  authorizeUrl;
    }


    @RequestMapping(value = "callback/{type}", method=RequestMethod.GET)
    @ResponseBody
    public Object callback(@RequestParam("code") final String code,
                                      @RequestParam("state") final long state,
                                 @PathVariable(value = "type") String type
                                    ) throws URISyntaxException {

//        oauthStateMap.get(state);
        Object o = null;
        if( type.equals(OauthType.github) ){
           String token =  OauthGithub.me().getAccessToken( code );
//            o = OauthGithub.me().getUserEmails( token );
            o = OauthGithub.me().getUserInfo(token);
//            return userService.getUserEmails(accessTokenService.getAccessToken(code));
        }

        return o;
    }







    // 用于web页面授权
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(final HttpSession httpSession, final Model model) {
        final long state = RANDOM.nextLong();

        httpSession.setAttribute("state", state);
        builder.replaceQueryParam("state", state);
        model.addAttribute("authorizeURL", builder.toUriString());

        return "index";
    }

    @RequestMapping("/authorized")
    @ResponseBody
    public List<Object> getUserEmails(@RequestParam("code") final String code,
                                      @RequestParam("state") final long state,
                                      final HttpSession session) throws URISyntaxException {

        long sessionState = (long) session.getAttribute("state");
        if (state != sessionState) {
            throw new InvalidOAuthStateException(state, sessionState);
        }

        return userService.getUserEmails(accessTokenService.getAccessToken(code));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    private void handleInvalidState(final InvalidOAuthStateException e) {
        LOGGER.error("Received invalid state", e);
        throw e;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    private void handleApiException(final ApiException e) {
        LOGGER.error("Received API exception", e);
        throw e;
    }
}
