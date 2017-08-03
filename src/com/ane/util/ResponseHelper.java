package com.ane.util;

import com.google.gson.Gson;

/**
 * 返回数据处理帮助类
 * @author Administrator
 *
 */
public class ResponseHelper {

	/**
	 * 将obj转换为json
	 * @param obj
	 * @return String
	 */
	public static String getJson(Object obj){
		if(null !=  obj){
			return new Gson().toJson(obj);
		}
		return "";
	}
	/**
	 * 构造成功返回json数据
	 * @param msg
	 * @return
	 */
	public static String buildSuccessResp(String msg){
		
		return getJson(new ResponseBean(ResultStatus.SUCCESS,msg));
	}
	/**
	 * 构造异常返回json数据
	 * @param msg
	 * @return
	 */
	public static String buildErrorResp(String msg){
		
		return getJson(new ResponseBean(ResultStatus.ERROR,msg));
	}
}
