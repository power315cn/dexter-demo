package com.dexter.thread.join;

/**
 * 插件2
 * @ClassName: Plugin2
 * @author: dexter
 * @date: 2016年7月8日 上午11:47:10
 * 
 */
class Plugin2 implements Runnable {

	@Override
	public void run() {
		System.out.println("插件2开始安装.");
		System.out.println("安装中...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("插件2完成安装.");
	}
}