package com.feiyahan.hanfei.web;


import com.feiyahan.hanfei.api.ApiParams;
import com.feiyahan.hanfei.common.utils.ApiUtil;
import com.feiyahan.hanfei.service.ApiService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口入口类
 * Created with IntelliJ IDEA <br/>
 * User: hanfei <br/>
 * Date:2015/7/30 10:16 <br/>
 * Version:V1.0 <br/>
 */
@Controller
public class ApiController {
    private static Logger log=Logger.getLogger(ApiController.class);

    @Autowired
    private ApiService apiService;

    @RequestMapping("api")
    @ResponseBody
    public Object apiEntrance(HttpServletRequest request, String jsonp){
        ApiParams permsBean = ApiUtil.parseParams(request);
        //创建一个通用的多部分解析器
        Object obj = apiService.apiServiceEntrance(permsBean);
        Map result=new HashMap();
        result.put(jsonp,obj);
        return result;
    }

    @RequestMapping("apiTest")
    @ResponseBody
    public Object apiTest(ModelAndView view){
//        view.setViewName("apiUploadFileTest");
        return view;
    }

}
