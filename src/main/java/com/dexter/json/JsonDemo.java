package com.dexter.json;
import java.awt.image.BufferStrategy;

import net.sf.json.JSONArray;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Buffer;


public class JsonDemo {
	public static void main(String[] args) {
		String json = "{\"appid\":\"kugouyinyue\",\"os\":\"ANDROID\",\"ip\":\"61.145.162.51\","
				+ "\"time\":1457227852566,\"umac\":\"\",\"emac\":\"\","
				+ "\"uuid\":\"91119a5e9c0c44239a0f411b95e83b16\",\"type\":\"h5\","
				+ "\"uuidtime\":\"1457227688571\",\"version\":\"2.0\","
				+ "\"page\":{\"url\":\"http://img.luokuang.com:8080/h5/kgnote/rinklist.html?;userid=sd3dhd&onrail=0&onclien=true\","
				+ "\"time\":1457227852637,"
				+ "\"refer\":\"http://img.luokuang.com:8080/h5/kgnote/desc_list.html?"
				+ ";type=appstart&package=com.kugou.android&"
				+ "object_title=%E9%85%B7%E7%8B%97%E9%9F%B3%E4%B9%90&"
				+ "url=http://dapps.iluokuang.cn:8080/apps/apk/kugou.apk\"}}";
//		String json = "{\"appid\":\"kugouyinyue\",\"os\":\"ANDROID\",\"ip\":\"61.145.162.51\","
//				+ "\"time\":1457227852566,\"umac\":\"\",\"emac\":\"\","
//				+ "\"uuid\":\"91119a5e9c0c44239a0f411b95e83b16\",\"type\":\"h5\","
//				+ "\"uuidtime\":\"1457227688571\",\"version\":\"2.0\","
//				+ "\"page\":{\"url\":\"http://img.luokuang.com:8080/h5/kgnote/rinklist.html?;userid=sd3dhd&onrail=0&onclien=true\","
//				+ "\"time\":1457227852637,"
//				+ "\"refer\":\"\"}}";
//		JsonParser parser = new JsonParser();
//		JsonObject jsonObj = parser.parse(json).getAsJsonObject();
//		String url = jsonObj.getAsJsonObject("page").get("url").getAsString();
//		JsonObject urlObj = parser.parse(StringDemo.url2Json(url).toString()).getAsJsonObject();
//		jsonObj.getAsJsonObject("page").add("url", urlObj);
//		String refer = jsonObj.getAsJsonObject("page").get("refer").getAsString();
//		JsonObject referObj = parser.parse(StringDemo.url2Json(refer).toString()).getAsJsonObject();
//		jsonObj.getAsJsonObject("page").add("refer", referObj);
//		System.out.println(jsonObj);

		get();
	}
	
	public static void get() {
//		String bodyStr = "{\"body\":{\"duration\":[{}]}}";
		String d = "{\"appid\":\"1212\"}";
////		String bodyStr ="{\"body\":{\"duration\":[{}]}}";
		JsonParser parser = new JsonParser();
//		JsonObject jObj = parser.parse(bodyStr).getAsJsonObject();
		JsonObject djObj = parser.parse(d).getAsJsonObject();
//		JsonObject bodyJObj = jObj.getAsJsonObject("body");
//		bodyJObj.add("duration",djObj);
//		System.out.println(bodyJObj);
		StringBuffer sb = new StringBuffer("{\"body\":{\"duration\":[");
		sb.append(djObj.toString());
		sb.append("]}}");
		
		System.out.println(sb.toString());
	}
}
