<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>短消息平台</title>
		<link type="text/css" rel="stylesheet" href="css/sms.css" />
	</head>

	<body>
		<div id="regTitle" class="png"></div>
		<div id="regForm" class="userForm png">
			<form action="userRegister" method="post">
				<dl>
					<div id="error">${message }</div>
					<dt>用户名：</dt>
					<dd>
						<input type="text" name="username" />
					</dd>
					<dt>密 码：</dt>
					<dd>
						<input type="password" name="password"/>
					</dd>
					<dt>确认密码：</dt>
					<dd>
						<input type="password" name="password2"/>
					</dd>
					<dt>昵称：</dt>
					<dd>
						<input type="text" name="nickname"/>
					</dd>
					<dt>邮 箱：</dt>
					<dd>
						<input type="email" name="email"/>
					</dd>
				</dl>
				<div class="buttons">
					<input class="btn-reg png" type="submit" value="" />
					<input class="btn-reset png" type="reset" value="" />
				</div>
				<div class="goback">
					<a href="login.html" class="png">返回登录页</a>
				</div>
			</form>
		</div>
	</body>
</html>
