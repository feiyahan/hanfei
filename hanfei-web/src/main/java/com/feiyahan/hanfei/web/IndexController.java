package com.feiyahan.hanfei.web;

import com.feiyahan.hanfei.pojo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hanfei7 on 2016/8/11.
 */
@Controller
public class IndexController {
    private final static Logger logger= LoggerFactory.getLogger(IndexController.class);

    /**
     * 首页
     * */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView toIndex(){
        ModelAndView mav = new ModelAndView("/index");
        logger.info("to index page");
        return mav;
    }
    /**
     * 后台管理首页
     * */
    @RequestMapping(value = "/admin/index",method = RequestMethod.GET)
    public ModelAndView toAdminIndex(){
        ModelAndView mav = new ModelAndView("admin/index");
        logger.info("to adminIndex page");
        return mav;
    }

}
