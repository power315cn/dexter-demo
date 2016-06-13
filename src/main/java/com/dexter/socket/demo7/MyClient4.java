package com.dexter.socket.demo7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * @ClassName:	MyClient4 
 * @Description:
 * 也主要关注sendFile和receiveFile方法 
 * @author:	dexter
 * @date:	2016年6月3日 下午3:43:32 
 *  
 */
public class MyClient4 {

	private final static Logger logger = Logger.getLogger(MyClient4.class.getName());
	
	public static void main(String[] args) throws Exception {
		new Thread(new MyRunnable()).start();
	}
	
	private static final class MyRunnable implements Runnable {
		public void run() {
			SocketChannel socketChannel = null;
			try {
				socketChannel = SocketChannel.open();
				SocketAddress socketAddress = new InetSocketAddress("localhost", 10000);
				socketChannel.connect(socketAddress);

				sendFile(socketChannel, new File("D:/test/client_send.log"));
				receiveFile(socketChannel, new File("D:/test/client_receive.log"));
			} catch (Exception ex) {
				logger.log(Level.SEVERE, null, ex);
			} finally {
				try {
					socketChannel.close();
				} catch(Exception ex) {}
			}
		}

		private void sendFile(SocketChannel socketChannel, File file) throws IOException {
			FileInputStream fis = null;
			FileChannel channel = null;
			try {
				fis = new FileInputStream(file);
				channel = fis.getChannel();
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
				int size = 0;
				while ((size = channel.read(buffer)) != -1) {
					buffer.rewind();
					buffer.limit(size);
					socketChannel.write(buffer);
					buffer.clear();
				}
				socketChannel.socket().shutdownOutput();
			} finally {
				try {
					channel.close();
				} catch(Exception ex) {}
				try {
					fis.close();
				} catch(Exception ex) {}
			}
		}

		private void receiveFile(SocketChannel socketChannel, File file) throws IOException {
			FileOutputStream fos = null;
			FileChannel channel = null;
			
			try {
				fos = new FileOutputStream(file);
				channel = fos.getChannel();
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

				int size = 0;
				while ((size = socketChannel.read(buffer)) != -1) {
					buffer.flip();
					if (size > 0) {
						buffer.limit(size);
						channel.write(buffer);
						buffer.clear();
					}
				}
			} finally {
				try {
					channel.close();
				} catch(Exception ex) {}
				try {
					fos.close();
				} catch(Exception ex) {}
			}
		}
	}
}
