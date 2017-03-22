package com.feiyahan.hanfei.web;

import com.alibaba.fastjson.JSONObject;
import com.feiyahan.hanfei.pojo.Users;
import com.feiyahan.hanfei.service.CommonService;
import com.feiyahan.hanfei.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by hanfei7 on 2016/12/25.
 */
@Controller
@RequestMapping(value = "admin")
public class UsersController {

    private final static Logger logger= LoggerFactory.getLogger(UsersController.class);
    @Autowired(required = false)
    private UsersService usersService;
    @Autowired(required = false)
    private CommonService<Users> commonService;

    @RequestMapping(value = "user/list")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView("admin/userList");

        List<Users> users = usersService.findAllUsers();
        logger.info("allUser {}", JSONObject.toJSONString(users));
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @RequestMapping(value = "user/add")
    public ModelAndView userAdd() {
        ModelAndView modelAndView = new ModelAndView("admin/userAdd");

        return modelAndView;
    }
}
