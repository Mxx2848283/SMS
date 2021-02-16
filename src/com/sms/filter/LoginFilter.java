package com.sms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sms.bean.User;

/**
 * 登录过滤器
 *
 */
@WebFilter(urlPatterns = {"/MessageServlet"})
public class LoginFilter implements Filter {	
	
	@Override
	public void doFilter(ServletRequest args0, ServletResponse args1, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoginFilter...");
		HttpServletRequest req = (HttpServletRequest) args0;
		HttpServletResponse resp = (HttpServletResponse) args1;
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null) {
			// Session失效或者还没有登录
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().println("<script>alert('会话已过期，请重新登录！');location.href='login.jsp';</script>");
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
