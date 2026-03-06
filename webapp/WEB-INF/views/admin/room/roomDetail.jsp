<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>객실 수정</h2>

<form action="${pageContext.request.contextPath}/admin/roomUpdate.do"
	method="post" enctype="multipart/form-data">
	<input type="hidden" name="room_id" value="${vo.room_id}"> 객실명:
	<input type="text" name="room_name" value="${vo.room_name}"><br>
	정원: <input type="text" name="capacity" value="${vo.capacity}"><br>
	위치: <input type="text" name="room_location" value="${vo.room_location}"><br>
	룸구성:
	<textarea name="room_description">${vo.room_description}</textarea>
	<br> 이용시간: <input type="text" name="usage_time"
		value="${vo.usage_time}"><br> 어매니티: <input type="text"
		name="amenity" value="${vo.amenity}"><br> 미니바: <input
		type="text" name="minibar" value="${vo.minibar}"><br>
	이미지: <input type="file" id="room_img" name="room_img" id="room_img"
		multiple onchange="previewImages(event)"><br>
	<div id="preview"></div>
	<hr>
	기존이미지<br>
	<c:forEach var="image" items="${imageVO}">
		<img src="${image.image_path}" width="200">
		<input type="radio" name="main_image" value="${image.image_no}"
			${image.is_main eq 'Y' ? 'checked' : ''}>
        대표이미지
        
        
	<input type="checkbox" name="delete_images" value="${image.image_no}">
    	삭제<br>
	</c:forEach>

	<button type="submit">수정</button>
	<button type="button"
		onclick="location.href='${pageContext.request.contextPath}/admin/roomManage.do'">
		뒤로가기</button>
</form>

<script>

let selectedFiles = [];

function previewImages(event) {

    const preview = document.getElementById("preview");
    const input = document.getElementById("room_img");

    const files = Array.from(event.target.files);

    // 기존 파일 + 새 파일 합치기
    selectedFiles = selectedFiles.concat(files);

    renderPreview();
    updateInputFiles();
}

function renderPreview() {

    const preview = document.getElementById("preview");
    preview.innerHTML = "";

    selectedFiles.forEach((file, index) => {

        const reader = new FileReader();

        reader.onload = function(e) {

            const wrapper = document.createElement("div");
            wrapper.style.marginBottom = "10px";

            const img = document.createElement("img");
            img.src = e.target.result;
            img.width = 200;

         // 삭제 체크박스
            const removeCheck = document.createElement("input");
            removeCheck.type = "checkbox";

            removeCheck.onchange = function() {

                if (this.checked) {
                    selectedFiles.splice(index, 1);
                    renderPreview();
                    updateInputFiles();
                }
            };

            wrapper.appendChild(img);
            wrapper.appendChild(document.createElement("br"));

			wrapper.appendChild(removeCheck);
			wrapper.appendChild(document.createTextNode(" 등록취소"));

            preview.appendChild(wrapper);
        };

        reader.readAsDataURL(file);
    });
}

function updateInputFiles() {

    const input = document.getElementById("room_img");
    const dataTransfer = new DataTransfer();

    selectedFiles.forEach(file => {
        dataTransfer.items.add(file);
    });

    input.files = dataTransfer.files;
}

</script>