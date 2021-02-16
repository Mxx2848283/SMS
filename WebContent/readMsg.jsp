<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>短消息平台</title>
		<link type="text/css" rel="stylesheet" href="css/sms.css" />
		<script type="text/javascript" src="js/sms.js"></script>
	</head>
	<body>
		<div id="main">
			<div class="mainbox">
				<div class="title readMessage png"></div>
				<div class="menu">
					<span>当前在线人数:${empty online_count ? 0 : online_count }</span>
					<span>当前用户：${user.nickname }</span>
					<span><a href="javascript:makeMsg();">发短消息</a></span>
					<span><a href="javascript:logout();">退出</a></span>
				</div>
				<div class="content">
					<div class="message">
						<div class="tmenu">
							<ul class="clearfix">
								<li>主题：${message.subject }</li>
								<li>来自：${fromUser.nickname }</li>
								<li>时间：<fmt:formatDate value="${message.create_time }"/></li>
							</ul>
						</div>
						<div class="view">
							<p>
								${message.content }
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
