<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="admin-faq-edit">

	<h2 class="admin-title">FAQ 수정</h2>

	<form action="${pageContext.request.contextPath}/admin/faq/update.do"
		method="post" class="faq-form">

		<input type="hidden" name="faq_no" value="${faq.faqNo}">

		<div class="form-group">
			<label>카테고리</label> <input type="text" name="category"
				value="${faq.category}">
		</div>

		<div class="form-group">
			<label>질문</label>
			<textarea name="question" class="question-box">${faq.question}</textarea>
		</div>

		<div class="form-group">
			<label>답변</label>
			<textarea name="answer" class="answer-box">${faq.answer}</textarea>
		</div>

		<div class="form-group">
			<label>정렬 순서</label> <input type="number" name="sort_order"
				value="${faq.sortOrder}">
		</div>

		<div class="form-group">
			<label>상태</label> <select name="status">

				<option value="ACTIVE"
					<c:if test="${faq.status eq 'ACTIVE'}">selected</c:if>>사용
				</option>

				<option value="INACTIVE"
					<c:if test="${faq.status eq 'INACTIVE'}">selected</c:if>>
					숨김</option>

			</select>

		</div>

		<div class="form-buttons">

			<button type="submit" class="btn-save">수정</button>

			<a class="btn-delete"
				href="${pageContext.request.contextPath}/admin/faq/delete.do?faq_no=${faq.faqNo}"
				onclick="return confirm('삭제하시겠습니까?')"> 삭제 </a> <a class="btn-list"
				href="${pageContext.request.contextPath}/admin/faq/list.do"> 목록
			</a>

		</div>

	</form>

</div>