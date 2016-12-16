package com.feiyahan.hanfei.dao;

import com.feiyahan.hanfei.pojo.Roles;

import java.util.List;

/**
 * Created by hanfei7 on 2016/12/16.
 */
public interface RolesDao extends CommonDao<Roles>{
    /**
     * 根据用户ID查询用户角色
     * */
    List<Roles> findRolesByUid(int uid);
}
