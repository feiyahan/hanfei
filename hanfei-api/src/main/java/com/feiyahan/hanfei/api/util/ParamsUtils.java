package com.feiyahan.hanfei.api.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA <br/>
 * User: hanfei <br/>
 * Date:2015/7/30 11:05 <br/>
 * Version:V1.0 <br/>
 */
public class ParamsUtils {
    private static final Logger log = Logger.getLogger(ParamsUtils.class);
    /**
     * 获取request请求中指定Key的值
     * @param request
     * @param key
     * @return
     */
    public static String getParameter(HttpServletRequest request,String key){
        String value = "";
        try {
            value = URLDecoder.decode(StringUtils.isEmpty(request.getParameter(key)) ? "" : request.getParameter(key), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码异常"+e);
//            e.printStackTrace();
        }
        return value;
    }

    public static Long getLongParameter(HttpServletRequest request,String key){
        String timestamp = request.getParameter(key);
        if(StringUtils.isEmpty(timestamp)){
            return System.currentTimeMillis();
        }
        return Long.parseLong(timestamp);
    }
}
