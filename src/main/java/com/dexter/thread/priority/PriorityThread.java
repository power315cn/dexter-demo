package com.dexter.thread.priority;

/**
 * 优先级
 * 
 * @ClassName: PriorityThread
 * @Description:
 * 线程优先级是指获得CPU资源的优先程序。优先级高的容易获得CPU资源，
 * 优先级底的较难获得CPU资源，表现出来的情况就是优先级越高执行的时间越多。
 * @author: dexter
 * @date: 2016年7月8日 上午11:53:17
 * 
 */
class PriorityThread implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
	}

	/** 
	 * Java中通过getPriority和setPriority方法获取和设置线程的优先级。
	 * Thread类提供了三个表示优先级的常量：MIN_PRIORITY优先级最低，为1；
	 * NORM_PRIORITY是正常的优先级；为5，MAX_PRIORITY优先级最高，为10。
	 * 我们创建线程对象后，如果不显示的设置优先级的话，默认为5。
	 */
	public static void main(String[] args) {
		//创建三个线程
		Thread thread1 = new Thread(new PriorityThread(), "Thread1");
		Thread thread2 = new Thread(new PriorityThread(), "Thread2");
		Thread thread3 = new Thread(new PriorityThread(), "Thread3");
		//设置优先级
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(8);
		//开始执行线程
		thread3.start();
		thread2.start();
		thread1.start();
		//从结果中我们可以看到线程thread1明显比线程thread3执行的快。
	}
}