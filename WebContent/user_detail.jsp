<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
	<h2>글작성자 정보</h2>
	<ul class="user_detail">
		<li>name : ${memberList.name} </li>
		<li>sex: ${memberList.gender}</li>
		<li>age : ${memberList.age} </li>
		<li>mail : ${memberList.email}</li>
	</ul>
	
	<a href="BoardFreetalk">목록으로 돌아가기</a>
</body>
</html>