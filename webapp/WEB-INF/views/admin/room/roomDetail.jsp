<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
 <h2>객실 수정</h2>

<form action="${pageContext.request.contextPath}/admin/roomUpdate.do" method="post">
    <input type="hidden" name="room_no" value="${vo.room_no}">

    객실명: <input type="text" name="room_name" value="${vo.room_name}"><br>
    정원: <input type="text" name="capacity" value="${vo.capacity}"><br>
    위치: <input type="text" name="room_location" value="${vo.room_location}"><br>
    룸구성: <textarea name="room_description">${vo.room_description}</textarea><br>
    이미지: <input type="file" name="room_img"><br>
<%--     <img  src="${pageContext.request.contextPath}${vo.image_path}" width="200"> --%>

    <button type="submit">수정완료</button>
</form>