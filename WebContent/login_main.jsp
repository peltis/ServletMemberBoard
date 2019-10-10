<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %> 

<div id="login_main" class="container form_base" >
	<h2>Login page</h2>	
 	<form name="LoginForm" action="LoginForm" method="post" onsubmit="return checkLogin();">
 		<ul>
			<li>
				<span>ID </span>
				<input type="text" name="id" id="id" autofocus placeholder="input your ID" maxlength="20">
			</li>
			<li>
				<span>PW </span>
				<input type="password" name="pw" id="pw" autofocus placeholder="input your PW" maxlength="20">
			</li>
			<li><input type="submit" value="로그인"></li>
		</ul>
 	</form>
 </div>
</body>
</html>	