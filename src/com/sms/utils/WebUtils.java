package com.sms.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {

	/**
	 * ����ת��
	 * @param req
	 * @param resp
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	/**
	 * �ض���
	 * @param resp
	 * @param url
	 * @throws IOException
	 */
	public static void redirect(HttpServletResponse resp, String url) throws IOException {
		resp.sendRedirect(url);
	}
	
}
