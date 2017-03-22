package com.feiyahan.hanfei.common.utils;


import com.feiyahan.hanfei.api.ApiParams;
import com.feiyahan.hanfei.api.util.ParamsUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Api帮助类
 * @author hf
 * @Date 2015-8-7 09:41:10
 */
public class ApiUtil {

	private static final Logger log = Logger.getLogger(ApiUtil.class);
	private static final String SIGN_SEPARATOR="ZAQ12WSX";
	private static final long TIMESTAMP_TIMEOUT=1800000;//时间戳30分钟超时
	/*@Autowired
	private static EhCacheCacheManager cacheManager;*/
	private ApiUtil() {}

	/**
	 * request转为api参数对象
	 * @param request
	 * @return
	 */
	public static ApiParams parseParams(HttpServletRequest request) {
		ApiParams bean = new ApiParams();
		String dataJson = ParamsUtils.getParameter(request, "data");
		bean.setDataJson(dataJson);
		bean.setTimestamp(ParamsUtils.getLongParameter(request, "timestamp"));
		bean.setSign(ParamsUtils.getParameter(request, "sign"));
		bean.setUid(ParamsUtils.getParameter(request, "uid"));
		bean.setAct(ParamsUtils.getParameter(request, "act"));
		bean.setVer(ParamsUtils.getParameter(request, "ver"));
		bean.setOs(ParamsUtils.getParameter(request, "os"));
		log.info("request Params={\"ip\":\""+request.getRemoteHost()+"\",\"os\":\"" + bean.getOs() + "\",\"ver\":\"" + bean.getVer() + "\",\"act\":\"" + bean.getAct() + "\",\"uid\":\"" + bean.getUid()
				+ "\",\"sign\":\"" + bean.getSign() + "\",\"timestemp\":\"" + bean.getTimestamp() + "\",\"data\":"+dataJson+"}");
//		entity.setData(JSONMap.valueOf(dataJson));
		bean.setData(JsonUtil.parseJSON2Map(dataJson));

		MultipartHttpServletRequest multiRequest = ApiUtil.createMultiRequest(request);
		if(null!=multiRequest){
			bean.setParam("multiRequest",multiRequest);
		}

		return bean;
	}

	/**
	 * 创建一个通用的多部分解析器 并转成多部分request
	 * @param request
	 */
	private static MultipartHttpServletRequest createMultiRequest(HttpServletRequest request){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)) {
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			return multiRequest;
		}else{
			return null;
		}
	}

	/**
	 * 验证URL是否合法
	 * @param request
	 * @return
	 */
	public static OperationResult validateUrl(HttpServletRequest request){
		String action = request.getParameter("act");
//		String version = request.getParameter("ver");
		String timestamp = request.getParameter("timestamp");
		if(StringUtils.isEmpty(action)){
			return OperationResult.OPER_TYPE_IS_NULL;
		}

//		if(StringUtils.isEmpty(version)){
//			return OperationResult.VERSION_IS_NULL;
//		}

		if(StringUtils.isEmpty(timestamp)){
			return OperationResult.TIMESTAMP_IS_NULL;
		}/*else if(Long.parseLong(timestamp)-System.currentTimeMillis()>TIMESTAMP_TIMEOUT){
			return OperationResult.TIMESTAMP_EXPIRED;//时间戳过期
		}*/
		return OperationResult.SUCCESS;
	}

	/**
	 * 加密
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getCipherkey(String str1,String str2){
//		return MD5.getMD5Code(str1 + SIGN_SEPARATOR + str2).toUpperCase();
		//shiro Md5加密
		return new Md5Hash(str1 + SIGN_SEPARATOR + str2).toHex().toUpperCase();

	}
	/**
	 * 包括了验证结果和执行结果
	 * @author zyang
	 */
	public enum OperationResult {
		
		SUCCESS(true, 0, "操作成功"),
		FAILED(false, 1, "操作失败"),
		TIMEOUT(false, 2, "操作超时"),
		POST_ONLY(false, 4, "仅接受POST类型的请求"),
		JSONP_ONLY(false, 5, "仅接受jsonp类型的请求"),
		VALID(true, 6, "通过验证"),					// 表示验证有效(内部使用)
//		IGNORE(true, 7, "忽略操作"),					// 用于机构未开通双向一站式登录，无需发送请求的情形
//		ASYNC_UNKNWON(true, 50, "异步结果不可知"),	// 用于表示异步调用下，结果未知的情形
		UNKNOWN(false, 99, "未知结果"),

		//用户信息错误相关返回码
		SMS_IS_NULL(false,1001,"获取验证码失败"),
		MOBILE_REGISTERED(false,1002,"手机号已经注册，请直接登录"),
		REGISTER_FAILED(false,1003,"注册失败"),
		USER_NOT_EXIST(false, 1004, "用户不存在"),
		USERNAME_ILLEGAL(false, 1005, "用户名不合法"),
		USERNAME_DUPLICATED(false, 1006, "用户名重复"),
		USER_OR_PWD_ERROR(false, 1007, "用户名或密码错误"),
		SMS_IS_ERROR(false,1008,"验证码错误"),
		SMS_IS_OUT10(false,1009,"今天发送短信已到上限"),

		// 通用验证返回码
		TIMESTAMP_IS_NULL(false, 2001, "缺少时间戳参数"),
		TIMESTAMP_EXPIRED(false, 2002, "时间戳已过期"),
		SIGN_OR_UID_IS_NULL(false, 2003, "缺少sign或uid参数"),
		SING_IS_WRONG(false, 2004, "sign验证失败"),
		OPER_TYPE_NOT_EXIST(false, 2005, "操作类型不存在"),
		OPER_TYPE_IS_NULL(false,2006,"操作类型为空"),
		OPER_DATA_IS_WRONG(false, 2007, "业务参数有误"),
		VERSION_IS_NULL(false,2008,"版本号为空"),
		;

		OperationResult(boolean valid, int code, String message) {
			this.valid = valid;
			this.code = code;
			this.message = message;
		}
		
		private boolean valid;
		private int code;
		private String message;
		
		public boolean isValid() {
			return valid;
		}
		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
		
		public Map<String, Object> toResultMap() {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", code);
			resultMap.put("message", message);
			return resultMap;
		}
		
		public static OperationResult parseCode(Integer code) {
			if(code == null) {
				return OperationResult.FAILED;
			}
			for(OperationResult result: OperationResult.values()) {
				if(result.getCode() == code) {
					return result;
				}
			}
			return OperationResult.UNKNOWN;
		}

		@Override
		public String toString() {
			return "code:"+this.getCode()+",message:"+this.getMessage();
		}
	}

}
