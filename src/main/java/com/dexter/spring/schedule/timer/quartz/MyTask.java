package com.dexter.spring.schedule.timer.quartz;

/**
 *在spring中对任务调度的集成除了使用JDK自带的Timer和TimerTask类来实现简单任务调度以外，
 *也可以使用企业级的开源作业调度框架Quartz来实现，下面是一个小例子：
 *1. 首先是一个任务类，这个类没有实现任何接口，其中包含一个run方法用来运行这个task，代码如下：
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
