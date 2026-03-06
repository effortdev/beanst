<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="admin-faq-write">

	<h2>FAQ 등록</h2>

	<form action="${pageContext.request.contextPath}/admin/faq/insert.do"
		method="post">

		<label>카테고리</label> <input type="text" name="category" value="일반">

		<label>질문</label> <input type="text" name="question"> <label>답변</label>
		<textarea name="answer"></textarea>

		<label>정렬 순서</label> <input type="number" name="sort_order" value="0">

		<label>상태</label> <select name="status">
			<option value="ACTIVE">사용</option>
			<option value="INACTIVE">숨김</option>
		</select>

		<button type="submit">등록</button>

	</form>

</div>