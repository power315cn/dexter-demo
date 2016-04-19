package com.dexter.string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


public class StringDemo {

	public static void main(String[] args) {
		String strs = "";
		System.out.println(Integer.parseInt(strs));
		long past = System.currentTimeMillis();
		try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long now = System.currentTimeMillis();
//		long diff = (now - past)/1000;
		long diff = (1501)/1000;
		System.out.println(diff);
//		String url = "http://h5.luokuang.com:8080/h5/liuliangchongzhi"
//				+ "/?test=001&userid=0f9f831c3bbca1fe4b293094e3d92032&yinyue=goon&onrail=1&youxi=gggggb";
		/*String url0 = "";
		System.out.println(url2Json(url0));
		String url = "http://h5.luokuang.com:8080/h5/liuliangchongzhi"
				+ "/";
		System.out.println(url2Json(url));
		String url1 = "http://h5.luokuang.com:8080/h5/liuliangchongzhi"
				+ "/?";
		System.out.println(url2Json(url1));
		String url2 = "http://h5.luokuang.com:8080/h5/liuliangchongzhi"
				+ "/?11";
		System.out.println(url2Json(url2));
		String url3 = "http://h5.luokuang.com:8080/h5/liuliangchongzhi"
				+ "/?11=";
		System.out.println(url2Json(url3));
		String url4 = "http://h5.luokuang.com:8080/h5/liuliangchongzhi"
				+ "/?11=dd&dduserid=lll&ss=&=";
		System.out.println(url2Json(url4));
		String url5 = "http://h5.luokuang.com:8080/h5/liuliangchongzhi"
				+ "/?11=dd";
		System.out.println(url2Json(url5));*/
		
		String str = "[001FF20BC9B2, 001FF20BC957, 001FF20BC9FD, 001FF20BC97A, 001FF20CB490, 001FF20CB639, 001FF20CB688, 001FF20CB59A, 001FF20CB648, 001FF20CB635, 001FF20CB7ED, 001FF20CB485, 001FF20CB5F4, 001FF20CB661, 001FF20CB573, 001FF20CB571, 001FF20CB4B2, 001FF20CB6C2, 001FF20CB460, 001FF20CB457, 001FF20CB615, 001FF20CB798, 001FF20C9AFE, 001FF20CB4BA, 001FF20CB5FE, 001FF20CB719, 001FF20CB7EC, 001FF20CB54C, 001FF20CB689, 001FF20CB7E5, 001FF20CB60E, 001FF20CB55F, 001FF20CB7D2, 001FF20CB59D, 001FF20CB4EA, 001FF20CB49E, 001FF20CB5EC, 001FF20CB44F, 001FF20CB731, 001FF20CB5A6, 001FF20CB6AC, 001FF20CB52D, 001FF20CB79C, 001FF20CB72E, 001FF20C98E2, 001FF20CB41A, 001FF20CB43D, 001FF20CB513, 001FF20C9942, 001FF20CB76B, 001FF20CB724, 001FF20CB500, 001FF20CB4B5, 001FF20CB423, 001FF20CB7DE, 001FF20CB597, 001FF20CB6AD, 001FF20C992F, 001FF20CB612, 001FF20CB7D6, 001FF20E0D11, 001FF20E0F23, 001FF20E0F1E, 001FF20E06A0, 001FF20E0D85, 001FF20E0ECD, 001FF20E0E73, 001FF20E0E70, 001FF20E05D8, 001FF20E0BA8, 001FF20E0E60, 001FF20E0D2B, 001FF20E0B82, 001FF20E0B8F, 001FF20E0D10, 001FF20E0F08, 001FF20E0E33, 001FF20E0D73, 001FF20E05E7, 001FF20E0B9E, 001FF20E0DB3, 001FF20E0D32, 001FF20E0BEF, 001FF20E0D0E]";
//		String[] strArray = str.substring(0, str.length());
		System.out.println(str.substring(0, str.length()));
		
	}
	
	public static String urlString(String url) {
		String[] urls = url.split("[?]");
		if(ArrayUtils.isEmpty(urls) || urls.length==1) {
			return url;
		}
		String[] params = urls[1].split("&");
		if(ArrayUtils.isEmpty(params) || params.length==1) {
			return url;
		}
		StringBuffer sb = new StringBuffer(urls[0]);
		sb.append("?");
		for(String param:params) {
//			System.out.println(u);
			if(param.indexOf("userid=")== -1 && param.indexOf("onrail=")==-1) {
//				System.out.println(u);
				sb.append(param).append("&");
			}
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	
	public static JSONObject url2Json(String url) {
		if(StringUtils.isEmpty(url) || url.indexOf("?") == -1) {
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		String[] urls = url.split("[?]");
		map.put("fullurl", url);
		if(ArrayUtils.isEmpty(urls) || urls.length==1) {
			return JSONObject.fromObject(map);
		}
		String[] params = urls[1].split("&");
		if(ArrayUtils.isEmpty(params) || params.length==1) {
				String[] param = params[0].split("=");
				if(ArrayUtils.isNotEmpty(param) && param.length!=1) {
					String key = param[0];
					String value = param[1];
					map.put(key, value);
				}
			return JSONObject.fromObject(map);
		}
		
		for(int i=0; i<params.length; i++) {
			String[] param = params[i].split("=");
			if(ArrayUtils.isEmpty(param) || param.length==1) {
				continue;
			}
			String key = param[0];
			String value = param[1];
			map.put(key, value);
		}
		return JSONObject.fromObject(map);
	}
//	public static JSONObject url2Json(String url) {
//		Map<String,Object> map = new HashMap<String, Object>();
//		String[] urls = url.split("[?]");
//		if(ArrayUtils.isEmpty(urls) || urls.length==1) {
//			map.put("url", url);
//			return JSONObject.fromObject(map);
//		}
//		map.put("url", urls[0]);
//		map.put("urls", url);
//		String[] params = urls[1].split("&");
//		if(ArrayUtils.isEmpty(params) || params.length==1) {
//			return JSONObject.fromObject(map);
//		}
//		
//		for(int i=0; i<params.length; i++) {
//			String[] param = params[i].split("=");
//			if(ArrayUtils.isEmpty(param) || param.length==1) {
//				continue;
//			}
//			String key = param[0];
//			String value = param[1];
//			map.put(key, value);
//		}
//		return JSONObject.fromObject(map);
//	}
}
