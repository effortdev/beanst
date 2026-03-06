<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>Q&A 상세</h2>

<p>
	<b>작성자</b> : ${qna.userId}
</p>
<p>
	<b>제목</b> : ${qna.title}
</p>

<hr>

<h3>질문 내용</h3>

<div style="border: 1px solid #ccc; padding: 15px;">
	${qna.content}</div>

<hr>

<h3>관리자 답변</h3>

<form action="${pageContext.request.contextPath}/admin/qna/answer.do"
	method="post">

	<input type="hidden" name="qna_no" value="${qna.qnaNo}">

	<textarea name="answer" rows="6" style="width: 100%">${qna.answer}</textarea>

	<br>
	<br>

	<button type="submit">답변 등록</button>

	<a
		href="${pageContext.request.contextPath}/admin/qna/delete.do?qna_no=${qna.qnaNo}">
		삭제 </a>

</form>