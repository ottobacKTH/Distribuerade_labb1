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
<h2>Welcome to the shop, ${user.getUsername()} ${user.getRole()}!</h2>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<h1>Store</h1>

<form action="getStore" method="get">
    <input type="submit" value="Get Store">
</form>
<c:forEach items="${storeList}" var="item">
    <form action="addCartItem" method="post">
        <!-- display field !-->
        <input name="storeItemId" value="${item.getId()}" disabled>
        <input hidden name="storeItemId" value="${item.getId()}">

        <input name="storeItemName" value="${item.getName()}" disabled>
        <input hidden name="storeItemName" value="${item.getName()}">

        <input name="storeItemPrice" value="${item.getPrice()}" disabled>
        <input hidden name="storeItemPrice" value="${item.getPrice()}">

        <input name="storeItemAmount" value="${item.getAmount()}" disabled>
        <label for="amountToAdd">amount to add:</label>
        <input type="text" id="amountToAdd" name="amountToAdd">
        <input type="submit" value="Add to cart!">
    </form>
</c:forEach>

<h1>Cart</h1>
<form action="getCart" method="get">
    <input type="submit" value="Get Cart">
</form>
<table>
    <c:if test="${cartList != null}">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>amount</th>
        </tr>

        <c:forEach items="${cartList}" var="item">
            <tr>
                <th>${item.getId()}</th>
                <th>${item.getName()}</th>
                <th>${item.getPrice()}</th>
                <th>${item.getAmount()}</th>
            </tr>
        </c:forEach>
    </c:if>
</table>
<form action="purchaseCart" method="post">
    <input type="submit" value="purchase cart">
</form>
<c:if test="${purchased != null}">
    <c:if test="${purchased}"> Cart has been purchased!</c:if>
    <c:if test="${!purchased}"> Cart could not be purchased!</c:if>
</c:if>

<form action="addUser" method="post">
    <input type="submit" value="Add User">
</form>

</body>
</html>
