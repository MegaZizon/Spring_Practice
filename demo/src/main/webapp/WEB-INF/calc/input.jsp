<%--
  Created by IntelliJ IDEA.
  User: zee
  Date: 2024-06-30
  Time: 오후 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/calc/makeResult" method="post">
        <input type="number" name="num1">
        <input type="number" name="num2">
        <button type="submit">send</button>
    </form>
</body>
</html>
