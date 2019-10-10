<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<script type="text/javascript">

	var httpRequest = null;

	// httpRequest 객체 생성
	function getXMLHttpRequest() {
		var httpRequest = null;

		if (window.ActiveXObject) {
			try {
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e2) {
					httpRequest = null;
				}
			}
		} else if (window.XMLHttpRequest) {
			httpRequest = new window.XMLHttpRequest();
		}
		return httpRequest;
	}

	// 댓글 등록
	function writeCmt() {
		var form = document.getElementById("writeCommentForm");

		var board = form.comment_board.value
		var id = form.comment_id.value
		var content = form.comment_content.value;

		if (!content) {
			alert("내용을 입력하세요.");
			return false;
		} else {
			var param = "comment_board=" + board + "&comment_id=" + id
					+ "&comment_content=" + content;

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "FreetalkComment", true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=EUC-KR');
			httpRequest.send(param);
		}
	}

	function checkFunc() {
		if (httpRequest.readyState == 4) {
			// 결과값을 가져온다.
			var resultText = httpRequest.responseText;
			if (resultText == 1) {
				document.location.reload(); // 상세보기 창 새로고침
			}
		}
	}
</script>

<script type="text/javascript">
	function modify_btn() {
		var boardWrite = '${boardList.boardWrite}';
		var sessionId = '${sessionScope.id}';
		var admin = 'admin';

		console.log("boardWrite : " + boardWrite);
		console.log("sessionId : " + sessionId);

		if (sessionId == "") {
			alert("로그인 후 이용하세요.");
			location.href = "login_main.jsp";
		} else {
			if (sessionId == admin) {
				alert("관리자가 글을 수정합니다.")
				location.href = "BoardFreetalkModifyView?boardNum=${boardList.boardNumber}";
			} else if (boardWrite != sessionId) {
				alert("본인이 아니면 수정할 수 없습니다");
			} else {
				location.href = "BoardFreetalkModifyView?boardNum=${boardList.boardNumber}";
			}
		}
	}

	function delete_btn() {
		var boardWrite = '${boardList.boardWrite}';
		var sessionId = '${sessionScope.id}';

		if (sessionId == "") {
			alert("로그인 후 이용하세요.");
			location.href = "login_main.jsp";
		} else {
			if (sessionId == admin) {
				alert("관리자가 글을 삭제합니다.")
				location.href = "BoardFreetalkDelete?boardNum=${boardList.boardNumber}";
			} else if (boardWrite != sessionId) {
				alert("본인이 아니면 삭제할 수 없습니다");
			} else {
				location.href = "BoardFreetalkDelete?boardNum=${boardList.boardNumber}";
			}
		}
	}
</script>

<div id="board_freetalk_write" class="container contents_base">
	<h2>${boardList.boardSubject}</h2>

	<ul>
		<li class="b_line"></li>
		<li class="b_middle"><span>작성일</span>${boardList.boardDate}</li>
		<li class="b_middle"><span>작성자</span>${boardList.boardWrite}</li>
		<li class="b_middle" style="border: none;"><span>조회수</span>${boardList.boardHit}</li>
		<li class="b_line"></li>
		<!-- 4448**XX		<li class="b_contents"> -->
		<li>${boardList.boardContent}
			<c:if test="${not empty boardList.boardFile}">
				<br>
				<img src="FreeBoardFile/${boardList.boardFile}">
			</c:if>
		</li>

		<li class="b_file"><span>첨부파일</span> <a
			href="BoardfileDown?downFile=${boardList.boardFile}">${boardList.boardFile}</a></li>

		<li class="contents_btn"><a href="javascript:modify_btn();">수정</a></li>
		<li class="contents_btn"><a href="javascript:delete_btn();">삭제</a></li>
		<li class="contents_btn"><a href="BoardFreetalk">목록</a></li>
	</ul>

	<div id="comment">
		<table style="border:1px solid lightgray; margin: 0 auto;">
			<!-- 댓글 목록 -->
			<c:if test="${requestScope.commentList != null}">
				<c:forEach var="comment" items="${requestScope.commentList}">
					<tr>
						<!-- 아이디, 작성날짜 -->
						<td>
							<div>
								${comment.comment_id}<br> <span>${comment.comment_date}</span>
							</div>
						</td>
						<!-- 본문내용 -->
						<td>
							<div class="text_wrapper">${comment.comment_content}</div>
						</td>
						<!-- 버튼 -->
						<td>
							<div id="btn" style="text-align: center;">
								<a href="#">[답변]</a><br>
								<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
								<c:if test="${comment.comment_id == sessionScope.sessionID}">
									<a href="#">[수정]</a>
									<br>
									<a href="#">[삭제]</a>
								</c:if>
							</div>
						</td>
					</tr>

				</c:forEach>
			</c:if>

			<!-- 로그인 했을 경우만 댓글 작성가능 -->
			<c:if test="${not empty sessionScope.id}">
				<tr id="writeCommentForm">
					<td>
						<input type="hidden" name="comment_board" value="${board.board_num}"> 
						<input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
					</td>
					<!-- 아이디-->
					<td style="width:150px"><div>${sessionScope.id}</div></td>
					<!-- 본문 작성-->
					<td style="width:550px">
						<div>
							<textarea name="comment_content" rows="4" cols="70"></textarea>
						</div>
					</td>
					<!-- 댓글 등록 버튼 -->
					<td style="width:100px">
						<div id="btn" style="text-align: center;">
							<p><a href="#" onclick="writeCmt()">[댓글등록]</a></p>	
						</div>
					</td>
				</tr>
			</c:if>
			<c:if test="${empty sessionScope.id}">
				<tr>
					<td>댓글 작성은 로그인 후에만 가능합니다.</td>
				</tr>
			</c:if>

		</table>
	</div>
</div>

</body>
</html>
