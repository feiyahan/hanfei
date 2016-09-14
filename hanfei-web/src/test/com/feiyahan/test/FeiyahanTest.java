package com.feiyahan.test;

import com.feiyahan.hanfei.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hanfei7 on 2016/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class FeiyahanTest {
    private static final Logger logger= LoggerFactory.getLogger(FeiyahanTest.class);


    @Autowired
    private IndexService indexService;

    @Test
    public void printProperties(){
        indexService.printProperties();
    }

}
