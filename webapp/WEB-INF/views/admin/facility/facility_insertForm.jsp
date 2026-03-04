<%@ page contentType="text/html; charset=UTF-8"%>

<h2>시설 등록</h2>

<form
	action="${pageContext.request.contextPath}/admin/facility/insert.do"
	method="post" enctype="multipart/form-data">

	시설 타입 <input type="text" name="facilityType" list="facilityTypes"
		placeholder="예: Pool, Spa, KidsZone" required>

	<datalist id="facilityTypes">
		<option value="Pool">
		<option value="Spa">
		<option value="Gym">
		<option value="Restaurant">
		<option value="Lounge">
	</datalist>

	<br> 시설 이름 <input type="text" name="facilityName" required>
	<br> 위치 <input type="text" name="location"> <br> 운영시간
	<input type="text" name="openTime" placeholder="예: 06:00 - 22:00">
	<br> 설명
	<textarea name="description"></textarea>

	<hr>

	<h3>시설 이미지</h3>

	<input type="file" id="imageInput" name="images" multiple
		accept="image/*">

	<div id="previewContainer"></div>

	<br>

	<button type="submit">시설 등록</button>

</form>
<script>

const input = document.getElementById("imageInput");
const preview = document.getElementById("previewContainer");

let fileList = [];

input.addEventListener("change", function(){

    fileList = Array.from(this.files);
    renderPreview();

});

function renderPreview(){

    preview.innerHTML = "";

    fileList.forEach((file, index) => {

        const reader = new FileReader();

        reader.onload = function(e){

            const div = document.createElement("div");
            div.style.display = "inline-block";
            div.style.margin = "10px";

            const img = document.createElement("img");
            img.src = e.target.result;
            img.width = 120;

            const radio = document.createElement("input");
            radio.type = "radio";
            radio.name = "mainImageIndex";
            radio.value = index;

            if(index === 0){
                radio.checked = true;
            }

            const delBtn = document.createElement("button");
            delBtn.type = "button";
            delBtn.innerText = "삭제";

            delBtn.onclick = function(){
                fileList.splice(index, 1);
                updateInputFiles();
                renderPreview();
            };

            div.appendChild(img);
            div.appendChild(document.createElement("br"));
            div.appendChild(radio);
            div.appendChild(document.createTextNode("대표 "));
            div.appendChild(delBtn);

            preview.appendChild(div);

        };

        reader.readAsDataURL(file);

    });

}

function updateInputFiles(){

    const dataTransfer = new DataTransfer();

    fileList.forEach(file => dataTransfer.items.add(file));

    input.files = dataTransfer.files;

}

</script>