package com.feiyahan.hanfei.mongodb.impl;

import com.feiyahan.hanfei.mongodb.IDbDao;
import com.feiyahan.hanfei.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hanfei7 on 2016/10/9.
 */
@Repository
public class IDbDaoImpl implements IDbDao {

    @Autowired(required = false)
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Object object) {
        mongoTemplate.insert(object);
    }

    @Override
    public void findAll() {
        List<Person> all = mongoTemplate.findAll(Person.class);
        System.out.println(all.toString());
    }
}
