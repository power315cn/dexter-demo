package com.dexter.io;



import java.io.*;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.dexter.common.GsonUtil;

/**
 * Created by Duke on 2016/3/3.
 */
public class H5ParserService {

	private static final String SOURCE_PATH = "D:"+ File.separator +"h5"+File.separator +"h5_20160310" + File.separator;
	private static final String TARGET_PATH = "D:"+ File.separator +"h5"+File.separator +"h5_20160310_OK" + File.separator;
    private static String serverDate;//yyyy-MM-dd
    private static String clearDate;//yyyyMMdd
    private static String serverTime;//HH:mm:ss

    private static String indexName = "h5";

    public H5ParserService() {
    }

    public static void main(String[] args) throws InterruptedException, IOException {
//        if (statDateTime == null) {
//            log.error("日期不能为空，请检查参数！");
//            return;
//        }
//        log.info("----------------h5日志数据清洗开始.----------------");
        H5ParserService sdps = new H5ParserService();
//        sdps.runPreprocess();
        out2File(null, "");
//        log.info("----------------h5日志数据清洗开始.----------------");
    }

    /**
     * 执行预处理
     */
    public void runPreprocess() {
    	
        File logDirectory = new File(SOURCE_PATH);
//elasticsearch_log.log.2016-03-03.log

        // 判断根目录是否存在
        if (!logDirectory.exists()) {
            System.out.println("{}目录不存在，程序退出." + logDirectory.getPath());
            return;
        }
        // 判断根目录下是否有文件
        File[] logZipFiles = logDirectory.listFiles();
        if (ArrayUtils.isEmpty(logZipFiles)) {
            System.out.println(logDirectory.getPath() + "目录中没有日志文件，程序退出.");
        }

        for (int i = 0; i < logZipFiles.length; i++) {
           File file = logZipFiles[i];
           File[] logs =  file.listFiles();
           for(int j = 0; j < logs.length; j++) {
        	   File log = logs[i];
        	   try {
        		   String fileName = log.getName();
        		   if(fileName.indexOf("elasticsearch_log") > -1) {
//        			   String wifiDeviceMac = fileName.substring(0, fileName.indexOf("_"));
        			   String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        			   if (StringUtils.equalsIgnoreCase(fileType, "log") ) {
        				   System.out.println("解析第"+i + 1+"个日志zip包，文件名称："+fileName);
        				   processor(log);
        				   //调整为300毫秒，时间拉的太长会影响hdfs中的server_date字段跨三天（正常只跨两天）
        				   Thread.sleep(300);
        			   }
        		   }
        	   } catch (Exception e) {
        		   System.out.println("解析第"+i + 1+"个日志zip包出现异常，文件路径："+file.getPath());
        	   }
           }
        }
    }

