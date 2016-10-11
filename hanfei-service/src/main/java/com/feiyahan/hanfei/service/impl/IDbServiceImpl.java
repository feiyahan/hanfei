package com.feiyahan.hanfei.service.impl;

import com.feiyahan.hanfei.mongodb.IDbDao;
import com.feiyahan.hanfei.pojo.Person;
import com.feiyahan.hanfei.service.IDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hanfei7 on 2016/10/9.
 */
@Service
public class IDbServiceImpl implements IDbService {

    @Autowired(required = false)
    private IDbDao iDbDao;

    @Override
    public void insert(Person person) {
        System.out.println(person.toString());
        iDbDao.insert(person);
    }

    @Override
    public void findAll() {
        iDbDao.findAll();
    }
}
