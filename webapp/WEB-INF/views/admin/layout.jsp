<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin | Vinst Hotel</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/admin/admin.css">
</head>
<body class="admin-body">

<%@ include file="/WEB-INF/views/common/adminHeader.jsp" %>

<div class="admin-wrapper">
    <%@ include file="/WEB-INF/views/common/adminSidebar.jsp" %>

    <div class="admin-content">
        <jsp:include page="${contentPage}" />
    </div>
</div>

</body>
</html>