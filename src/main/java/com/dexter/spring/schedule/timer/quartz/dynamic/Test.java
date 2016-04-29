package com.dexter.spring.schedule.timer.quartz.dynamic;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.text.ParseException;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/dexter/spring/schedule/timer/quartz/dynamic/spring.xml");
		Scheduler scheduler = (Scheduler)ctx.getBean("scheduler");

		System.out.println("Scheduling to run tasks.");
//		for (int i = 0; i < 5; i++) {
			try {
				JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("testJob_1","group_1")
		                .build();
		                Trigger trigger= TriggerBuilder
		                        .newTrigger()
		                        .withIdentity("trigger_1","group_1")
		                        .startNow()
		                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
		                                .withIntervalInSeconds(10) //时间间隔
		                                .withRepeatCount(5)        //重复次数(将执行6次)
		                                )
		                        .build();

		                SchedulerFactory sf = new StdSchedulerFactory();
		                Scheduler sched = sf.getScheduler();
		         
		                sched.scheduleJob(jobDetail,trigger);
		         
		                sched.start();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
//		}

		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Un-scheduling to run tasks.");
	}
}