package com.feiyahan.hanfei.dao;

import com.feiyahan.hanfei.pojo.Users;

/**
 * Created by hanfei7 on 2016/12/16.
 */
public interface CommonDao<T> {
    int save(T t);

    int update(T t);

    T findByParams(T t);

    int delete(int id);
}
