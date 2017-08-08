package com.zhiyin.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public String defaultExceptionHandler(Throwable ex, HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        return "system error";
    }

//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public ApiResponse transException(IllegalArgumentException ex, HttpServletRequest request) {
//        logger.warn(ex.getMessage(), ex);
//        String message = UserCenterErrorEnum.PARAM_ERROR.getErrMsg();
//        if (StringUtils.isNotEmpty(ex.getMessage())) {
//            message = ex.getMessage();
//        }
//        int code = UserCenterErrorEnum.PARAM_ERROR.getErrCode();
//        return ApiResponse.buildFailure(code, message);
//    }
}
