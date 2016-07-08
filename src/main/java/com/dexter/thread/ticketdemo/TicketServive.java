package com.dexter.thread.ticketdemo;

public class TicketServive {
	
	private String ticketName; // 票名
	private int totalCount; // 总票数
	private int remaining; // 剩余票数

	public TicketServive(String ticketName, int totalCount) {
		this.ticketName = ticketName;
		this.totalCount = totalCount;
		this.remaining = totalCount;
	}

	public synchronized int saleTicket(int ticketNum) {
	      if (remaining > 0) {
	         remaining -= ticketNum;
	         try {        //暂停0.1秒，模拟真实系统中复杂计算所用的时间
	            Thread.sleep(100);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }

	         if (remaining >= 0) {
	            return remaining;
	         } else {
	            remaining += ticketNum;
	            return -1;
	         }
	      }
	      return -1;
	   }

	   public synchronized int getRemaining() {
	      return remaining;
	   }

	   public String getTicketName() {
	      return this.ticketName;
	   }
}
