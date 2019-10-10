<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RAON COMMUNITY</title>

<link rel="stylesheet" href="style.css" type="text/css">
<script type="text/javascript">
	function checkLogin() {
		if (!document.LoginForm.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (!document.LoginForm.pw.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
	}
</script>
<script type="text/javascript">
	// 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
	function checkValue() {
		if (!document.JoinForm.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		var chk = document.JoinForm.id_duplication.value;
		
		if (document.JoinForm.id_duplication.value != "id_check") {
			console.log(chk);
			alert("아이디를 중복체크를 해주세요.");
			return false;
		}

		if (!document.JoinForm.pw.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}

		// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
		if (document.JoinForm.pw.value != document.JoinForm.pw_check.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}

		if (!document.JoinForm.name.value) {
			alert("이름을 입력하세요.");
			return false;
		}

		if (!document.JoinForm.birthyy.value) {
			alert("생년월일을 정확히 선택하세요.");
			return false;
		}

		if (!document.JoinForm.birthmm.value) {
			alert("생년월일을 정확히 선택하세요.");
			return false;
		}
		if (!document.JoinForm.birthdd.value) {
			alert("생년월일을 정확히 선택하세요.");
			return false;
		}
		if (!document.JoinForm.phone_two.value) {
			alert("전화번호를 정확히 입력하세요.");
			return false;
		}
		if (!document.JoinForm.phone_thr.value) {
			alert("전화번호를 정확히 입력하세요.");
			return false;
		}
		if (!document.JoinForm.gender.value) {
			alert("성별을 선택하세요.");
			return false;
		}

		if (!document.JoinForm.email.value) {
			alert("이메일을 입력하세요.");
			return false;
		}
	}

	// 취소 버튼 클릭시 로그인 화면으로 이동
	function goLoginForm() {
		location.href = "LoginForm.jsp";
	}
</script>

</head>

<body>
	<div class="top_container">
		<div id="top_menu">
			<div class="account">
				<ul>
					<li><a href="Main.jsp">HOME</a></li>
					<c:if test="${empty sessionScope.id}">
						<li><a href="login_main.jsp">로그인</a></li>
						<li><a href="Join_form.jsp">회원가입</a></li>
					</c:if>
					<li><a href="BoardFreetalk">자유게시판</a></li>	
					<c:if test="${not empty sessionScope.id}">
						<li><a href="MemberPage">마이페이지</a></li>
					</c:if>

					<c:if test="${sessionScope.id eq 'admin'}">
						<li><a href="MemberList" style="padding-left: 13px;">회원관리(관리자전용)</a></li>
					</c:if>
					
					<c:if test="${not empty sessionScope.id}">
						<li><a href="LogoutMember" class="top_login">로그아웃</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</div>
	
	<div class="middle_container">
		<div id="middle_menu" class="r_width">
			<h1>
				<a href="Main.jsp"><img id="main_logo"
					src="img/logo_top.png" style="width:100px;"></a>
			</h1>
			<div class="search">
			<input name="is_keyword" type="text" class="TopinputText" title="keyword" value=" 검색..." onfocus="if(this.value==' 검색...')this.value='';" onblur="if(this.value=='')this.value=' 검색...';" />
			<input type="image" src="img/empty.gif" alt="submit" title="submit" class="Topsearch" />		
			</div>
		</div>
	</div>
	
	<div class="gnb_container">

	</div>