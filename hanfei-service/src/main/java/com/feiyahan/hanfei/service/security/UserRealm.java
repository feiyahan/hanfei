package com.feiyahan.hanfei.service.security;

import com.feiyahan.hanfei.dao.PermitsDao;
import com.feiyahan.hanfei.dao.RolesDao;
import com.feiyahan.hanfei.dao.UsersDao;
import com.feiyahan.hanfei.pojo.LoginUser;
import com.feiyahan.hanfei.pojo.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hanfei7 on 2016/12/16.
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 用户DAO
     */
    @Autowired(required = false)
    private UsersDao usersDao;
    /**
     * 角色DAO
     */
    @Autowired(required = false)
    private RolesDao rolesDao;
    /**
     * 权限DAO
     */
    @Autowired(required = false)
    private PermitsDao permitsDao;

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
        Users user = new Users();
        user.setUsername(loginName);
        LoginUser loginUser = new LoginUser();
        //到数据库查是否有此对象
        user = usersDao.findByParams(user);
        loginUser.setUser(user);
        if (user != null) {
            //查询当前用户的角色
            loginUser.setRoles(rolesDao.findRolesByUid(user.getUid()));
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.setRoles(loginUser.getRolesCode());
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            /*List<Role> roleList=user.getRoleList();
            for (Role role : roleList) {
                info.addStringPermissions(role.getPermissionsName());
            }*/
            return info;
        }
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
        UsernamePasswordToken login_token = (UsernamePasswordToken) authenticationToken;
        //System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        Users user = new Users();
        user.setUsername(login_token.getUsername());
        user = usersDao.findByParams(user);

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);

        if (null != user && 1 == user.getUserStatus()) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getLoginPass(), getName());
            loginUser.setRoles(rolesDao.findRolesByUid(user.getUid()));
            loginUser.setPermitses(permitsDao.findPermitsByRoles(loginUser.getRoles()));
            this.setSession("loginUser", loginUser);

            return authcInfo;
        } else {
            throw new UnknownAccountException();
        }
    }

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }

        }
    }
}
