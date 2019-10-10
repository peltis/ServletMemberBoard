<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<style>
#admin_page ul {
	margin: 0;
	padding: 0;
}

#admin_page ul>li {
	height: 20px;
	margin-left: -6px;
	display: inline-block;
	width: 130px;
	border: 1px solid #d9d9d9;
	padding: 10px 0;
}

#admin_page .admin_btn {
	border: 1px solid #ccc;
	padding: 5px;
	border-radius: 5px;
}
</style>

<div id="admin_page" class="container">
	<h2>멤버 리스트</h2>
	<ul>
		<li>멤버넘버</li>
		<li>아이디</li>
		<li>닉네임</li>
		<li>생년월일</li>
		<li>성별</li>
		<li>핸드폰</li>
		<li style="width: 200px;">이메일</li>
		<li>주소</li>
		<li>상세</li>
	</UL>
	<c:forEach var="result" items="${memberList}">
		<ul>
			<li>${result.memberNumber}</li>
			<li>${result.id}</li>
			<li>${result.name}</li>
			<li>${result.birthyy}/ ${result.birthmm} / ${result.birthdd}</li>
			<li>${result.gender}</li>
			<li>0${result.phone_one} - ${result.phone_two} -
				${result.phone_thr}</li>
			<li style="width: 200px;">${result.email}</li>
			<li><c:set var="add" value="${result.address}" /> 
			<c:if test="${add == null}">　</c:if> ${result.address}</li>
			<li><a href="AdminMemberModify?id=${result.id}" class="admin_btn_modify admin_btn">관리</a> </li>
		</ul>
	</c:forEach>

</div>
</body>
</html>
