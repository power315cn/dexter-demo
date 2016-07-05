package com.dexter.thread;

/**
 * 修饰一个静态的方法
 * @ClassName: SyncThread2
 * @Description:我们知道静态方法是属于类的而不属于对象的。同样的， 
 * synchronized修饰的静态方法锁定的是这个类的所有对象。我们对Demo1进行一些修改如下：
 * @author: dexter
 * @date: 2016年6月29日 下午6:06:45
 * 
 */
public class SyncThread2 implements Runnable {

	private static int count;

	public SyncThread2() {
		count = 0;
	}

	public synchronized static void method() {
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

	@Override
	public synchronized void run() {
		method();
	}
	
//	syncThread1和syncThread2是SyncThread的两个对象，但在thread1和thread2并发
//	执行时却保持了线程同步。这是因为run中调用了静态方法method，而静态方法是属
//	于类的，所以syncThread1和syncThread2相当于用了同一把锁。这与Demo1是不同的。
	public static void main(String[] args) {
		SyncThread2 syncThread1 = new SyncThread2();
		SyncThread2 syncThread2 = new SyncThread2();
		Thread thread1 = new Thread(syncThread1, "SyncThread1");
		Thread thread2 = new Thread(syncThread2, "SyncThread2");
		thread1.start();
		thread2.start();
	}

}
