<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="wrap" style="background: #FAF8F5">
	<section class="facilityHeader">

		<div class="breadcrumb">HOME · Facilities ·
			${facility.facilityName}</div>

		<h1 class="title">${facility.facilityName}</h1>

		<p class="desc">${facility.description}</p>

	</section>

	<c:forEach var="img" items="${images}">
		<c:if test="${img.isMain eq 'Y'}">

			<div class="facilityHero">
				<img src="${img.imagePath}">
			</div>

		</c:if>
	</c:forEach>

	<div class="facilityGallery">

		<c:forEach var="img" items="${images}">
			<c:if test="${img.isMain ne 'Y'}">

				<div class="galleryItem">
					<img src="${img.imagePath}">
				</div>

			</c:if>
		</c:forEach>

	</div>
</div>