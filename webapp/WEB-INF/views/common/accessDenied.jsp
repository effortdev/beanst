<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>접근 제한</title>
    <style>
        body {
            text-align: center;
            padding: 100px;
            font-family: Arial;
        }
        .box {
            border: 1px solid #ddd;
            padding: 40px;
            display: inline-block;
        }
        .code {
            font-size: 48px;
            color: #d9534f;
        }
        .msg {
            margin-top: 20px;
            font-size: 18px;
        }
        a {
            display: inline-block;
            margin-top: 30px;
            text-decoration: none;
            padding: 10px 20px;
            background: black;
            color: white;
        }
    </style>
</head>
<body>

<div class="box">
    <div class="code">로그인 오류!</div>
    <div class="msg">
        ${errorMsg}
    </div>
    <a href="${pageContext.request.contextPath}/main.do">
        메인으로 이동
    </a>
</div>

</body>
</html>