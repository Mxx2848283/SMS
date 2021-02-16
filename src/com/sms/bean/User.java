package com.sms.bean;

import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class User implements HttpSessionBindingListener {
	
	private int id;
	private String nickname;
	private String username;
	private String password;
	private String email;
	private Date create_time;
	private Date last_login_time;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		ServletContext ctx = event.getSession().getServletContext();
		Integer onlineCount = (Integer) ctx.getAttribute("online_count");
		if(onlineCount == null) {
			onlineCount = 1;
		} else {
			onlineCount++;
		}
		System.out.println("用户登录，当前在线人数：" + onlineCount);
		ctx.setAttribute("online_count", onlineCount);
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		ServletContext ctx = event.getSession().getServletContext();
		Integer onlineCount = (Integer) ctx.getAttribute("online_count");
		if(onlineCount == null) {
			onlineCount = 0;
		} else {
			onlineCount--;
			if(onlineCount < 0) {
				onlineCount = 0;
			}
		}
		System.out.println("用户退出登录，当前在线人数：" + onlineCount);
		ctx.setAttribute("online_count", onlineCount);
	}
	
}
