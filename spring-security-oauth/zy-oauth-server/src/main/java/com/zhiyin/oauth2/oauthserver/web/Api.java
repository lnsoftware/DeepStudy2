//package com.hg.oauth2.oauthserver.config.web;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.core.token.Token;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//import java.util.Date;
//
///**
// * Created by hg on 2016/6/22.
// */
//public class Api {
//
//    /**
//     * All Method need to validate token id
//     * @param headers
//     * @return
//     */
//    @ModelAttribute("tokenIdValidateResult")
//    public TokenValidateResult validateTokenId(@RequestHeader HttpHeaders headers){
//        String tokenId=headers.getFirst("X-Auth-Token");
//        Token searchToken = new Token();
//        searchToken.setTokenId(tokenId);
//        if(StringUtils.isEmpty(tokenId)){
//            return TokenValidateResult.TOKEN_EMPTY;
//        }
//
//        Token token=tokenService.findOneByExample(searchToken);
//        //Token not exists
//        if(token==null){
//            return TokenValidateResult.TOKEN_NOT_EXIST;
//        }
//
//        //Token 过期
//        if(token.getExpireTime().before(new Date())){
//            return TokenValidateResult.TOKEN_EXPIRED;
//        }
//
//        //Success
//        return TokenValidateResult.SUCCESS;
//    }
//
//
//}
