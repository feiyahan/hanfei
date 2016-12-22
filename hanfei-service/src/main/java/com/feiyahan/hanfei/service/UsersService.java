package com.feiyahan.hanfei.service;

import com.feiyahan.hanfei.pojo.Users;

/**
 * Created by hanfei7 on 2016/12/19.
 */
public interface UsersService {
    Users findByUsername(String username);
}
