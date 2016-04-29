package com.dexter.spring.schedule.timer;

/**
 * 在spring中提供了一些关于任务调度的集成功能，
 * 最简单的就是利用JDK自带的Timer和TimerTask类来实现简单任务调度。
 * 看下面的小例子：一个简单的Task类，没有实现任何接口，其中包含一个run方法用来运行这个task
 *
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
