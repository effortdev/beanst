<%@ page contentType="text/html; charset=UTF-8" %>

<header class="admin-header">
    <div class="admin-header-left">
        <h1>Vinst Hotel Admin</h1>
    </div>

    <div class="admin-header-right">
        <span class="admin-user">
            ${sessionScope.adminId}
        </span>

        <a href="${pageContext.request.contextPath}/admin/logout.do"
           class="admin-logout">
            Logout
        </a>
    </div>
</header>