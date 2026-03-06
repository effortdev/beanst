<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>Q&A 관리</h2>

<table border="1" width="100%">

	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>상태</th>
		<th>작성일</th>
	</tr>

	<c:forEach var="q" items="${qnaList}">

		<tr>

			<td>${q.qnaNo}</td>

			<td><a
				href="${pageContext.request.contextPath}/admin/qna/detail.do?qna_no=${q.qnaNo}">
					${q.title} </a></td>

			<td>${q.userId}</td>

			<td><c:choose>

					<c:when test="${q.status eq 'WAITING'}">
						<span style="color: red">답변대기</span>
					</c:when>

					<c:otherwise>
						<span style="color: green">답변완료</span>
					</c:otherwise>

				</c:choose></td>

			<td>${q.regDate}</td>

		</tr>

	</c:forEach>

</table>