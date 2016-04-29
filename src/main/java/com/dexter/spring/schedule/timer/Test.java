package com.dexter.spring.schedule.timer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) throws Exception {
		//spring-context 3.1.1.RELEASE才包含schedule
		new ClassPathXmlApplicationContext("/com/dexter/spring/schedule/timer/spring.xml");
	}
}