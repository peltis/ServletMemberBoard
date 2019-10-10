<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>

<div id="board_freetalk_write" class="container form_base">
	<h2>게시글 수정</h2>
	<form name="board_freetalk_modify" id="board_freetalk_modify" action = "BoardFreetalkModifyComplete?boardNum=${boardList.boardNumber}" method="post" 
			 enctype="multipart/form-data">
			<ul>
				<li>
					<span>작성자 : </span>
					<input type="text"  name="boardWriter" id="boardWriter" value="${sessionScope.id}" readonly maxlength="50">				
				</li>
				<li>
					<span>제목 : </span>
					<input type="text" name="subject" id="subject" value="${boardList.boardSubject}" autofocus maxlength="50" >				
				</li>
				<li>
					<span>내용 : </span><br><br>
					<textarea  name="boardContent" id ="boardContent" rows="10" cols="100">${boardList.boardSubject}</textarea>
				</li>
				<li>
					<span>첨부파일 : </span>
					<input type="file" name="boardFile" id="boardFile" value="${boardList.boardFile}">
				</li>
				<li><input type="submit" value="업로드"></li>
			</ul>
		</form>
</div>
</body>
</html>
