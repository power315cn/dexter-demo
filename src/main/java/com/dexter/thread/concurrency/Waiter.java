package com.dexter.thread.concurrency;

/** 
 * @ClassName:	Waiter 
 * @Description:
 * 一个Waiter类，等待其它的线程调用notify方法以唤醒线程完成处理。
 * 注意等待线程必须通过加synchronized同步锁拥有Message对象的监视器。
 * @author:	dexter
 * @date:	2016年7月8日 下午4:19:42 
 *  
 */
public class Waiter implements Runnable{

    private Message msg;

    public Waiter(Message m){
        this.msg=m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try{
                System.out.println(name+" waiting to get notified at time:"+System.currentTimeMillis());
                msg.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name+" waiter thread got notified at time:"+System.currentTimeMillis());
            //process the message now
            System.out.println(name+" processed: "+msg.getMsg());
        }
    }

}