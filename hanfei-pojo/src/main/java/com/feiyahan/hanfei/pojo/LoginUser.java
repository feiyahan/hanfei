package com.feiyahan.hanfei.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hanfei7 on 2016/12/16.
 */
public class LoginUser {

    private Users user;

    private List<Roles> roles;

    private List<Permits> permitses;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Permits> getPermitses() {
        return permitses;
    }

    public void setPermitses(List<Permits> permitses) {
        this.permitses = permitses;
    }

    public Set<String> getRolesCode(){
        List<Roles> roles=getRoles();
        Set<String> set=new HashSet<String>();
        for (Roles role : roles) {
            set.add(role.getRoleCode());
        }
        return set;
    }
}
