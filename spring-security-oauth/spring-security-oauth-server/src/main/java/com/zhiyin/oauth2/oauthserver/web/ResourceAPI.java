//package com.zhiyin.oauth2.oauthserver.web;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(value="/resources/v1")
//public class ResourceAPI {
//
//    @RequestMapping(method= RequestMethod.POST)
//    @ResponseBody
//    public ResourceResponseEntity createResource(@ModelAttribute("tokenIdValidateResult")TokenValidateResult tokenIdValidateResult,@RequestHeader HttpHeaders headers,@JsonParam ResourceReponseEntity resourceResponseEntity ) {
//        if(tokenIdValidateResult == TokenValidateResult.SUCCESS){
//            try {
//                String tokenId=headers.getFirst("X-Auth-Token");
//                Token searchToken = new Token();
//                searchToken.setTokenId(tokenId);
//                //TODO:创建Resource
//                return resourceResponseEntity;
//            } catch (Exception e) {
//                throw new CutomizedException(e);
//            }
//        }else{
//            //异常处理交给异常处理框架
//            throw new CutomizedException(HttpStatus.UNAUTHORIZED,"Token validte failed,ErrorCode:"+tokenIdValidateResult);
//        }
//      }
//}