<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<div id="freetalk_board" class="container board_base">
	<h2>자유게시판</h2>
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
			<li style="width: 400px;"><a
				href="BoardFreetalkContents?boardNumber=${result.boardNumber}&page=${paging.page}"
				style="color: #f18c8e"> ${result.boardSubject} </a></li>
			<li class="boardWrite">
				<a href="BoardFreetalkUserDetail?boardWrite=${result.boardWrite}" style="color:#bb1542">${result.boardWrite}</a>
			</li>
			<li>${result.boardDate}</li>	
			<li>${result.boardHit}</li>
		</ul>
	</c:forEach>

	<c:if test="${paging.page<=1}">
		[이전]&nbsp;
	</c:if>

	<c:if test="${paging.page>1}">
		<a href="BoardFreetalk?page=${paging.page-1}">[이전]</a>&nbsp;
	</c:if>

	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
		step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
				<span style="color: #ff8260;">${i}</span>
			</c:when>
			<c:otherwise>
				<a href="BoardFreetalk?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${paging.page>=paging.maxPage}">
		[다음]
	</c:if>

	<c:if test="${paging.page<paging.maxPage}">
		<a href="BoardFreetalk?page=${paging.page+1}">[다음]</a>
	</c:if>
	
		<div class="boardBot">
			<a href="javascript:write_btn();">글쓰기</a>
		</div>
</div>
</body>
</html>

<script>
	function write_btn(){
		var sessionId = '${sessionScope.id}';
		
		console.log("sessionId : " + sessionId );
		
		if ( sessionId == "" ) {
			alert("로그인 후 이용하세요.");
			//location.href="login_main.jsp";
		} 
		else {
			location.href="board_freetalk_write.jsp";
		}
	}
</script>