package com.dexter.spring.schedule.timer.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext("/com/dexter/spring/schedule/timer/quartz/spring.xml");
	}
}