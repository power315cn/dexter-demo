package com.dexter.database.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.dexter.database.mybatis.dao.UserDao;
import com.dexter.database.mybatis.domain.User;
import com.google.gson.Gson;

public class MybatisDemo {
	//mybatis的配置文件
	private final static String resource = "mybatis.xml";
	private static SqlSessionFactory sessionFactory;
	static {
		//使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisDemo.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
//        Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
//        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        
	}
	
	public static void main(String[] args) {
		
	}

	@Test
	public void getUser() {
		SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user =  userDao.selectById(1);
        Gson json = new Gson();
        System.out.println(json.toJson(user));
        //{"id":2,"name":"夜夜","age":18,"sex":1,"createTime":"Apr 23, 2016 8:09:05 AM"}
        session.close();
	}
	
	@Test
	public void insert() {
		SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User u = new User();
        u.setName("萧南天");
        u.setSex(1);
        u.setAge(26);
        int i = userDao.insert(u);
        session.commit();
        if(i == 1) {
        	Gson json = new Gson();
        	User user =  userDao.selectById(u.getId());
        	System.out.println(json.toJson(user));
        } 
        session.close();
	}
	
	@Test
	public void update() {
		SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        Integer id = 13;
        Gson json = new Gson();
        User userBefor =  userDao.selectById(id);
    	System.out.println("修改前：" + json.toJson(userBefor));
    	userBefor.setName("牙牙");
        int i = userDao.updateByIdSelective(userBefor);
        session.commit();
        if(i == 1) {
        	User userAfter =  userDao.selectById(id);
        	System.out.println("修改后：" + json.toJson(userAfter));
        } 
        session.close();
	}
	
	@Test
	public void delete() {
		SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        Integer id = 13;
        Gson json = new Gson();
        User userBefor =  userDao.selectById(id);
    	System.out.println("删除前：" + json.toJson(userBefor));
        int i = userDao.deleteById(id);
        session.commit();
        if(i == 1) {
        	User userAfter =  userDao.selectById(id);
        	System.out.println("删除后：" + json.toJson(userAfter));
        } 
        session.close();
	}
	
}
