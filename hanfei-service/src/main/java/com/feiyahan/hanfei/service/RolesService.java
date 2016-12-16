package com.feiyahan.hanfei.service;

import com.feiyahan.hanfei.pojo.Roles;

import java.util.List;

/**
 * 角色Service
 * Created by hanfei7 on 2016/12/16.
 */
public interface RolesService {
    List<Roles> findRolesByUid(int uid);
}
