package com.feiyahan.jdjr.service.impl;

import com.feiyahan.jdjr.pojo.UserInfo;
import com.feiyahan.jdjr.service.IndexService;
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
}