    public void processor(File file) {
    	FileReader fr = null;
    	BufferedReader br = null;
        try {
        	fr = new FileReader(file);
			br = new BufferedReader(fr); // 将流整体读取。
			String str;
			while ((str = br.readLine()) != null) {// 判断是否是最后一行
				System.out.println(str);// 输出每一行内容。
				String json = str.substring(str.indexOf("{\"appid\":"),str.length());
				System.out.println(json);
				 Map<String,Object> map = GsonUtil.parseDataToMap(json);
		            if (map != null) {
//		                map.put("envir", "2");
//		                map.put("serverdate", clearDate);
//		                map.put("servertime", serverTime);
		                Object obj = map.get("page");
		                if (obj != null && obj instanceof Map) {
		                    Map<String,Object> pageMap = (Map<String,Object>) obj;
		                    String url = (String) pageMap.get("url");
		                    if (StringUtils.isNotEmpty(url)) {
		                        if (url.indexOf("?") > 0) {
		                            Map<String,Object> urlparams = url2Json(url);
		                            url = url.substring(0, url.indexOf("?"));
		                            pageMap.put("urlparams", urlparams);
		                        }
		                        pageMap.put("url", url);
		                    }

		                    String refer = (String) pageMap.get("refer");
		                    if (StringUtils.isNotEmpty(refer)) {
		                        if (refer.indexOf("?") > 0) {
		                            Map<String,Object> referparams = url2Json(refer);
		                            refer = refer.substring(0, refer.indexOf("?"));
		                            pageMap.put("referparams", referparams);
		                        }
		                        pageMap.put("refer", refer);
		                    }
		                }
		                json = GsonUtil.toJson(map);
		           }
		            out2File(file, json);
			}
        } catch (Exception e) {
        	System.out.println("日志解析异常：路径为：" +e);
        } finally {
        	try {
        		if(br !=null) {
        			br.close();
        		}
				if(fr !=null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }


    public static void out2File(File file, String str) {
//    	FileInputStream fis=new FileInputStream("文件路径");
    	File tar_file = new File(TARGET_PATH+"111.log");
    	if(!tar_file.exists()) {
    		try {
    			tar_file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	FileOutputStream fos = null;
    	OutputStreamWriter osw = null;
    	
		try {
			fos = new FileOutputStream(tar_file, true);
			osw= new OutputStreamWriter(fos,"UTF-8");
			osw.write("1111");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
				if (fos != null) {
					fos.close();
				}
				if (osw != null) {
					osw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

    /**
     * Log目录：主目录+ /yyyyMM/+/dd/+/HH
     *
     * @param rootPath 主目录
     * @param date     日期
     * @return 日志存放全路径
     */
//    protected static String getLogFullPath(String rootPath, Date date) {
//        String datePart = DateFormatUtils.format(date, "yyyyMM");
//        String dayPart = DateFormatUtils.format(date, "dd");
//        String hourPart = DateFormatUtils.format(date, "HH");
//        StringBuffer fullPath = new StringBuffer();
//        fullPath.append(LOG_ROOT_PATH).append("/").append(datePart).append("/").append(dayPart).append("/")
//                .append(hourPart);
//        return fullPath.toString();
//    }

//    protected static String getFullSavePath(String saveRootPath, Date date) {
//        String datePart = DateFormatUtils.format(date, "yyyyMM");
//        String dayPart = DateFormatUtils.format(date, "dd");
//        StringBuffer fullSavePath = new StringBuffer();
//        fullSavePath.append(saveRootPath + "/" + datePart + "/" + dayPart + "/");
//        return fullSavePath.toString();
//    }
//
//    /**
//     * 获取日期，这个日期有可能是shell中传递的参数，也有可能是程序的默认日期。
//     *
//     * @throws Exception
//     */
//    protected static Date getDate(String[] args) {
//        if (ArrayUtils.isEmpty(args)) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(new Date());
//            c.add(Calendar.HOUR, -1);
//            return c.getTime();
//        }
//        Date dateNow = null;
//        String[] format = new String[]{"yyyy-MM-dd_HH", "yyyy-MM-dd_HH:mm:ss"};
//        try {
//            dateNow = DateUtils.parseDate(args[0], format);
//        } catch (ParseException e) {
//            log.error("参数：" + args[0] + "解析异常", e);
//        }
//        return dateNow;
//    }


    /**
     * 转换URL参数为Map
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Map<String,Object> url2Json(String url) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(url) || url.indexOf("?") == -1) {
            return null;
        }
        url = paramValueDecoder(url);

        Map<String, Object> map = new HashMap<String, Object>();
        String[] urls = url.split("[?]");
        map.put("fullurl", url);
        if (ArrayUtils.isEmpty(urls) || urls.length == 1) {
            return map;
        }
        String[] params = urls[1].split("&");
        if (ArrayUtils.isEmpty(params) || params.length == 1) {
            String[] param = params[0].split("=");
            if (ArrayUtils.isNotEmpty(param) && param.length != 1) {
                String key = param[0];
                String value = param[1];
                map.put(key, value);
            }
            return map;
        }

        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (ArrayUtils.isEmpty(param) || param.length == 1) {
                continue;
            }
            String key = param[0];
            String value = param[1];
            map.put(key, value);
        }
        return map;
    }

    /**
     * 反编码参数，因为客户端的问题所以需要转两次
     *
     * @param paramValue
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String paramValueDecoder(String paramValue) throws UnsupportedEncodingException {
        try {
            paramValue = URLDecoder.decode(paramValue, "UTF-8");
            paramValue = URLDecoder.decode(paramValue, "UTF-8");
        } catch (Exception e) {
            return paramValue;
        }
        return paramValue;
    }
}
