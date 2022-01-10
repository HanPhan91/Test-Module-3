<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/10/2022
  Time: 8:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Management</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <h4 class="h4">Add new Product</h4>
            <form method="post">

                <span class="form-text">Product name:</span>
                <input type="text" class="text form-control" placeholder="Name" name="name">
                <span class="form-text">Product price:</span>
                <input type="number" class="text form-control" placeholder="Price" name="price">
                <span class="form-text">Product quantity:</span>
                <input type="number" class="text form-control" placeholder="Quantity" name="quantity">
                <span class="form-text">Product color:</span>
                <input type="text" class="text form-control" placeholder="Color" name="color">
                <span class="form-text">Product description:</span>
                <input type="text" class="text form-control" placeholder="Description" name="desc">
                <span class="form-text">Category:</span>
                <select name="category">
                    <c:forEach var="category" items="${requestScope['listCategory']}">
                        <option value="${product.idCategory}" selected>${category.nameCategory}</option>
                        <c:if test="${category.id != product.idCategory}"></c:if>
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="Add" class="btn btn-success mt-5">
                <a href="/products" class="btn btn-warning mt-5">Back</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
