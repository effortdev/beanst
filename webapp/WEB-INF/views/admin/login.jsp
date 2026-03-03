<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-login-container">
    <h2 class="title">Admin Login</h2>

    <form action="${pageContext.request.contextPath}/admin/login.do"
          method="post">

        <div class="form-group">
            <label>ID</label>
            <input type="text" name="user_id" required>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required>
        </div>

        <button type="submit">Login</button>

        <c:if test="${not empty errorMsg}">
            <p class="error">${errorMsg}</p>
        </c:if>
    </form>
</div>