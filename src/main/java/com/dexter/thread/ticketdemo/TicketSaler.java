package com.dexter.thread.ticketdemo;
/**
 * 售票程序
 */
class TicketSaler implements Runnable {
   private String name;
   private TicketServive service;

   public TicketSaler(String windowName, TicketServive service) {
      this.name = windowName;
      this.service = service;
   }

   @Override
   public void run() {
      while (service.getRemaining() > 0) {
         synchronized (this)
         {
            System.out.print(Thread.currentThread().getName() + "出售第" + service.getRemaining() + "张票，");
            int remaining = service.saleTicket(1);
            if (remaining >= 0) {
               System.out.println("出票成功!剩余" + remaining + "张票.");
            } else {
               System.out.println("出票失败！该票已售完。");
            }
         }
      }
   }
}