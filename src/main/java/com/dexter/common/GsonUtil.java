package com.dexter.common;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Module ID: </br> Comments：本类主要处理</br> Create Date: 2013-4-27
 * 
 * @author zhangbo
 * @version 1.0
 */
public class GsonUtil {
	private static GsonBuilder gb = new GsonBuilder();
	private static Gson g;

	public static Map<String, Object> parseDataToMap(String data) {
		g = gb.create();
		Map<String, Object> map = g.fromJson(data,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		return map;
	}

	public static <T> T parseObject(String data, Type t) {
		g = gb.create();
		return g.fromJson(data, t);
	}

	public static String toJson(Object json) {
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		return g.toJson(json);
	}

	public static void main(String[] args) throws Exception {
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); Map<String,
		 * Object> body = new HashMap<String, Object>(); body.put("abc",
		 * "嗯ok饿呢"); map.put("body", body);
		 */
		/*
		 * File file = new File("d:/aa.json"); FileInputStream inputStream = new
		 * FileInputStream(file); String json =
		 * FileUtils.getStringFromInpuStram(inputStream,Constant.charset);
		 */
		String json = "{'body':{'terminate':[{'duration':'90571','time':'15:37:03','session_id':'BA70E33DED57AFD24485CFF1ACC7DFD5','activities':[{'com.cmmobi.statistics.activitys.MainActivity':'60450'}],'dntr':'180','date':'2013-04-28','uptr':'821'}]},'header':{'os':'Android','access_subtype':'WIFI','package_name':'com.cmmobi.statistics','cpu':'armeabi-v7a','appkey':'123456','sdk_version':'1.0','app_version':'1.0','device_id':'351554054859419','resolution':'720*1184','access':'WIFI','version_code':'1','os_version':'4.2.1','idmd5':'40F8F8667B99E588AB3B02BB84E3135E','country':'cn','device_model':'Galaxy Nexus','timezone':'Asia\\/Shanghai','sdk_type':'Android','mc':'a0:0b:ba:e9:56:0f','req_time':'611','carrier':'CHN-CUGSM','language':'zh','channel':'channel'}}";

	}
}
