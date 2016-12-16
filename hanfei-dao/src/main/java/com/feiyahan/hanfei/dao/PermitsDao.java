package com.feiyahan.hanfei.dao;

import com.feiyahan.hanfei.pojo.Permits;
import com.feiyahan.hanfei.pojo.Roles;

import java.util.List;

/**
 * Created by hanfei7 on 2016/12/16.
 */
public interface PermitsDao extends CommonDao<Roles>{
    /**
     * 根据角色查询用户权限
     * */
    List<Permits> findPermitsByRoles(List<Roles> roles);
}
