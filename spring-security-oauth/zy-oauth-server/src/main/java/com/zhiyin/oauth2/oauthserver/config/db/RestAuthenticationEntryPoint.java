//package com.zhiyin.oauth2.oauthserver.config.db;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
///**
// * The Entry Point will not redirect to any sort of Login - it will return the 401
// */
//@Slf4j
//@Component
//public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//        response.setHeader("hg","hg");
//        log.info("AuthenticationEntryPoint" + JSON.toJSONString(response));
//    }
//
//}