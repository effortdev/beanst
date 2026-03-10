<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="admin-room-update">
	<h2 class="admin-title">객실 추가</h2>

	<form action="${pageContext.request.contextPath}/admin/roomInsert.do"
		method="post" enctype="multipart/form-data">

		<div class="form-group">
			<label>객실명</label> <input type="text" name="room_name">
		</div>
		<div class="form-group">
			<label>정원</label> <input type="text" name="capacity">
		</div>
		<div class="form-group">
			<label>위치</label> <input type="text" name="room_location">
		</div>
		<div class="form-group">
			<label>룸구성</label> <input type="text" name="room_description">
		</div>
		<div class="form-group">
			<label>이용시간</label> <input type="text" name="usage_time">
		</div>
		<div class="form-group">
			<label>어매니티</label> <input type="text" name="amenity">
		</div>
		<div class="form-group">
			<label>미니바</label> <input type="text" name="minibar">
		</div>
		<div class="form-group">
			<label>이미지</label> <input type="file" id="room_img" name="room_img"
				multiple accept="image/*" onchange="previewImages(event)">
		</div>
		<div id="preview"></div>
		<div class="form-buttons">
			<button type="submit" class="btn-save">등록</button>
			<a class="btn-list"
				href='${pageContext.request.contextPath}/admin/roomManage.do'">
				목록</a>
		</div>
	</form>
</div>
<script>

let selectedFiles = [];

function previewImages(event) {

    const preview = document.getElementById("preview");
    const input = document.getElementById("room_img");

    const files = Array.from(event.target.files);


    if (selectedFiles.length + files.length > 5) {
        alert("이미지는 최대 5개까지 업로드 가능합니다.");
        event.target.value = "";
        return;
    }

    for (let i = 0; i < files.length; i++) {

        const file = files[i];

        if (!file.type.startsWith("image/")) {
            alert("이미지 파일만 업로드 가능합니다.");
            event.target.value = "";
            return;
        }

    }


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