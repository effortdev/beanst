<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="page-content">
	<div class="container">
		<div class="form-box" style="max-width: 800px">
			<h2>1:1 문의 작성</h2>
			<c:if test="${not empty errorMsg}">
				<div class="alert alert-danger" style="margin-top: 10px;">
					${errorMsg}</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/qnaInsert.do"
				method="post">
				<div class="form-group">
					<label>제목</label> <input type="text" name="title"
						class="form-control" required placeholder="제목을 입력하세요">
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea name="content" class="form-control"
						style="min-height: 250px" required placeholder="문의 내용을 입력하세요"></textarea>
				</div>
				<div class="board-actions">
					<button type="submit" class="btn btn-primary">등록</button>
					<a href="${pageContext.request.contextPath}/qnaList.do"
						class="btn btn-secondary">취소</a>
				</div>
			</form>
		</div>
	</div>
</div>
