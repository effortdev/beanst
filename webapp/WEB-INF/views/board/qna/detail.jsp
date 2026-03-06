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
				<span>작성자: ${fn:substring(qna.userId, 0, 3)}***</span> <span>등록일:
					<fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd HH:mm" />
				</span> <span>상태: <span
					class="badge badge-${fn:toLowerCase(qna.status)}">${qna.status == 'WAITING' ? '답변대기' : '답변완료'}</span></span>
			</div>
			<div class="view-content">${qna.content}</div>

			<%-- 답변 영역: 답변완료 상태일 때만 표시 --%>
			<c:if test="${qna.status == 'ANSWERED' && not empty qna.answer}">
				<div class="qna-answer-section">
					<div class="qna-answer-header">
						<span class="qna-answer-icon">💬</span> <strong>관리자 답변</strong> <span
							class="qna-answer-date"> <fmt:formatDate
								value="${qna.answerDate}" pattern="yyyy-MM-dd HH:mm" />
						</span>
					</div>
					<div class="qna-answer-body">${qna.answer}</div>
				</div>
			</c:if>

			<div class="board-actions">
				<a href="${pageContext.request.contextPath}/qnaList.do"
					class="btn btn-secondary">목록</a>
			</div>
		</div>
	</div>
</div>