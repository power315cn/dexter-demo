package com.dexter.socket.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * @ClassName:	MyServer 
 * @Description:TODO
 * 用来监听10000端口，并从这个端口接收消息然后输出，当收到“bye”时退出
 * 首先运行MyServer类，然后MyClient类，然后在MyClient的控制台输入任意字符，可以看到当输入bye是server和client都会退出。
 * @author:	dexter
 * @date:	2016年6月3日 上午10:30:48 
 *  
 */
public class MyServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10000);
		Socket socket = server.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		
		while (true) {
			String msg = in.readLine();
			System.out.println(msg);
			out.println("Server received " + msg);
			out.flush();
			if (msg.equals("bye")) {
				break;
			}
		}
		socket.close();
	}
}