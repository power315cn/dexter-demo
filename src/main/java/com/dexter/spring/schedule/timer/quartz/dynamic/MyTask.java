package com.dexter.spring.schedule.timer.quartz.dynamic;

/**
 * 但那些例子都是对静态作业做调度的例子，
 * 这里所谓静态作业都是指作业信息和调度信息是写死在spring的配置文件中的，
 * 但是真实很多应用的情况都是需要动态的对作业进行调度，比如动态添加或者删除作业，
 * 动态的设置作业的trigger等。下面就来看看在spring中怎样实现对动态作业进行调度。
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