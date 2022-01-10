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
    <c:if test="${check == true}">
        <script>
            alert("Cập nhật thành công");
        </script>
    </c:if>
    <div class="row mt-5">
        <div class="col-6 align-content-lg-start">
            <a href="/products?action=create" class="btn btn-success">Add new product</a>
        </div>
        <div class="col-6 align-content-end">
            <form method="post" action="/products?action=search">
                <input type="text" placeholder="Search" name="search">
                <input type="submit" value="Search">
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <h4 class="h4">Product List</h4>
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>#</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Color</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${requestScope['listProduct']}" varStatus="count">
                    <tr>
                        <td>${count.count}</td>
                        <td>${product.name}</td>
                        <td>${product.getPriceString()}</td>
                        <td>${product.quantity}</td>
                        <td>${product.color}</td>
                        <td>${product.nameCategory}</td>
                        <td><a href="/products?action=edit&id=${product.id}" class="btn btn-success">Edit</a>
                            <a href="#" onclick="removeProduct(${product.id})" class="btn btn-danger">Remove</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <script>
                function removeProduct(id) {
                    if (confirm("Bạn có chắc chắn muốn gỡ thức uống này ?") == true)
                        window.location = "/products?action=remove&id=" + id;
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>
