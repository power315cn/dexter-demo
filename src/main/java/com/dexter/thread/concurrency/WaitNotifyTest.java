package com.dexter.thread.concurrency;

/** 
 * Java多线程中wait, notify and notifyAll的使用
 * @ClassName:	WaitNotifyTest 
 * @Description:
 * 一个测试类，交付创建多个等待线程和一个通过线程，并启动这些线程。
 * @author:	dexter
 * @date:	2016年7月8日 下午4:22:51 
 *  
 */
public class WaitNotifyTest {
	//1.当我们调用以上的代码时可以看到以下的输出，但并没有结束(完成)，
	//因为有两个线程等待同一个Message对象，但notify()方法只能唤醒一个线程，
	//另一个线程仍然在等待被唤醒。
	
	//2.如果我们注释掉Notifier类中的notify() 方法的调用，并打开notifyAll() 方法的调用，
	//一旦notifyAll()方法唤醒所有的Waiter线程，程序将会执行完成并退出。
    public static void main(String[] args) {
        Message msg = new Message("process it");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter,"waiter").start();

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();
        
        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("All the threads are started");
    }

}