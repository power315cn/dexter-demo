package com.dexter.socket.demo4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/** 
 * Java Socket实战之四 传输压缩对象
 * @ClassName:	MyServer 
 * @Description:TODO
 * 上一篇文章说到了用Java Socket来传输对象，
 * 但是在有些情况下比如网络环境不好或者对象比较大的情况下需要把数据
 * 对象进行压缩然后在传输，此时就需要压缩这些对象流，此时就可以
 * GZIPInputStream和GZIPOutputStream来处理一下socket的InputStream和OutputStream
 * @author:	dexter
 * @date:	2016年6月3日 下午2:29:04 
 *  
 */
public class MyServer {

	private final static Logger logger = Logger.getLogger(MyServer.class.getName());
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10000);

		while (true) {
			Socket socket = server.accept();
			socket.setSoTimeout(10 * 1000);
			invoke(socket);
		}
	}

	private static void invoke(final Socket socket) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				GZIPInputStream gzipis = null;
				ObjectInputStream ois = null;
				GZIPOutputStream gzipos = null;
				ObjectOutputStream oos = null;
				
				try {
					gzipis = new GZIPInputStream(socket.getInputStream());
					ois = new ObjectInputStream(gzipis);
					gzipos = new GZIPOutputStream(socket.getOutputStream());
					oos = new ObjectOutputStream(gzipos);

					Object obj = ois.readObject();
					User user = (User)obj;
					System.out.println("user: " + user.getName() + "/" + user.getPassword());

					user.setName(user.getName() + "_new");
					user.setPassword(user.getPassword() + "_new");

					oos.writeObject(user);
					oos.flush();
					gzipos.finish();
				} catch (IOException ex) {
					logger.log(Level.SEVERE, null, ex);
				} catch(ClassNotFoundException ex) {
					logger.log(Level.SEVERE, null, ex);
				} finally {
					try {
						ois.close();
					} catch(Exception ex) {}
					try {
						oos.close();
					} catch(Exception ex) {}
					try {
						socket.close();
					} catch(Exception ex) {}
				}
			}
		}).start();
	}
}