package com.sms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sms.bean.Message;
import com.sms.bean.Pagnation;
import com.sms.bean.User;
import com.sms.dao.MessageDao;
import com.sms.dao.UserDao;
import com.sms.dao.impl.MessageDaoImpl;
import com.sms.dao.impl.UserDaoImpl;
import com.sms.utils.StringUtils;
import com.sms.utils.WebUtils;
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4163109018581361671L;

	private MessageDao messageDao = new MessageDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(StringUtils.isBlank(action)) {
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().println("<script>alert('action请求参数缺失！');</script>");
			return;
		}
		switch (action) {
		case "to_list":
			showMessageList(req, resp);
			break;
		case "reply":
			replyMsg(req, resp);
			break;
		case "del":
			delMsg(req, resp);
			break;
		case "make_msg":
			makeMsg(req, resp);
			break;
		case "read_msg":
			readMsg(req, resp);
			break;
		case "send":
			sendMsg(req, resp);
			break;
		default:
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().println("<script>alert('不支持的action请求类型："+action+"');</script>");
			return;
		}
	}
	
	private void sendMsg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int toId = Integer.parseInt(req.getParameter("to_id"));
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("user");
		Message message = new Message();
		message.setFrom_id(currentUser.getId());
		message.setTo_id(toId);
		message.setSubject(subject);
		message.setContent(content);
		message.setStatus(1);	//未读
		messageDao.save(message);
		
		WebUtils.redirect(resp, "MessageServlet?action=to_list");
	}

	private void readMsg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Message message = messageDao.findMessageById(id);
		req.setAttribute("message", message);
		User fromUser = userDao.findUserById(message.getFrom_id());
		req.setAttribute("fromUser", fromUser);
		
		// 将消息标记为已读状态
		message.setStatus(2);
		messageDao.updateMessageStatus(message);
		
		WebUtils.forward(req, resp, "readMsg.jsp");
	}

	/**
	 * 写消息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void makeMsg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("user");
		List<User> receiveUserList = userDao.findAllUserExcludeByUsername(currentUser.getUsername());
		req.setAttribute("receiveUsers", receiveUserList);
		WebUtils.forward(req, resp, "newMsg.jsp");
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void delMsg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		messageDao.deleteMsgById(id);
		WebUtils.redirect(resp, "MessageServlet?action=to_list");
	}

	private void replyMsg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Message message = messageDao.findMessageById(id);
		int fromId = message.getFrom_id();
		User receiveUser = userDao.findUserById(fromId);
		req.setAttribute("receiveUser", receiveUser);
		WebUtils.forward(req, resp, "replyMsg.jsp");
	}

	private void showMessageList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (NumberFormatException e) {
		}
		int pageSize = 5;
		try {
			pageSize = Integer.parseInt(req.getParameter("pageSize"));
		} catch (NumberFormatException e) {
		}
		
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("user");
		int toId = currentUser.getId();
		Pagnation<Message> pagnation = new Pagnation<>();
		pagnation.setCurrentPage(currentPage);
		pagnation.setPageSize(pageSize);
		
		messageDao.findMessageByReceiveId(pagnation, toId);
		req.setAttribute("pagnation", pagnation);
		WebUtils.forward(req, resp, "main.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
}
