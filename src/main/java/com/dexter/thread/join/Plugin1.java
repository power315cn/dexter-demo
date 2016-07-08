package com.dexter.thread.join;

/** 
 * 插件1
 * @ClassName:	Plugin1 
 * @author:	dexter
 * @date:	2016年7月8日 上午11:46:55 
 */
public class Plugin1 implements Runnable {

	@Override
	public void run() {
		System.out.println("插件1开始安装.");
		System.out.println("安装中...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("插件1完成安装.");
	}

}
