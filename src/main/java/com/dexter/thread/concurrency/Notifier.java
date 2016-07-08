package com.dexter.thread.concurrency;
/** 
 * 
 * @ClassName:	Notifier 
 * @Description:
 * 一个Notifier类，处理Message对象并调用notify方法唤醒等待Message对象的线程。
 * 注意synchronized代码块被用于持有Message对象的监视器
 * @author:	dexter
 * @date:	2016年7月8日 下午4:22:29 
 *  
 */
public class Notifier implements Runnable {

    private Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+" started");
        try {
            Thread.sleep(1000);
            synchronized (msg) {
                msg.setMsg(name+" Notifier work done");
                msg.notify();
                // msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}