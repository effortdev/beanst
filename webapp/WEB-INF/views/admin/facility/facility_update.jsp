<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>시설 수정</h2>

<form
	action="${pageContext.request.contextPath}/admin/facility/update.do"
	method="post" enctype="multipart/form-data">

	<input type="hidden" name="facilityId" value="${facility.facilityId}" />

	타입: <input type="text" name="facilityType"
		value="${facility.facilityType}"><br> 이름: <input
		type="text" name="facilityName" value="${facility.facilityName}"><br>

	위치: <input type="text" name="location" value="${facility.location}"><br>

	운영시간: <input type="text" name="openTime" value="${facility.openTime}"><br>

	설명:
	<textarea name="description">${facility.description}</textarea>
	<br>

	<hr>

	<h3>기존 이미지</h3>

	<c:forEach var="img" items="${imageList}">
		<div>
			<img src="${img.imagePath}" width="120"> 대표: <input
				type="radio" name="mainImageId" value="${img.imageId}"
				${img.isMain == 'Y' ? 'checked' : ''}> 삭제: <input
				type="checkbox" name="deleteImageIds" value="${img.imageId}">
		</div>
	</c:forEach>

	<hr>

	<h3>새 이미지 추가</h3>

	<input type="file" id="newImages" name="newImages" multiple
		accept="image/*">
	<div id="imagePreview"></div>

	<button type="submit">수정 완료</button>

</form>

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
            div.style.display = "inline-block";
            div.style.margin = "10px";
            div.style.textAlign = "center";

            const img = document.createElement("img");
            img.src = e.target.result;
            img.width = 120;



            const radio = document.createElement("input");
            radio.type = "radio";
            radio.name = "mainNewImage";
            radio.value = index;

            if(index === 0){
                radio.checked = true;
            }


            radio.addEventListener("change", function(){

                document.querySelectorAll("input[name='mainImageId']")
                    .forEach(r => r.checked = false);

            });



            const delBtn = document.createElement("button");
            delBtn.type = "button";
            delBtn.innerText = "삭제";

            delBtn.onclick = function(){
                removeImage(index);
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



function removeImage(index){

    fileList.splice(index,1);

    const dataTransfer = new DataTransfer();

    fileList.forEach(file=>{
        dataTransfer.items.add(file);
    });

    input.files = dataTransfer.files;

    renderPreview();
}



document.querySelectorAll("input[name='mainImageId']").forEach(radio => {

    radio.addEventListener("change", function(){

        document.querySelectorAll("input[name='mainImageIndex']")
            .forEach(r => r.checked = false);

    });

});

</script>

