<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-facility-form">

	<h2>시설 수정</h2>

	<form
		action="${pageContext.request.contextPath}/admin/facility/update.do"
		method="post" enctype="multipart/form-data">

		<input type="hidden" name="facilityId" value="${facility.facilityId}" />

		<div class="facility-form-group">
			<label>시설 타입</label> <input type="text" name="facilityType"
				value="${facility.facilityType}">
		</div>

		<div class="facility-form-group">
			<label>시설 이름</label> <input type="text" name="facilityName"
				value="${facility.facilityName}">
		</div>

		<div class="facility-form-group">
			<label>위치</label> <input type="text" name="location"
				value="${facility.location}">
		</div>

		<div class="facility-form-group">
			<label>운영시간</label> <input type="text" name="openTime"
				value="${facility.openTime}">
		</div>

		<div class="facility-form-group">
			<label>설명</label>
			<textarea name="description">${facility.description}</textarea>
		</div>

		<hr>

		<h3 class="facility-image-title">기존 이미지</h3>

		<div class="image-grid">

			<c:forEach var="img" items="${imageList}">
				<div class="image-card">

					<img src="${img.imagePath}">

					<div class="image-controls">

						<label> <input type="radio" name="mainImage"
							value="old-${img.imageId}" ${img.isMain == 'Y' ? 'checked' : ''}>
							대표
						</label> <label class="delete-radio"> <input type="checkbox"
							name="deleteImageIds" value="${img.imageId}"> 삭제
						</label>

					</div>

				</div>
			</c:forEach>

		</div>

		<hr>

		<h3 class="facility-image-title">새 이미지 추가</h3>

		<input type="file" id="newImages" name="newImages" multiple
			accept="image/*">

		<div id="imagePreview" class="image-grid"></div>

		<button type="submit" class="btn-save">수정 완료</button>

	</form>

</div>


<script>

const input = document.getElementById("newImages");
const preview = document.getElementById("imagePreview");

let fileList = [];

input.addEventListener("change", function(){

	fileList = Array.from(this.files);
	renderPreview();

});


function renderPreview(){

	preview.innerHTML = "";

	fileList.forEach((file, index)=>{

		const reader = new FileReader();

		reader.onload = function(e){

			const div = document.createElement("div");
			div.className = "image-card";

			const img = document.createElement("img");
			img.src = e.target.result;

			const controls = document.createElement("div");
			controls.className = "image-controls";

			const radio = document.createElement("input");
			radio.type = "radio";
			radio.name = "mainImage";
			radio.value = "new-" + index;

			const label = document.createElement("label");
			label.appendChild(radio);
			label.appendChild(document.createTextNode("대표"));

			const delBtn = document.createElement("button");
			delBtn.type = "button";
			delBtn.innerText = "삭제";

			delBtn.onclick = function(){
				removeImage(index);
			};

			controls.appendChild(label);
			controls.appendChild(delBtn);

			div.appendChild(img);
			div.appendChild(controls);

			preview.appendChild(div);

		};

		reader.readAsDataURL(file);
	});

}


function removeImage(index){

	fileList.splice(index,1);

	const dataTransfer = new DataTransfer();

	fileList.forEach(file=>{
		dataTransfer.items.add(file);
	});

	input.files = dataTransfer.files;

	renderPreview();

}



</script>