package com.sms.dao;

import java.util.List;

import com.sms.bean.User;

public interface UserDao {
	//注册  保存用户信息到数据库表
	public int saveUser(User user);
	
	/**
	 * 按照用户名查询用户
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> findAllUser();
	
	/**
	 * 查询所有用户名非username的用户
	 * @param username
	 * @return
	 */
	public List<User> findAllUserExcludeByUsername(String username);
	
	public User findUserById(int id);
}
