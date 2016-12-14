package com.feiyahan.hanfei.dao;

import com.feiyahan.hanfei.pojo.Users;

/**
 * 用户表DAO
 * Created by hanfei7 on 2016/8/12.
 */
public interface UsersDao {
    int save(Users users);
    int update(Users users);
    Users findByParams(Users users);
    int delete(int uid);
}
