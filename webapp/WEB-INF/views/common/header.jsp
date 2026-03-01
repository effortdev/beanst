<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

	<header id="hdr">
		<div class="hdr_inner">
			<div class="logo">
				<img
					src="${pageContext.request.contextPath}/assets/images/beanstLogo.png"
					alt="logo">
			</div>
			<div class="righItem">
				<ul>
					<li><a href=# id="reserv" class="text en">Contact Us</a></li>
					<li><a
						href="${pageContext.request.contextPath}/reservationMain.do"
						id="reserv" class="ko">예약</a></li>
				</ul>
			</div>
		</div>
	</header>

	<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>