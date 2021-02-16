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
		//如果遇到中文乱码
		req.setCharacterEncoding("utf-8");//指定字符集编码
		//接收用户名
		String username = req.getParameter("username");
		//接收密码
		String password = req.getParameter("password");
		//确认密码
		String password2 = req.getParameter("password2");
		//昵称
		String nickname = req.getParameter("nickname");
		//邮箱
		String email = req.getParameter("email");
		
		System.out.println(username + "-" +password + "-" + password2);
		System.out.println(nickname + "-" + email);
		
		//判断两次密码是否一致
		if(!password.equals(password2)){//不相等
			//提醒用户注册失败，检查用户信息
			String msg = "两次密码不一致，注册失败！";
			req.setAttribute("message", msg);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		//封装数据到user对象
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		user.setUsername(username);
		user.setPassword(password);
		
		//调用dao方法，保存用户信息
		UserDao userDao = new UserDaoImpl();
		int num = userDao.saveUser(user);
		//判断是否注册成功
		if(num > 0){
			//跳转到登录页面
			resp.sendRedirect("login.jsp");
		}else{//注册失败 返回注册页面
			//提醒用户注册失败，检查用户信息
			String msg = "注册失败！";
			req.setAttribute("message", msg);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		
		
	}
}
