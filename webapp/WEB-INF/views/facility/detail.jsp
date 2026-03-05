<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="wrap">
	<div class="facilityHeader">

		<div class="breadcrumb fade-up">
			<a href="${pageContext.request.contextPath}/main.do" class="en">Home</a>
			> <a href="${pageContext.request.contextPath}/facilityList.do">호텔
				정보</a> > <span class="selectNone">${facility.facilityName}</span>
		</div>

		<h1 class="title en fade-up">${facility.facilityName}</h1>

		<p class="desc fade-up">${facility.description}</p>

	</div>

	<section class="bgImg">
		<c:forEach var="img" items="${images}">
			<c:if test="${img.isMain eq 'Y'}">

				<div class="facilityHero">
					<img src="${img.imagePath}">
				</div>

			</c:if>
		</c:forEach>
	</section>

	<section class="hotelInfo">
		<table class="facilityInfoTable">
			<tr>
				<th>시설 이름</th>
				<td>${facility.facilityName}</td>
			</tr>
			<tr>
				<th>운영 시간</th>
				<td>${facility.openTime}</td>
			</tr>
			<tr>
				<th>시설 위치</th>
				<td>${facility.location}</td>
			</tr>
		</table>
	</section>

	<section class="Gallery">

		<div class="facilityGallery">

			<c:forEach var="img" items="${images}" varStatus="status">
				<c:if test="${img.isMain ne 'Y'}">

					<div class="galleryItem" data-index="${status.index}">
						<img src="${img.imagePath}">
					</div>

				</c:if>
			</c:forEach>

		</div>

	</section>

	<div class="galleryModal">

		<div class="galleryModalInner">

			<span class="galleryClose">✕</span>

			<div class="swiper gallerySwiper">

				<div class="swiper-wrapper">

					<c:forEach var="img" items="${images}">
						<c:if test="${img.isMain ne 'Y'}">

							<div class="swiper-slide">
								<img src="${img.imagePath}">
							</div>

						</c:if>
					</c:forEach>

				</div>

				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>

			</div>

		</div>

	</div>

</div>

<script>
document.addEventListener("DOMContentLoaded", function () {

    const targets = document.querySelectorAll(".fade-up");

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {

            if (entry.isIntersecting) {
                entry.target.classList.add("show");


                observer.unobserve(entry.target);
            }

        });
    }, {
        threshold: 0.3  
    });

    targets.forEach(target => {
        observer.observe(target);
    });

});


document.addEventListener("DOMContentLoaded", function(){

	const modal = document.querySelector(".galleryModal");
	const closeBtn = document.querySelector(".galleryClose");
	const items = document.querySelectorAll(".galleryItem");

	const swiper = new Swiper(".gallerySwiper",{
		navigation:{
			nextEl:".swiper-button-next",
			prevEl:".swiper-button-prev"
		}
	});

	items.forEach((item,index)=>{

		item.addEventListener("click",()=>{

			modal.classList.add("active");

			swiper.slideTo(index,0);

		});

	});

	closeBtn.addEventListener("click",()=>{

		modal.classList.remove("active");

	});

	modal.addEventListener("click",(e)=>{

		if(e.target === modal){
			modal.classList.remove("active");
		}

	});

});
</script>