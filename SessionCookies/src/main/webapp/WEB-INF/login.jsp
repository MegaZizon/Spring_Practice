<%--
  Created by IntelliJ IDEA.
  User: zee
  Date: 2024-07-02
  Time: 오후 2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>
    <form action="/login" method="post">
        <input type="text" name="mid">
        <input type="text" name="mpw">
        <input type="checkbox" NAME="auto">
        <button type="submit">LOGIN</button>
    </form>
</body>
</html>
