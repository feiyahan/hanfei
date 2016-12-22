package com.feiyahan.hanfei.service;

import com.feiyahan.hanfei.pojo.Permits;
import com.feiyahan.hanfei.pojo.Roles;

import java.util.List;

/**
 * 权限表
 * Created by hanfei7 on 2016/12/20.
 */
public interface PermitsService {

    /**
     * 根据角色查询权限信息
     * @param roles
     * @return
     */
    List<Permits> findPermitsByRoles(List<Roles> roles);
}
