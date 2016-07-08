package com.dexter.thread.ticketdemo;

/**
 * 测试类
 */
public class TicketingSystem {
	public static void main(String args[]) {
		TicketServive service = new TicketServive("北京-->赣州", 50);
		TicketSaler ticketSaler = new TicketSaler("售票程序", service);
		// 创建8个线程，以模拟8个窗口
		Thread threads[] = new Thread[8];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(ticketSaler, "窗口" + (i + 1));
			System.out.println("窗口" + (i + 1) + "开始出售 "
					+ service.getTicketName() + " 的票...");
			threads[i].start();
		}

	}
}