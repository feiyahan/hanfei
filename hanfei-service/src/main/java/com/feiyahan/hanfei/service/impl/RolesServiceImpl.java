package com.feiyahan.hanfei.service.impl;

import com.feiyahan.hanfei.pojo.Roles;
import com.feiyahan.hanfei.service.CommonService;
import com.feiyahan.hanfei.service.RolesService;

import java.util.List;

/**
 * Created by hanfei7 on 2016/12/16.
 */
public class RolesServiceImpl implements CommonService<Roles>,RolesService {
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
        return null;
    }
}
