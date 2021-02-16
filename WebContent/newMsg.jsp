<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form action="MessageServlet?action=send" method="post">
		<div id="main">
			<div class="mainbox">
				<div class="menu">
					<span>当前在线人数:${empty online_count ? 0 : online_count }</span>
					<span>当前用户:${user.nickname }</span>
					<span><a href="javascript:logout();">退出</a></span>
				</div>
				<div class="content">
					<div class="message">
						<div class="tmenu">
							<ul class="clearfix">
								<li>
									发送给：
									<select name="to_id">
										<c:forEach items="${receiveUsers }" var="user">
											<option value="${user.id }">${user.nickname }</option>
										</c:forEach>
									</select>
								</li>
								<li>
									<span style="vertical-align:-3px;">标题：</span> <input type="text" name="subject" />
								</li>
							</ul>
						</div>
						<div class="view">
							<textarea name="content"></textarea>
							<div class="send">
								<input type="submit" value=" " />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>