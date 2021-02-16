package com.sms.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sms.bean.User;
import com.sms.dao.UserDao;
import com.sms.dao.impl.UserDaoImpl;
import com.sms.utils.StringUtils;
import com.sms.utils.WebUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean isRememberMe = false;
		try {
			isRememberMe = Boolean.parseBoolean(req.getParameter("rememberMe"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		System.out.println("isRememberMe: " + isRememberMe);
		
		resp.setContentType("text/html;charset=UTF-8");
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			req.setAttribute("error", "�û��������벻��Ϊ�գ�");
			WebUtils.forward(req, resp, "login.jsp");
			return;
		}
		
		User user = userDao.findUserByUsername(username);
		if(user == null) {
			req.setAttribute("error", "�˺Ų����ڣ�");
			WebUtils.forward(req, resp, "login.jsp");
			return;
		}
		
		if(!user.getPassword().equals(password)) {
			req.setAttribute("error", "�û������������");
			WebUtils.forward(req, resp, "login.jsp");
			return;
		}
		
		System.out.println("��¼�ɹ�!");
		
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		
		// ���ѡ���˼�ס���룬���û���Ϣת���д��Cookie��
		if(isRememberMe) {
			String userNameEncode = URLEncoder.encode(username, "UTF-8");
			String passwordEncode = URLEncoder.encode(password, "UTF-8");
			Cookie usernameCookie = new Cookie("username", userNameEncode);
			Cookie passwordCookie = new Cookie("password", passwordEncode);
			resp.addCookie(usernameCookie);
			resp.addCookie(passwordCookie);
		} else {
			// ���û�й�ѡ��ס���룬��Cookie�����
			Cookie usernameCookie = new Cookie("username", null);
			Cookie passwordCookie = new Cookie("password", null);
			resp.addCookie(usernameCookie);
			resp.addCookie(passwordCookie);
		}
		WebUtils.redirect(resp, "MessageServlet?action=to_list");
	}

}
