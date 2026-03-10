<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.vo.RoomManageVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<div class="admin-room">
	<div class="titleBox">
		<h2 class="admin-title">객실 정보 관리</h2>
		<div class="room-filter">
			<a href="${pageContext.request.contextPath}/admin/roomAdd.do"
				class="btn-filter">객실 추가</a>
		</div>
	</div>

	<table class="admin-table">
		<thead>
			<tr>
				<th>번호</th>
				<th>객실명</th>
				<th>정원</th>
				<th>위치</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="room" items="${roomList}" varStatus="status">
				<tr>
					<td>${(pageInfo.currentPage-1)*pageInfo.boardLimit + status.count}</td>
					<td>${room.room_name}</td>
					<td>${room.capacity}</td>
					<td>${room.room_location}</td>
					<%-- 			<td>${room.room_description}</td> --%>
					<td>
						<div class="room-actions">
							<form
								action="${pageContext.request.contextPath}/admin/roomUpdate.do"
								method="get" style="display: inline;">
								<input type="hidden" name="room_id" value="${room.room_id}">
								<button type="submit" class="room-btn-edit">수정</button>
							</form>
							<form
								action="${pageContext.request.contextPath}/admin/roomDelete.do"
								method="post" style="display: inline;"
								onsubmit="return confirm('이 객실을 삭제하시겠습니까?\n예약 데이터가 있을 경우 삭제되지 않습니다.');">

								<input type="hidden" name="room_id" value="${room.room_id}">

								<button type="submit" class="room-btn-delete">삭제</button>

							</form>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">


		<c:choose>
			<c:when test="${pageInfo.currentPage > 1}">
				<a
					href="${pageContext.request.contextPath}/admin/roomManage.do?page=${pageInfo.currentPage - 1}">이전</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">이전</a>
			</c:otherwise>
		</c:choose>


		<c:forEach var="i" begin="${pageInfo.startPage}"
			end="${pageInfo.endPage}">
			<c:choose>
				<c:when test="${i eq pageInfo.currentPage}">
					<span class="active">${i}</span>
				</c:when>
				<c:otherwise>
					<a
						href="${pageContext.request.contextPath}/admin/roomManage.do?page=${i}">
						${i} </a>
				</c:otherwise>
			</c:choose>
		</c:forEach>


		<c:choose>
			<c:when test="${pageInfo.currentPage < pageInfo.maxPage}">
				<a
					href="${pageContext.request.contextPath}/admin/roomManage.do?page=${pageInfo.currentPage + 1}">다음</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">다음</a>
			</c:otherwise>
		</c:choose>

	</div>
</div>