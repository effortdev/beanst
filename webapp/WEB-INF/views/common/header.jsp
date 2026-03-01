<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>My Hotel</title>

<!-- 공통 CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
</head>

<body>

	<header id="hdr">
		<div class="hdr_inner">
			<div class="logo">
				<img
					src="${pageContext.request.contextPath}/assets/images/beanstLogo.png"
					alt="logo">
			</div>
			<div class="righItem">
				<ul>
					<li>
						<p class="text en">Contact Us</p>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/reservationMain.do"
						id="reserv" class="ko">예약</a></li>
				</ul>
			</div>
		</div>
	</header>

	<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>