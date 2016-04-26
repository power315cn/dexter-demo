package com.dexter.common.properties;

import java.io.IOException;

import com.dexter.common.properties.util.PropertiesUtil;

/**
 * Created by likun on 2015/12/26.
 * 
 * 属性文件读写是程序经常要配置的内容.从网上找一些属性文件读写的源码大多数是操作一个键值和读写所有键值的工具类封装.
本文所提供的工具类在原读写的基础上支持了自动转换为实体类对象,这样的封装带来的好处:
    1.更面向对象化, 属性文件和实体类对象无缝转化.
    2.避免了定义键名书写字符串容易书写错误的风险.
 * 
 */
public class PropertiesDemo {


    public static void main(String[] args) throws IOException {
        Student student = null;
        String fileName = "d://temp/studentConfig.properties";

        PropertiesUtil util = new PropertiesUtil(fileName);

        student = util.loadProperties(Student.class);
        if (student == null) {
            //重置对象
            util.resetProperties(Student.class);
        }
        //加载对象
        student = util.loadProperties(Student.class);
        System.out.println(student);

        student.setName("李坤 -- " + System.currentTimeMillis());
        student.setAge(30);
        student.setSex("man");
        //保存对象
        util.saveProperties(Student.class, student);
        System.out.println(util.loadProperties(Student.class));


        //清空所有属性
        //util.clearProperties();

        //测试单个属性的读取
        util.setValueByKey("name", "LiKun~李坤");
        util.getValueByKey("name");

    }
}
