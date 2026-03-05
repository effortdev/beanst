<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="page-content">
	<div class="container">
		<div class="board-view">
			<h2 class="view-title">${qna.title}</h2>
			<div class="view-info">
				<span>작성자: ${fn:substring(qna.memberId, 0, 3)}***</span> <span>등록일:
					<fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd HH:mm" />
				</span> <span>상태: <span
					class="badge badge-${fn:toLowerCase(qna.status)}">${qna.status == 'WAITING' ? '답변대기' : '답변완료'}</span></span>
			</div>
			<div class="view-content">${qna.content}</div>



			<div class="board-actions">
				<a href="${pageContext.request.contextPath}/qnaList.do"
					class="btn btn-secondary">목록</a>
			</div>
		</div>
	</div>
</div>

