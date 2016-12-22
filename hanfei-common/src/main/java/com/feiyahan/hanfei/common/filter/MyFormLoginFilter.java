package com.feiyahan.hanfei.common.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hanfei7 on 2016/12/19.
 */
public class MyFormLoginFilter extends PathMatchingFilter {
    private String loginUrl = "/login";
    private String successUrl = "/";
    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

    private static final Logger log = LoggerFactory.getLogger(MyFormLoginFilter.class);

    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object
            mappedValue) throws Exception {
        log.info("MyFormLoginFilter onPreHandle方法");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(SecurityUtils.getSubject().isAuthenticated()) {
            log.info("user already login");
            return true;//已经登录过
        }

        String ajaxHeader = req.getHeader("x-requested-with");

        if(isLoginRequest(req)) {
            if("post".equalsIgnoreCase(req.getMethod())) {//form表单 post 提交
                boolean loginSuccess = login(req); //登录
                if(loginSuccess) {
                    return redirectToSuccessUrl(req, resp);
                }
            }
            return true;//继续过滤器链
        } else if (!StringUtils.isEmpty(ajaxHeader) && "XMLHttpRequest".equalsIgnoreCase(ajaxHeader)) {
            //如果是ajax请求，引导到登录页面
            //如果是ajax请求响应头会有，x-requested-with
            resp.setHeader("sessionstatus", "timeout");//在响应头设置session状态
            /*redirectToLogin(req,resp);*/
            return false;
        }else{//保存当前地址并重定向到登录界面
            saveRequestAndRedirectToLogin(req, resp);
            return false;
        }
    }

    private void redirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.issueRedirect(req,resp,loginUrl);
    }

    private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        WebUtils.redirectToSavedRequest(req, resp, successUrl);
        return false;
    }
    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.saveRequest(req);
        WebUtils.issueRedirect(req, resp, loginUrl);
    }
    private boolean login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        log.info("login username:{} password:{} rememberMe:{}",username,password,rememberMe);
        try {
            UsernamePasswordToken token=new UsernamePasswordToken(username, new Md5Hash(password).toString());
            token.setRememberMe(StringUtils.isEmpty(rememberMe)?false:true);
            SecurityUtils.getSubject().login(token);
        } catch (Exception e) {
            //清除session中当前user缓存
            Subject currentUser = SecurityUtils.getSubject();
            if(null != currentUser){
                currentUser.logout();
            }
            req.setAttribute(failureKeyAttribute, e.getClass().getName());
            return false;
        }
        return true;
    }
    private boolean isLoginRequest(HttpServletRequest req) {
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));
    }
}
