package com.dexter.socket.demo3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * Java Socket实战之三 传输对象
 * @ClassName:	MyServer 
 * @Description:
 * 对于Server端的代码，代码中分别使用了ObjectInputStream和ObjectOutputStream
 * 来接收和发送socket中的InputStream和OutputStream，然后转换成Java对象 
 * @author:	dexter
 * @date:	2016年6月3日 下午12:07:51 
 *  
 */
public class MyServer {

	private final static Logger logger = Logger.getLogger(MyServer.class.getName());
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10000);

		while (true) {
			Socket socket = server.accept();
			invoke(socket);
		}
	}

	private static void invoke(final Socket socket) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				ObjectInputStream is = null;
				ObjectOutputStream os = null;
				try {
					is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
					os = new ObjectOutputStream(socket.getOutputStream());

					Object obj = is.readObject();
					User user = (User)obj;
					System.out.println("user: " + user.getName() + "/" + user.getPassword());

					user.setName(user.getName() + "_new");
					user.setPassword(user.getPassword() + "_new");

					os.writeObject(user);
					os.flush();
				} catch (IOException ex) {
					logger.log(Level.SEVERE, null, ex);
				} catch(ClassNotFoundException ex) {
					logger.log(Level.SEVERE, null, ex);
				} finally {
					try {
						is.close();
					} catch(Exception ex) {}
					try {
						os.close();
					} catch(Exception ex) {}
					try {
						socket.close();
					} catch(Exception ex) {}
				}
			}
		}).start();
	}
}