package com.ufgov.zc.client.ftp.apache.utils;

public class FtpConfig {

	//服务器地址名称
	private String server;
	//端口号
	private int port;
	//用户名称
	private String username;
	//密码
	private String password;
	//工作目录
	private String root;
   
	/**
	 * 
	 * @param server
	 * @param port
	 * @param user
	 * @param password
	 * @param targetDir 允许访问的目标目录，如/test/abc/
	 */
	public FtpConfig(String server,int port,String user,String password,String targetDir){
		this.server=server;
		this.port=port;
		this.username=user;
		this.password=password;
		this.root=targetDir;
	}
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

}
