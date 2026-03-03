<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin | Vinst Hotel</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/admin.css">
	
	
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css">
</head>






<body class="admin-body">

	<%@ include file="/WEB-INF/views/common/adminHeader.jsp"%>

	<c:choose>


		<c:when test="${fn:contains(contentPage, 'login.jsp')}">
			<div class="admin-login-wrapper">
				<jsp:include page="${contentPage}" />
			</div>
		</c:when>


		<c:otherwise>
			<div class="admin-wrapper">
				<%@ include file="/WEB-INF/views/common/adminSidebar.jsp"%>

				<div class="admin-content">
					<jsp:include page="${contentPage}" />
				</div>
			</div>
		</c:otherwise>

	</c:choose>

</body>
</html>