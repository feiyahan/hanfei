package com.feiyahan.hanfei.service.impl;

import com.feiyahan.hanfei.pojo.UserInfo;
import com.feiyahan.hanfei.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by hanfei7 on 2016/8/11.
 */
@Service
public class IndexServiceImpl implements IndexService {
    private final static Logger logger= LoggerFactory.getLogger(IndexServiceImpl.class);

    @Value("${redis.timeout}")
    private int redisTimeout;

    @Value("${user.bean}")
    private String userInfo;
    @Value("${flag.trueOrFalse}")
    private boolean trueOrFalse;

    @Override
    public void printProperties() {
        logger.info("redisTimeout={}",redisTimeout==2);
        logger.info("userInfoJson={}",userInfo);
        logger.info("flag.trueOrFalse={}",trueOrFalse && false);
//        UserInfo userInfo = JSONObject.parseObject(java.lang.String.valueOf(userInfoJson), UserInfo.class);
//        logger.info("username={},password={}",userInfo.getUsername(),userInfo.getPassword());
    }
    @Override
    public String printSuccess(int num) {
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for (int i=0;i<100;i++){
            String result = "result "+i;
        }
        String result = "result "+num;
        System.out.println(result);
        return result;
    }
}
