<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="admin-faq-write">

	<h2 class="admin-title">FAQ 등록</h2>

	<form action="${pageContext.request.contextPath}/admin/faq/insert.do"
		method="post" class="faq-form">

		<div class="form-group">
			<label>카테고리</label> <input type="text" name="category"
				list="faqTypes" placeholder="예약, 시설, 기타" required>
			<datalist id="faqTypes">
				<option value="예약">
				<option value="시설">
				<option value="기타">
			</datalist>
		</div>

		<div class="form-group">
			<label>질문</label>
			<textarea name="question" class="question-box"></textarea>
		</div>

		<div class="form-group">
			<label>답변</label>
			<textarea name="answer" class="answer-box"></textarea>
		</div>

		<div class="form-group">
			<label>정렬 순서</label> <input type="number" name="sort_order" value="0">
		</div>

		<div class="form-group">
			<label>상태</label> <select name="status">
				<option value="ACTIVE">사용</option>
				<option value="INACTIVE">숨김</option>
			</select>

		</div>

		<div class="form-buttons">

			<button type="submit" class="btn-save">등록</button>

			<a class="btn-list"
				href="${pageContext.request.contextPath}/admin/faq/list.do"> 목록
			</a>

		</div>

	</form>

</div>