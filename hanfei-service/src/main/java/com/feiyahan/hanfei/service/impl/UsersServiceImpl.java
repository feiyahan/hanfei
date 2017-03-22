package com.feiyahan.hanfei.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feiyahan.hanfei.dao.UsersDao;
import com.feiyahan.hanfei.pojo.Users;
import com.feiyahan.hanfei.service.CommonService;
import com.feiyahan.hanfei.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanfei7 on 2016/12/14.
 */
@Service
public class UsersServiceImpl implements CommonService<Users>,UsersService {

    private final static Logger logger= LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired(required = false)
    private UsersDao usersDao;

    @Override
    public int save(Users users) {
        return usersDao.save(users);
    }

    @Override
    public int update(Users users) {
        return usersDao.update(users);
    }

    @Override
    public Users findByParams(Users users) {
        logger.info("根据参数查询用户信息，Service入参：{}", JSONObject.toJSONString(users));
        Users user = usersDao.findByParams(users);
        logger.info("根据参数查询用户信息，Service出参：{}",JSONObject.toJSONString(user));
        return user;
    }

    @Override
    public int delete(int id) {
        return usersDao.delete(id);
    }

    @Override
    public Users findByUsername(String username) {
        logger.info("根据用户名查询用户信息，Service入参：{}", username);
        Users user = usersDao.findByUsername(username);
        logger.info("根据用户名查询用户信息，Service出参：{}",JSONObject.toJSONString(user));
        return user;
    }

    @Override
    public List<Users> findAllUsers() {
        return usersDao.findAllUsers();
    }
}
