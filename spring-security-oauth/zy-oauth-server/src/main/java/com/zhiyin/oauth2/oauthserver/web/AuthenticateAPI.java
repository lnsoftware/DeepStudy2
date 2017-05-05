//package com.hg.oauth2.oauthserver.config.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.token.Token;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/admin/v1/")
//public class AuthenticateAPI {
//
//    @Autowired
//    private UserService userService;
//
//        /**
//         * 用户授权验证
//         * @param param
//         * @return
//         */
//        @RequestMapping(value="/tokens",method= RequestMethod.POST)
//        @ResponseBody
//        public String authenticate(@RequestBody String param){
//            try {
//                User user  = JSON.parseObject(param, User.class);
//                Token token = userService.login(user);
//                if(token == null){
//                    throw new CutomizedException(HttpStatus.UNAUTHORIZED, "User authenticate failed.");
//                }
//                TokenResponseEntity tokenResponseEntity = new TokenResponseEntity();
//                tokenResponseEntity.setEntity(token);
//                tokenResponseEntity.setHttpStatus(HttpStatus.OK);
//                tokenResponseEntity.setMessage("User authenticate successful.");
//                return JSON.toJSONStringWithDateFormat(tokenResponseEntity, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
//            }catch(JSONException e){
//                throw new CutomizedException(HttpStatus.BAD_REQUEST, "Bad request body.");
//            }catch (Exception e) {
//                throw new CutomizedException(HttpStatus.INTERNAL_SERVER_ERROR, "Server internal error");
//            }
//
//        }
//
//}