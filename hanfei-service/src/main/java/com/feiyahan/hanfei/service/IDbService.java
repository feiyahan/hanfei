package com.feiyahan.hanfei.service;

import com.feiyahan.hanfei.pojo.Person;

/**
 * Created by hanfei7 on 2016/10/9.
 */
public interface IDbService {
    void insert(Person person);
    void findAll();
}
