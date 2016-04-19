package com.dexter.code;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class CodingDemo {

	
	public static void main(String[] args) throws Exception{
//		String str = "ä¸、­å½èé";
//		String str = "ä¸­å½èé";
//		String strr = "中国联通";
		String strr = "Hive鏂囨。瀛樺偍璁捐";
		System.out.println(new String(new String(strr.getBytes("utf-8"),"ISO-8859-1").getBytes("ISO-8859-1"),"utf-8"));
		System.out.println(new String(strr.getBytes("ISO-8859-1"),"utf-8"));
		System.out.println("------------------------");
		System.out.println(new String(strr.getBytes("utf-8"),"ISO-8859-1"));
		System.out.println(new String(strr.getBytes(),"utf-8"));
		System.out.println(new String(strr.getBytes(),"ISO-8859-1"));
//		String str = "%257B%2522appid%2522%253A%2522kugouyinyue%2522%252C%2522os%2522%253A%2522%2522%252C%2522ip%2522%253A%2522%2522%252C%2522time%2522%253A1456978976861%252C%2522umac%2522%253A%2522%2522%252C%2522emac%2522%253A%2522%2522%252C%2522uuid%2522%253A%2522%2522%252C%2522type%2522%253A%2522h5%2522%252C%2522uuidtime%2522%253A%2522%2522%252C%2522version%2522%253A%25222.0%2522%252C%2522page%2522%253A%257B%2522url%2522%253A%2522http%253A%252F%252Fh5.luokuang.com%253A8080%252Fh5%252Fkgnotesongshujiang%252Fdesc_list.html%253Franking%253Drbxgb%2523%2522%252C%2522time%2522%253A1456978976867%252C%2522refer%2522%253A%2522http%253A%252F%252Fh5.luokuang.com%253A8080%252Fh5%252Fkgnotesongshujiang%252Frinklist.html%2522%257D%257D";
		System.out.println(URLDecoder.decode(URLDecoder.decode(strr,"utf-8"),"utf-8"));
		/*String str11 = "http://172.16.5.33:18888/kgnote/desc_list.html?ranking=ysjq#musicPath=cbea9d6702705095bddbbdc71f60de1a&musicAuthor=%E5%A4%AA%E5%AD%90%E5%A6%83%E5%8D%87%E8%81%8C%E8%AE%B0%E7%BD%91%E7%BB%9C%E5%89%A7&musicName=%E5%8F%AF%E5%BF%B5%E4%B8%8D%E5%8F%AF%E8%AF%B4";
		String str1 = "http://img.luokuang.com:8080/h5/kgnote/desc_list.html?;type=appstart&package=com.kugou.android&object_title=%25E9%2585%25B7%25E7%258B%2597%25E9%259F%25B3%25E4%25B9%2590&url=http://dapps.iluokuang.cn:8080/apps/apk/kugou.apk#musicPath=39eae0a2e6cf711aa1f3c6dde08e6cfa&musicAuthor=%E5%BC%A0%E6%9D%B0%E3%80%81%E8%8E%AB%E6%96%87%E8%94%9A&musicName=%E4%B8%80%E5%BF%B5%E4%B9%8B%E9%97%B4%E3%80%90%E7%94%B5%E5%BD%B1%E3%80%8A%E9%81%93%E5%A3%AB%E4%B8%8B%E5%B1%B1%E3%80%8B%E4%B8%BB%E9%A2%98%E6%9B%B2%E3%80%91";
		System.out.println(URLDecoder.decode(str1, "utf-8"));
		System.out.println(URLDecoder.decode(URLDecoder.decode(str1, "utf-8"), "utf-8"));
		System.out.println(URLDecoder.decode(str11, "utf-8"));
		String str2 = "%";
		int i = 0;
		try {
			System.out.println(i=1);
			System.out.println(URLDecoder.decode(str2, "utf-8"));
			System.out.println(i);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			
		}
		System.out.println(i);*/
//		System.out.println(URLEncoder.encode("http://img.luokuang.com:8080/h5%", "utf-8"));
	}
}
