package com.feiyahan.hanfei.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feiyahan.hanfei.dao.PermitsDao;
import com.feiyahan.hanfei.pojo.Permits;
import com.feiyahan.hanfei.pojo.Roles;
import com.feiyahan.hanfei.service.CommonService;
import com.feiyahan.hanfei.service.PermitsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanfei7 on 2016/12/20.
 */
@Service
public class PermitsServiceImpl implements CommonService<Permits>,PermitsService {
    private final static Logger logger= LoggerFactory.getLogger(PermitsServiceImpl.class);

    @Autowired(required = false)
    private PermitsDao permitsDao;
    @Override
    public int save(Permits permits) {
        return 0;
    }

    @Override
    public int update(Permits permits) {
        return 0;
    }

    @Override
    public Permits findByParams(Permits permits) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Permits> findPermitsByRoles(List<Roles> roles) {
        List<Permits> permitsByRoles = permitsDao.findPermitsByRoles(roles);
        logger.info("permitsService permitByRoles:{}", JSONObject.toJSONString(permitsByRoles));
        return permitsByRoles;
    }
}
