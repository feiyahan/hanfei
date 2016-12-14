package com.feiyahan.hanfei.service.impl;

import com.feiyahan.hanfei.dao.UsersDao;
import com.feiyahan.hanfei.pojo.Users;
import com.feiyahan.hanfei.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hanfei7 on 2016/12/14.
 */
@Service
public class UsersServiceImpl implements CommonService<Users> {

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
        return usersDao.findByParams(users);
    }

    @Override
    public int delete(int id) {
        return usersDao.delete(id);
    }
}
