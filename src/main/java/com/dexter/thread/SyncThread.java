package com.dexter.thread;

/**
 * 同步线程修饰一个方法
 * 
 * @ClassName: SyncThread
 * @Description:
 * 一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞。
 * @author: dexter
 * @date: 2016年6月29日 下午2:11:19
 * 
 */
public class SyncThread implements Runnable {
	private static int count;

	public SyncThread() {
		count = 0;
	}

	@Override
	public void run() {
		synchronized (this) {
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

	public int getCount() {
		return count;
	}

	public static void main(String[] args) {
		SyncThread syncThread = new SyncThread();
		Thread thread1 = new Thread(syncThread, "SyncThread1");
		Thread thread2 = new Thread(syncThread, "SyncThread2");
		thread1.start();
		thread2.start();

		// SyncThread1:0
		// SyncThread1:1
		// SyncThread1:2
		// SyncThread1:3
		// SyncThread1:4
		// SyncThread2:5
		// SyncThread2:6
		// SyncThread2:7
		// SyncThread2:8
		// SyncThread2:9
	}
}
