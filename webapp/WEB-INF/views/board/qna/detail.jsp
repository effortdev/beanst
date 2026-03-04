<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<div class="page-content"><div class="container">
    <div class="board-view">
        <h2 class="view-title">
            <c:if test="${qna.isSecret == 'Y'}"><span class="badge badge-secret">비밀</span></c:if>
            ${qna.title}
        </h2>
        <div class="view-info">
            <span>작성자: ${fn:substring(qna.memberId, 0, 3)}***</span>
            <span>등록일: <fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd HH:mm"/></span>
            <span>상태: <span class="badge badge-${fn:toLowerCase(qna.status)}">${qna.status == 'WAITING' ? '답변대기' : '답변완료'}</span></span>
        </div>
        <div class="view-content">${qna.content}</div>

        <c:if test="${not empty qna.answer}">
        <div style="margin-top:30px;padding:25px;background:#fdf6ea;border-radius:8px;border-left:4px solid #c9a96e">
            <h4 style="color:#c9a96e;margin-bottom:10px">&#128172; 관리자 답변</h4>
            <p style="white-space:pre-wrap">${qna.answer}</p>
            <p style="color:#999;font-size:12px;margin-top:10px"><fmt:formatDate value="${qna.answerDate}" pattern="yyyy-MM-dd HH:mm"/></p>
        </div>
        </c:if>

        <c:if test="${not empty sessionScope.loginUser && sessionScope.loginUser.role == 'ADMIN' && qna.status == 'WAITING'}">
        <div style="margin-top:20px;padding:25px;background:#f8f9fa;border-radius:8px">
            <h4 style="margin-bottom:10px">답변 작성</h4>
            <form action="${pageContext.request.contextPath}/adminQnaAnswer.do" method="post">
                <input type="hidden" name="qnaNo" value="${qna.qnaNo}">
                <div class="form-group"><textarea name="answer" class="form-control" style="min-height:150px" required placeholder="답변 내용을 입력하세요"></textarea></div>
                <button type="submit" class="btn btn-primary">답변 등록</button>
            </form>
        </div>
        </c:if>

        <div class="board-actions">
            <a href="${pageContext.request.contextPath}/qnaList.do" class="btn btn-secondary">목록</a>
        </div>
    </div>
</div></div>