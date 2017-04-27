package com.java115.entity;

public class Userinfo {
	private String username;
	private String password;
	private int userid;
	private String realname;
	
	public Userinfo() {
		super();
	}

	public Userinfo(String username, String password, int userid,
			String realname) {
		super();
		this.username = username;
		this.password = password;
		this.userid = userid;
		this.realname = realname;
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Override
	public String toString() {
		return "Userinfo [username=" + username + ", password=" + password
				+ ", userid=" + userid + ", realname=" + realname + "]";
	}

	
	
	
	
	
}
