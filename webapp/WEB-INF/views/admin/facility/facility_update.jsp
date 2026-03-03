<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>시설 수정</h2>

<form action="${pageContext.request.contextPath}/admin/facility/update.do"
      method="post"
      enctype="multipart/form-data">

    <input type="hidden" name="facilityId" value="${facility.facilityId}"/>

    타입:
    <input type="text" name="facilityType" value="${facility.facilityType}"><br>

    이름:
    <input type="text" name="facilityName" value="${facility.facilityName}"><br>

    위치:
    <input type="text" name="location" value="${facility.location}"><br>

    운영시간:
    <input type="text" name="openTime" value="${facility.openTime}"><br>

    설명:
    <textarea name="description">${facility.description}</textarea><br>

    <hr>

    <h3>기존 이미지</h3>

    <c:forEach var="img" items="${imageList}">
        <div>
            <img src="${pageContext.request.contextPath}${img.imagePath}"
                 width="120">

            대표:
            <input type="radio"
                   name="mainImageId"
                   value="${img.imageId}"
                   ${img.isMain == 'Y' ? 'checked' : ''}>

            삭제:
            <input type="checkbox"
                   name="deleteImageIds"
                   value="${img.imageId}">
        </div>
    </c:forEach>

    <hr>

    <h3>새 이미지 추가</h3>
    <input type="file" name="newImages" multiple>

    <br><br>

    <button type="submit">수정 완료</button>

</form>