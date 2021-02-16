<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>短消息平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<script type="text/javascript" src="js/cookie.js"></script>
</head>
<body>

	<div id="loginTitle" class="png"></div>
	<div id="loginForm" class="userForm png">
		<form action="LoginServlet" method="post">
			<dl>
				<div id="error">${empty error ? "&nbsp;" : error}</div>
				<dt>用户名：</dt>
				<dd><input id="username" type="text" name="username" /></dd>
				<dt>密　码：</dt>
				<dd><input id="password" type="password" name="password" /></dd>
				<dd><input id="rememberMe" type="checkbox" name="rememberMe" value="true" class="pass_status"><span>记住密码</span> </dd>
			</dl>
			<div class="buttons">
				<input class="btn-login png" type="submit" value="" />
				<a href="register.html"><input class="btn-reg png" type="button" value="" /></a>
			</div>
		</form>
	</div>

</body>
<script type="text/javascript">
	var cookieUtil = new CookieUtil();
	var username = cookieUtil.getCookie("username");
	var password = cookieUtil.getCookie("password");
	console.log("username: " + username);
	console.log("password: " + password);
	
	var usernameEle = document.getElementById("username");
	var passwordEle = document.getElementById("password");
	var rememberMe = document.getElementById("rememberMe");
	if(username && password) {
		console.log("记住过密码");
		username = decodeURI(username);
		password = decodeURI(password);
		usernameEle.value = username;
		passwordEle.value = password;
		rememberMe.checked = true;
	} else {
		console.log("没有记住过密码");
		usernameEle.value = "";
		passwordEle.value = "";
		rememberMe.checked = false;
	}
	
</script>
</html>