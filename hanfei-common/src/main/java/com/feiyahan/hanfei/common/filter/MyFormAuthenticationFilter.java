package com.feiyahan.hanfei.common.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by hanfei7 on 2016/12/23.
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = this.getUsername(request);
        // 密码MD5加密
        String password = new Md5Hash(this.getPassword(request)).toString();
        return this.createToken(username, password, request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }
}
