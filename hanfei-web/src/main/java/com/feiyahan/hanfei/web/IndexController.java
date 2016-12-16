package com.feiyahan.hanfei.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hanfei7 on 2016/8/11.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView toIndex(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("username","freemarker");
        return mav;
    }
    @RequestMapping(value = "login")
    public ModelAndView toLogin(){
        System.out.println("login-------------------");
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("username","freemarker");
        return mav;
    }
}
