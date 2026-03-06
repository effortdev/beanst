<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-qna-detail">

	<h2 class="admin-title">1:1 문의 상세</h2>

	<div class="qna-meta">

		<p>
			<b>작성자</b> : ${qna.userId}
		</p>
		<p>
			<b>제목</b> : ${qna.title}
		</p>

	</div>

	<div class="qna-question">

		<h3>질문 내용</h3>

		<div class="question-box">${qna.content}</div>

	</div>

	<div class="qna-answer">

		<h3>관리자 답변</h3>

		<form action="${pageContext.request.contextPath}/admin/qna/answer.do"
			method="post">

			<input type="hidden" name="qna_no" value="${qna.qnaNo}">

			<textarea name="answer">${qna.answer}</textarea>

			<div class="answer-buttons">

				<button type="submit" class="btn-save"
					onclick="return confirm('정말 등록하시겠습니까?')">답변 등록</button>

				<a class="btn-delete"
					href="${pageContext.request.contextPath}/admin/qna/delete.do?qna_no=${qna.qnaNo}"
					onclick="return confirm('정말 삭제하시겠습니까?')"> 삭제 </a> <a
					class="btn-list"
					href="${pageContext.request.contextPath}/admin/qna/list.do">
					목록보기 </a>

			</div>

		</form>

	</div>

</div>