package com.feiyahan.hanfei.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanfei7 on 2016/12/19.
 */
public class JsonUtil {
    /**
     * Json转Map<String,Object>
     * @param jsonStr
     * @return
     */
    public static Map parseJSON2Map(String jsonStr){
        Map<?,?> map = new HashMap();
        if(StringUtils.isEmpty(jsonStr))
            return map;

        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  map;
    }

    /**
     * map转javaBean
     * @param map
     * @param obj
     */
    public static void map2Bean(Map<String,Object> map,Object obj){
        if(map==null||obj==null){
            return;
        }else {
            try {
                BeanUtils.populate(obj,map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
