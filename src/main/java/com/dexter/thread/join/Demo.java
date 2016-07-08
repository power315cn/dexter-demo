package com.dexter.thread.join;

/**
 * 线程合并
 * 
 * @ClassName: Demo
 * @Description: 所谓合并，就是等待其它线程执行完，再执行当前线程， 执行起来的效果就好像把其它线程合并到当前线程执行一样
 * @author: dexter
 * @date: 2016年7月8日 上午11:47:56
 * 
 */
public class Demo {
	// public final void join()
	// 等待该线程终止
	// public final void join(long millis);
	// 等待该线程终止的时间最长为 millis 毫秒。超时为 0 意味着要一直等下去。
	// public final void join(long millis, int nanos)
	// 等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒

	public static void main(String[] args) {
		System.out.println("主线程开启...");
		Thread thread1 = new Thread(new Plugin1());
		Thread thread2 = new Thread(new Plugin2());
		try {
		   thread1.start();   //开始插件1的安装
		   thread1.join();       //等插件1的安装线程结束
		   thread2.start();   //再开始插件2的安装
		   thread2.join();       //等插件2的安装线程结束,才能回到主线程
		} catch (InterruptedException e) {
		   e.printStackTrace();
		}
		System.out.println("主线程结束，程序安装完成！");
	}
}
