package com.feiyahan.test;

import com.feiyahan.hanfei.pojo.Person;
import com.feiyahan.hanfei.service.AkkaService;
import com.feiyahan.hanfei.service.IDbService;
import com.feiyahan.hanfei.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    private AkkaService akkaService;

    @Qualifier("IDbServiceImpl")
    @Autowired
    private IDbService iDbService;

    @Test
    public void printProperties(){
        indexService.printProperties();
    }

    @Test
    public void testAkkaService(){
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new akkaClient(i, "host"+i));
            thread.start();	//同时启动5个客户端请求Master
        }
    }

    class akkaClient implements Runnable{
        int num;
        String host;

        public akkaClient(int num,String host) {
            this.num=num;
            this.host=host;
        }

        @Override
        public void run() {
            System.out.println("service "+num +" start");
            String result = akkaService.service2Akka(num, host);
            System.out.println("service "+num +" "+result);
        }
    }

    @Test
    public void testMongo(){
        System.out.println();
        iDbService.insert(new Person("joy",20));
        iDbService.findAll();
    }
}
