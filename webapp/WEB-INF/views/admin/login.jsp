<%@ page contentType="text/html; charset=UTF-8" %>

<div class="admin-login-container">
    <h2>Admin Login</h2>

    <form action="${pageContext.request.contextPath}/admin/login.do"
          method="post">

        <div class="form-group">
            <label>ID</label>
            <input type="text" name="adminId" required>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="adminPw" required>
        </div>

        <button type="submit">Login</button>

        <c:if test="${not empty errorMsg}">
            <p class="error">${errorMsg}</p>
        </c:if>
    </form>
</div>