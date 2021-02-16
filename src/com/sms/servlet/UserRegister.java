package com.sms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sms.bean.User;
import com.sms.dao.UserDao;
import com.sms.dao.impl.UserDaoImpl;
@WebServlet("/userRegister")
public class UserRegister extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp)
			throws ServletException, IOException {
		//���������������
		req.setCharacterEncoding("utf-8");//ָ���ַ�������
		//�����û���
		String username = req.getParameter("username");
		//��������
		String password = req.getParameter("password");
		//ȷ������
		String password2 = req.getParameter("password2");
		//�ǳ�
		String nickname = req.getParameter("nickname");
		//����
		String email = req.getParameter("email");
		
		System.out.println(username + "-" +password + "-" + password2);
		System.out.println(nickname + "-" + email);
		
		//�ж����������Ƿ�һ��
		if(!password.equals(password2)){//�����
			//�����û�ע��ʧ�ܣ�����û���Ϣ
			String msg = "�������벻һ�£�ע��ʧ�ܣ�";
			req.setAttribute("message", msg);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		//��װ���ݵ�user����
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		user.setUsername(username);
		user.setPassword(password);
		
		//����dao�����������û���Ϣ
		UserDao userDao = new UserDaoImpl();
		int num = userDao.saveUser(user);
		//�ж��Ƿ�ע��ɹ�
		if(num > 0){
			//��ת����¼ҳ��
			resp.sendRedirect("login.jsp");
		}else{//ע��ʧ�� ����ע��ҳ��
			//�����û�ע��ʧ�ܣ�����û���Ϣ
			String msg = "ע��ʧ�ܣ�";
			req.setAttribute("message", msg);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		
		
	}
}
