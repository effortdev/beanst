<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<header class="admin-header">
	<div class="admin-header-left">
		<h1>Vinst Hotel Admin</h1>
	</div>

	<div class="admin-header-right">
		<c:if test="${not empty sessionScope.admin}">
			<span class="admin-user"> ${sessionScope.admin.role} 님 환영합니다.</span>
			<a href="${pageContext.request.contextPath}/admin/logout.do"
				class="admin-logout"> Logout </a>
		</c:if>
	</div>
</header>