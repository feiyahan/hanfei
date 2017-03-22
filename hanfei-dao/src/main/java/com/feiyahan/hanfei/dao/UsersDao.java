package com.feiyahan.hanfei.dao;

import com.feiyahan.hanfei.pojo.Users;

import java.util.List;

/**
 * 用户表DAO
 * Created by hanfei7 on 2016/8/12.
 */
public interface UsersDao extends CommonDao<Users>{
    int save(Users users);

    int update(Users users);

    Users findByParams(Users users);

    int delete(int uid);

    Users findByUsername(String username);

    List<Users> findAllUsers();
}
