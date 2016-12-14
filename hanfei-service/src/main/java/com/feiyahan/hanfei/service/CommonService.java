package com.feiyahan.hanfei.service;

/**
 * Created by hanfei7 on 2016/12/14.
 */
public interface CommonService<T> {
    int save(T t);
    int update(T t);
    T findByParams(T t);
    int delete(int id);
}
