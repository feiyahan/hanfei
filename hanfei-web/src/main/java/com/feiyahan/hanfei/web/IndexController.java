package com.feiyahan.hanfei.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hanfei7 on 2016/8/11.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    private final static Logger logger= LoggerFactory.getLogger(IndexController.class);
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView toIndex(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("username","freemarker");
        return mav;
    }
    @RequestMapping(value = "login")
    public ModelAndView toLogin(HttpServletRequest request,ModelAndView modelAndView){
        /*String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        logger.info("login exception {}",exceptionClassName);
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名不存在";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(LockedAccountException.class.getName().equals(exceptionClassName)){
            error = "账户已锁定";
        }else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
            error = "用户名/密码输入错误次数太多";
        }else if(AuthenticationException.class.getName().equals(exceptionClassName)){
            error = "您没有权限";
        }else if(exceptionClassName != null) {
            error = "未知错误：" + exceptionClassName;
        }
        modelAndView.addObject("error", error);*/
        logger.info("to login page");
        return modelAndView;
    }
    @RequestMapping(value = "login/auth",method = RequestMethod.POST)
    public ModelAndView loginAuth(HttpServletRequest request,ModelAndView modelAndView){
        String username = request.getParameter("username");
        String password = request.getParameter("loginPass");
        String rememberMe = request.getParameter("rememberMe");
        logger.info("username:{} password:{} remeberMe:{}",username,password,rememberMe);
        UsernamePasswordToken token=new UsernamePasswordToken(username, new Md5Hash(password).toString());
        token.setRememberMe(Boolean.parseBoolean(rememberMe));
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @ControllerAdvice
    public class DefaultExceptionHandler {
        @ExceptionHandler({UnauthorizedException.class})
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
            ModelAndView mv = new ModelAndView();
            mv.addObject("exception", e);
            mv.setViewName("unauthorized");
            return mv;
        }
    }
}
