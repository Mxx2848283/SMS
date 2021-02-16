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
			req.setAttribute("error", "用户名或密码不能为空！");
			WebUtils.forward(req, resp, "login.jsp");
			return;
		}
		
		User user = userDao.findUserByUsername(username);
		if(user == null) {
			req.setAttribute("error", "账号不存在！");
			WebUtils.forward(req, resp, "login.jsp");
			return;
		}
		
		if(!user.getPassword().equals(password)) {
			req.setAttribute("error", "用户名或密码错误！");
			WebUtils.forward(req, resp, "login.jsp");
			return;
		}
		
		System.out.println("登录成功!");
		
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		
		// 如果选择了记住密码，将用户信息转码后写入Cookie中
		if(isRememberMe) {
			String userNameEncode = URLEncoder.encode(username, "UTF-8");
			String passwordEncode = URLEncoder.encode(password, "UTF-8");
			Cookie usernameCookie = new Cookie("username", userNameEncode);
			Cookie passwordCookie = new Cookie("password", passwordEncode);
			resp.addCookie(usernameCookie);
			resp.addCookie(passwordCookie);
		} else {
			// 如果没有勾选记住密码，将Cookie清除掉
			Cookie usernameCookie = new Cookie("username", null);
			Cookie passwordCookie = new Cookie("password", null);
			resp.addCookie(usernameCookie);
			resp.addCookie(passwordCookie);
		}
		WebUtils.redirect(resp, "MessageServlet?action=to_list");
	}

}
