package com.sms.dao;

import java.util.List;

import com.sms.bean.User;

public interface UserDao {
	//ע��  �����û���Ϣ�����ݿ��
	public int saveUser(User user);
	
	/**
	 * �����û�����ѯ�û�
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);
	
	/**
	 * ��ѯ�����û�
	 * @return
	 */
	public List<User> findAllUser();
	
	/**
	 * ��ѯ�����û�����username���û�
	 * @param username
	 * @return
	 */
	public List<User> findAllUserExcludeByUsername(String username);
	
	public User findUserById(int id);
}
