<%@ page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.DT.UserDTO" %>
<%@ page import="main.DT.ItemDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
<%
    //disable caching
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","No-Cache");
    //redirect if not logged in
    if(session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<h2>Welcome to the shop, ${user.getUserName()} ${user.getRole()}!</h2>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<h1>Store</h1>

<form action="getStore" method="get">
    <input type="submit" value="Get Store">
</form>

<c:forEach items="${storeList}" var="item">
    ${item.getName()}<br>
</c:forEach>

<h1>Cart</h1>
<form action="getCart" method="get">
    <input type="submit" value="Get Cart">
</form>

<form action="addStoreItem" method="post">
    <input type="submit" value="Add to store">
</form>
<form action="addCartItem" method="post">
    <input type="submit" value="Add to cart">
</form>

</body>
</html>
