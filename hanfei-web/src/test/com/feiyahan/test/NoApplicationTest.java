package com.feiyahan.test;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;

/**
 * Created by hanfei7 on 2016/8/18.
 */
public class NoApplicationTest {

    private boolean exceptionReturn(){
        boolean flag=false;
        try {
            flag=true;
            System.out.println("========try========");
            Integer.parseInt("a");
        } catch (Exception e) {
            System.out.println("====catch==="+e);
            flag=false;
            throw new RuntimeException(e);
        }finally {
            System.out.println("=======finally====");
            flag=true;
            return flag;
        }
    }

    @Test
    public void test(){
        System.out.println(exceptionReturn());
    }

    @Test
    public void ThousandBits(){
        String a="12345333333343.343";
        System.out.println(NumberFormat.getInstance().format(Double.parseDouble(a)));
        String str="\t\n";
        System.out.println(org.apache.commons.lang.StringUtils.isBlank(str));
    }

}
