<%--
  Created by IntelliJ IDEA.
  User: Otto Bäckström
  Date: 2022-10-05
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="login" method="post">
  <label for="username">username:</label>
  <input type="text" id="username" name="username"><br>
  <label for="password">password:</label>
  <input type="password" id="password" name="password"><br>
  <input type="submit" value="Login">
</form>
</body>
</html>
