package com.feiyahan.hanfei.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feiyahan.hanfei.dao.RolesDao;
import com.feiyahan.hanfei.pojo.Roles;
import com.feiyahan.hanfei.service.CommonService;
import com.feiyahan.hanfei.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanfei7 on 2016/12/16.
 */
@Service
public class RolesServiceImpl implements CommonService<Roles>,RolesService {

    private final static Logger logger = LoggerFactory.getLogger(RolesServiceImpl.class);

    @Autowired(required = false)
    private RolesDao rolesDao;
    @Override
    public int save(Roles roles) {
        return 0;
    }

    @Override
    public int update(Roles roles) {
        return 0;
    }

    @Override
    public Roles findByParams(Roles roles) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Roles> findRolesByUid(int uid) {
        List<Roles> rolesByUid = rolesDao.findRolesByUid(uid);
        logger.info("rolesByUid:{}", JSONObject.toJSONString(rolesByUid));
        return rolesByUid;
    }
}
