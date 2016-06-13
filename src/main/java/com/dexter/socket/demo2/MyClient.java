package com.dexter.socket.demo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** 
 * @ClassName:	MyClient 
 * @Description:
 * 上一篇文章说到怎样写一个最简单的Java Socket通信，但是在上一篇文章中的例子
 * 有一个问题就是Server只能接受一个Client请求，当第一个Client连接后就占据了这
 * 个位置，后续Client不能再继续连接，所以需要做些改动，当Server没接受到一个
 * Client连接请求之后，都把处理流程放到一个独立的线程里去运行，然后等待下一个
 * Client连接请求，这样就不会阻塞Server端接收请求了。每个独立运行的程序在使用完
 * Socket对象之后要将其关闭 
 * @author:	dexter
 * @date:	2016年6月3日 上午10:49:19 
 *  
 */
public class MyClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 10000);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String msg = reader.readLine();
			out.println(msg);
			out.flush();
			if (msg.equals("bye")) {
				break;
			}
			System.out.println(in.readLine());
		}
		socket.close();
	}
}
