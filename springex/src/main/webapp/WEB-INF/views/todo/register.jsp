<%--
  Created by IntelliJ IDEA.
  User: zee
  Date: 2024-07-03
  Time: 오전 2:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/register" method="post">
    <div>
        <input type="text" name="title" placeholder="INSERT TITLE">
    </div>
    <div>
        <input type="date", name="dueDate" value="2022-12-25">
    </div>
    <div>
        Writer: <input type="text" name="writer">
    </div>
    <div>
        Finished: <input type="checkbox" name="finished">
    </div>
    <div>
        <button type="submit">REGISTER</button>
    </div>
</form>
</body>
</html>
