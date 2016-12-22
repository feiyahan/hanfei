package com.feiyahan.hanfei.service.security;

import com.alibaba.fastjson.JSONObject;
import com.feiyahan.hanfei.dao.PermitsDao;
import com.feiyahan.hanfei.dao.RolesDao;
import com.feiyahan.hanfei.dao.UsersDao;
import com.feiyahan.hanfei.pojo.LoginUser;
import com.feiyahan.hanfei.pojo.Users;
import com.feiyahan.hanfei.service.CommonService;
import com.feiyahan.hanfei.service.PermitsService;
import com.feiyahan.hanfei.service.RolesService;
import com.feiyahan.hanfei.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hanfei7 on 2016/12/16.
 */
public class UserRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 用户DAO
     */
    @Autowired(required = false)
    private UsersService usersService;


    /**
     * 角色DAO
     */
    @Autowired(required = false)
    private RolesService rolesService;
    /**
     * 权限DAO
     */
    @Autowired(required = false)
    private PermitsService permitsService;

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
        logger.info("权限验证，doGetAuthorizationInfo()方法，loginName:{}", loginName);
        //到数据库查是否有此对象
        LoginUser loginUser = (LoginUser) usersService.findByUsername(loginName);

        if (loginUser != null) {
            //查询当前用户的角色
            loginUser.setRoles(rolesService.findRolesByUid(loginUser.getUid()));
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            //用户的角色集合
            authorizationInfo.setRoles(loginUser.getRolesCode());
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            /*List<Role> roleList=user.getRoleList();
            for (Role role : roleList) {
                info.addStringPermissions(role.getPermissionsName());
            }*/
            logger.info("权限验证，doGetAuthorizationInfo()方法，返回：{}", JSONObject.toJSONString(authorizationInfo));
            return authorizationInfo;
        }
        logger.info("权限验证，doGetAuthorizationInfo()方法，user:{} 不存在，返回NULL", loginName);
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
        logger.info("登录认证，入参：{}",JSONObject.toJSONString(authenticationToken));
        //System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        String username = login_token.getUsername();
        Users user = usersService.findByUsername(username);
        logger.info("userRealm 根据用户名查询用户，返回：{}",JSONObject.toJSONString(user));
        if (null != user) {
            logger.info("loginUser is not null");
            if( 1 == user.getUserStatus()){
                LoginUser loginUser=LoginUser.getLoginUserByUser(user);
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getLoginPass(), getName());
                loginUser.setRoles(rolesService.findRolesByUid(user.getUid()));
//                loginUser.setPermitses(permitsService.findPermitsByRoles(loginUser.getRoles()));
                this.setSession("currentUser", loginUser);
                logger.info("return authcInfo:{}",JSONObject.toJSONString(authcInfo));
                return authcInfo;
            }else {
                logger.info("被锁定的账户：{}",username);
                throw new LockedAccountException("Locked Account "+username);
            }
        } else {
            logger.info("未知账户：{}",username);
            throw  new UnknownAccountException("unknown account "+username);
        }
    }

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            logger.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                logger.info("Session set currentUser:{}",JSONObject.toJSONString(value));
                session.setAttribute(key, value);
            }
        }
    }
}
