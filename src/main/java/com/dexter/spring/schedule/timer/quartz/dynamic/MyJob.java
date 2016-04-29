package com.dexter.spring.schedule.timer.quartz.dynamic;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 2. 一个Job类，这个类需要继承spring的QuartzJobBean类，
 * 来说明当前类是一个Quartz的Job类，类包含了一个Task类的对象实例，
 * 在每次Job被调度的时候，将会运行其中的executeInternal方法，代码如下：
 */
public class MyJob extends QuartzJobBean {
	
	private MyTask myTask;

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		myTask.run();
	}

	public MyTask getMyTask() {
		return myTask;
	}

	public void setMyTask(MyTask myTask) {
		this.myTask = myTask;
	}
}