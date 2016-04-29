package com.dexter.spring.schedule.timer.quartz.cron;

/**
 * 在Quartz中除了使用最简单的Simple Trigger以外，
 * 也可以使用类似Linux上Cron作业的CronTrigger的方式来运行Job，下面是一个小例子：
 * 1. 首先是一个任务类，这个类没有实现任何接口，其中包含一个run方法用来运行这个task，代码如下：
 */
public class MyTask {
	private String name;
	
	public void run() {
		System.out.println("Run task: " + name + ".");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}