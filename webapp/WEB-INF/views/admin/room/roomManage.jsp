<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.AdminDAO" %>
<%@ page import="com.vo.RoomVO" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<h2>객실 목록</h2>
<form action="${pageContext.request.contextPath}/admin/roomAdd.do"
      method="get"
      style="display:inline;">
    <button type="submit">객실 추가</button>
</form>
<table>
    <tr>
        <th>번호</th>
        <th>객실명</th>
        <th>정원</th>
        <th>위치</th>
        <th>룸구성</th>
        <th>관리</th>
    </tr>

<c:forEach var="room" items="${roomList}">
    <tr>
        <td>${room.room_no}</td>
        <td>${room.room_name}</td>
        <td>${room.capacity}</td>
        <td>${room.room_location}</td>
        <td>${room.room_description}</td>
         <td>
            <form action="${pageContext.request.contextPath}/admin/roomDetail.do"
      method="get"
      style="display:inline;">

    <input type="hidden" name="room_no" value="${room.room_no}">
    <button type="submit">수정</button></form><form action="${pageContext.request.contextPath}/admin/roomDelete.do"
      method="post"
      style="display:inline;"
      onsubmit="return confirm('정말 삭제하시겠습니까?');">

    <input type="hidden" name="room_no" value="${room.room_no}">
    <button type="submit">삭제</button>
</form>
        </td>
    </tr>
</c:forEach>

</table>