package com.feiyahan.hanfei.web;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hanfei7 on 2016/12/23.
 */
//@ControllerAdvice
public class DefaultExceptionHandler {
    /*private final static Logger logger= LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        logger.error("系统异常，去往错误页",e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("error");
        return mv;
    }*/
}
