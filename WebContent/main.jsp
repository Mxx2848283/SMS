<%@page import="com.sms.bean.Message"%>
<%@page import="com.sms.bean.Pagnation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<title>短消信平台</title>
	<link type="text/css" rel="stylesheet" href="css/sms.css" />
	<script type="text/javascript" src="js/sms.js"></script>
</head>

<body>
	<div id="main">
		<div class="mainbox">
			<div class="title myMessage png"></div>
			<div class="menu">
				<span>当前在线人数:${empty online_count ? 0 : online_count }</span>
				<span>当前用户:${user.nickname }</span>
				<span><a href="javascript:makeMsg();">发短消息</a></span>
				<span><a href="javascript:logout();">退出</a></span>
			</div>
			<div id="error"></div>
			<div class="content messageList">
				<ul id="content">
					<c:forEach items="${pagnation.data }" var="message">
						<li class='${message.status == 1 ? "unReaded" : "" }'>
							<em><fmt:formatDate value="${message.create_time }"/></em>
							<em><a href="javascript:replyMsg(${message.id });">回信</a></em>
							<em><a href="javascript:delMsg(${message.id });">删除</a></em>
							<p>
								<strong>
									<a href="javascript:readMsg(${message.id })">${message.subject }</a>
								</strong>
							</p>
						</li>
					</c:forEach>
				</ul>
				<div class="page">
					<span>第${pagnation.currentPage }页</span>
					<%
						Pagnation<Message> pagnation = (Pagnation<Message>)request.getAttribute("pagnation");
					%>
					<%
						if(pagnation.hasPre()) {
					%>
					<a href="javascript:page(1);">首页</a>
					<a href="javascript:page(${pagnation.currentPage-1 });">上一页</a>
					<%
						} else {
							out.println("<span>首页</span><span>上一页</span>");
						}
					%>
					<%
						if(pagnation.hasNext()) {
					%>
					<a href="javascript:page(${pagnation.currentPage+1 });">下一页</a>
					<a href="javascript:page(${pagnation.totalPage });">尾页</a>
					<%
						} else {
							out.println("<span>下一页</span><span>尾页</span>");
						}
					%>
					<span>共${pagnation.totalPage }页</span>
					<span>共${pagnation.totalCount }条</span>
					<form class="skip">
						<input id="pageNum" type="number" max="40" min="1">
						<input type="button" onclick="jumpPage();" value="跳转">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>