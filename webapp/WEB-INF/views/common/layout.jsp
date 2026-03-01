<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle != null ? pageTitle : "Vinst Hotel"}</title>

<!-- 공통 CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">

<!-- 페이지별 CSS -->
<c:if test="${pageCss != null}">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/${pageCss}.css">
</c:if>

<!-- 공통 JS -->
<script
	src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/swiper-bundle.min.js"></script>
</head>

<body>

	<!-- HEADER -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- MAIN CONTENT -->
	<main>
		<jsp:include page="${contentPage}" />
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />



</body>
</html>