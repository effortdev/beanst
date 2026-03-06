<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h2>객실 추가</h2>

<form action="${pageContext.request.contextPath}/admin/roomInsert.do"
	method="post" enctype="multipart/form-data">

	객실번호: <input type="text" name="room_id"><br> 객실명: <input
		type="text" name="room_name"><br> 정원: <input type="text"
		name="capacity"><br> 위치: <input type="text"
		name="room_location"><br> 룸구성: <input type="text"
		name="room_description"><br> 이용시간: <input type="text"
		name="usage_time"><br> 어매니티: <input type="text"
		name="amenity"><br> 미니바: <input type="text"
		name="minibar"><br> 이미지: <input type="file" id="room_img"
		name="room_img" id="room_img" multiple onchange="previewImages(event)"><br>
	<div id="preview"></div>
	<hr>
	<button type="submit">등록</button>
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