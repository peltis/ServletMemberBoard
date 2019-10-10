<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<div id="new_board" class="container board_base">
	<h2>신입 멤버 알림 게시판</h2>
	<ul>
		<li>글번호</li>
		<li style="width: 400px;">글제목</li>
		<li>작성자</li>
		<li>작성일자</li>
		<li>조회수</li>
	</ul>
	<c:forEach var="result" items="${boardList}">
		<ul style="margin-top: -17px;">
			<li>${result.boardNumber}</li>
			<li style="width: 400px;">
				<!-- BoardListContents?boardnumber=${result.boardNumber}&page=${paging.page} -->
				<a href="#" style="color: #f18c8e;"> ${result.boardSubject} </a>
			</li>
			<li>${result.boardWrite}</li>
			<li>${result.boardDate}</li>
			<li>${result.boardHit}</li>
		</ul>
	</c:forEach>

	<c:if test="${paging.page<=1}">
		[이전]&nbsp;
	</c:if>

	<c:if test="${paging.page>1}">
		<a href="BoardNewMember?page=${paging.page-1}">[이전]</a>&nbsp;
	</c:if>

	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
		step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
				<span style="color: #ff8260;">${i}</span>
			</c:when>
			<c:otherwise>
				<a href="BoardNewMember?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${paging.page>=paging.maxPage}">
		[다음]
	</c:if>

	<c:if test="${paging.page<paging.maxPage}">
		<a href="BoardNewMember?page=${paging.page+1}">[다음]</a>
	</c:if>
</div>
</body>
</html>