package com.feiyahan.hanfei.service.impl;

import com.feiyahan.hanfei.api.ApiParams;
import com.feiyahan.hanfei.api.ApiResult;
import com.feiyahan.hanfei.common.utils.ApiUtil;
import com.feiyahan.hanfei.dao.ApiDao;
import com.feiyahan.hanfei.pojo.ApiSource;
import com.feiyahan.hanfei.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA <br/>
 * User: hanfei <br/>
 * Date:2015/7/30 14:57 <br/>
 * Version:V1.0 <br/>
 */
@Service
public class ApiServiceImpl extends ApplicationObjectSupport implements ApiService {
    static final Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);
    private static final String SIGN_SEPARATOR = "ZAQ12WSX";

    @Autowired(required = false)
    private ApiDao apiDao;

    public ApiResult apiServiceEntrance(ApiParams paramsBean) {
        ApiResult result = new ApiResult();
        String action = paramsBean.getAct();//action请求编码
        String version = StringUtils.isEmpty(paramsBean.getVer()) ? "1" : paramsBean.getVer();//api版本号，默认为1

        ApiSource apiEntry = apiDao.findByActionAndVersion(action, version);
        if (null == apiEntry) {
            log.warn("action or version error!");
            result.setCode(ApiUtil.OperationResult.OPER_TYPE_NOT_EXIST.getCode());
            result.setMessage(ApiUtil.OperationResult.OPER_TYPE_NOT_EXIST.getMessage());
            return result;
        }
        /*String classMethod=apiEntry.getClassMethod();
        String className=classMethod.substring(0, classMethod.lastIndexOf("."));//类名
        String methodName=classMethod.substring(classMethod.lastIndexOf(".") + 1);//方法名*/
        String className = apiEntry.getClassName();//类名
        String methodName = apiEntry.getMethodName();//方法名
        try {

            //获取类对象
            Class clsObj = Class.forName(className);
            Object obj = this.getApplicationContext().getBean(clsObj);
            //获取参数对象
            Class[] params = new Class[]{ApiParams.class};
            //获取方法
            Method method = clsObj.getMethod(methodName, params);
            //执行方法
            Object invoke = method.invoke(obj, paramsBean);
            if (null != invoke) {
                result.setCode(ApiUtil.OperationResult.SUCCESS.getCode());
                result.setMessage(ApiUtil.OperationResult.SUCCESS.getMessage());
                result.setData(invoke);
            } else {
                result.setCode(ApiUtil.OperationResult.FAILED.getCode());
                result.setMessage(ApiUtil.OperationResult.FAILED.getMessage());
            }
        } catch (ClassNotFoundException e) {
            log.warn("ApiService ClassName:{} Error", className, e);
            result.setCode(ApiUtil.OperationResult.UNKNOWN.getCode());
            result.setMessage(ApiUtil.OperationResult.UNKNOWN.getMessage());
        } catch (NoSuchMethodException e) {
            log.warn("ApiService methodName:{} Error", methodName, e);
            result.setCode(ApiUtil.OperationResult.UNKNOWN.getCode());
            result.setMessage(ApiUtil.OperationResult.UNKNOWN.getMessage());
        } catch (InvocationTargetException e) {
            log.warn("ApiService Error", e);
            result.setCode(ApiUtil.OperationResult.UNKNOWN.getCode());
            result.setMessage(ApiUtil.OperationResult.UNKNOWN.getMessage());
        } catch (IllegalAccessException e) {
            log.warn("ApiService Error" + e);
            result.setCode(ApiUtil.OperationResult.UNKNOWN.getCode());
            result.setMessage(ApiUtil.OperationResult.UNKNOWN.getMessage());
        } catch (Exception e) {
            log.warn("ApiService Error", e);
            result.setCode(ApiUtil.OperationResult.UNKNOWN.getCode());
            result.setMessage(ApiUtil.OperationResult.UNKNOWN.getMessage());
        }finally {
            return result;
        }
    }
}
