package com.dexter.thread;

/**
 * @ClassName: Counter
 * @Description: 当一个线程访问对象的一个synchronized(this)同步代码块时，
 *               另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块
 * @author: dexter
 * @date: 2016年6月29日 下午2:35:16
 * 
 */
public class Counter implements Runnable{

	private static int count;

	public Counter() {
		count = 0;
	}

	public void countAdd() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
	public void printCount() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + "count:" + count);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		if("A".equals(name)) {
			countAdd();
		} else if("B".equals(name)) {
			printCount();
		}
	}
	
//	上面代码中countAdd是一个synchronized的，printCount是非synchronized的。
//	从上面的结果中可以看出一个线程访问一个对象的synchronized代码块时，
//	别的线程可以访问该对象的非synchronized代码块而不受阻塞。
	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread thread1 = new Thread(counter, "A");
		Thread thread2 = new Thread(counter, "B");
		thread1.start();
		thread2.start();
//		A:0 
//		B count:1 
//		A:1 
//		B count:2 
//		A:2 
//		B count:3 
//		A:3 
//		B count:4 
//		A:4 
//		B count:5
	}
}
