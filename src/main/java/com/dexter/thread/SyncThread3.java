package com.dexter.thread;

/**
 * 修饰一个类
 * @ClassName: SyncThread3
 * @Description:其效果和SyncThread2是一样的，synchronized作用于一个类T时，
 * 是给这个类T加锁，T的所有对象用的是同一把锁。
 * @author: dexter
 * @date: 2016年6月29日 下午6:06:45
 * 
 */
public class SyncThread3 implements Runnable {

	private static int count;

	public SyncThread3() {
		count = 0;
	}

	public static void method() {
		synchronized (SyncThread.class) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":"
							+ (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public synchronized void run() {
		method();
	}
	
	public static void main(String[] args) {
		SyncThread3 syncThread1 = new SyncThread3();
		SyncThread3 syncThread2 = new SyncThread3();
		Thread thread1 = new Thread(syncThread1, "SyncThread1");
		Thread thread2 = new Thread(syncThread2, "SyncThread2");
		thread1.start();
		thread2.start();
	}

}
