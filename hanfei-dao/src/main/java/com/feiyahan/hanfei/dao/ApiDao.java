package com.feiyahan.hanfei.dao;


import com.feiyahan.hanfei.pojo.ApiSource;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA <br/>
 * User: hanfei <br/>
 * Date:2015/7/30 15:24 <br/>
 * Version:V1.0 <br/>
 */

public interface ApiDao {
    ApiSource findByActionAndVersion(@Param("action") String action, @Param("version") String version);
}
