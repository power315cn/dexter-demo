package com.dexter.socket.demo3;

/** 
 * @ClassName:	User 
 * @Description:
 * 由于需要序列化这个对象以便在网络上传输，所以实现java.io.Serializable接口就是必不可少的了 
 * @author:	dexter
 * @date:	2016年6月3日 下午12:05:59 
 *  
 */
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;

	public User() {
		
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}