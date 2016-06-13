package com.dexter.socket.demo1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** 
 * @ClassName:	MyClient 
 * @Description:
 * 接收任何用户输入，当遇到回车时发送字符串到Server上，当输入“bye”是退出。 
 * @author:	dexter
 * @date:	2016年6月3日 上午10:43:52 
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