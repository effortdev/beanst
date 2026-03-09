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
					<td>${status.count}</td>
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
								onsubmit="return confirm('정말 삭제하시겠습니까?');">
								<input type="hidden" name="room_id" value="${room.room_id}">
								<button type="submit" class="room-btn-delete">삭제</button>
							</form>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>